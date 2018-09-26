package com.mys.akka11;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;
import com.alibaba.fastjson.JSON;

import java.util.UUID;

public class ExamplePersistentActor extends UntypedPersistentActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ExampleState state = new ExampleState();

    public int getNumEvents() {
        return state.size();
    }

    @Override
    public void onReceiveRecover(Object msg) throws Throwable {
        log.info("onReceiveRecover: " + JSON.toJSONString(msg));
        if (msg instanceof Evt) {
            log.info("onReceiveRecover -- msg instanceof Event");
            log.info("event --- " + ((Evt) msg).getData());
            state.update((Evt) msg);
        } else if (msg instanceof SnapshotOffer) {
            log.info("onReceiveRecover -- msg instanceof SnapshotOffer");
            state = (ExampleState) ((SnapshotOffer) msg).snapshot();
        } else {
            unhandled(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) throws Throwable {
        log.info("onReceiveCommand: " + JSON.toJSONString(msg));
        if (msg instanceof Cmd) {
            final String data = ((Cmd)msg).getData();

            // generate an event we will persist after being enriched with a uuid
            final Evt evt1 = new Evt(data + "-" + getNumEvents(), UUID.randomUUID().toString());
            final Evt evt2 = new Evt(data + "-" + (getNumEvents() + 1), UUID.randomUUID().toString());

            // persist event and THEN update the state of the processor
            persistAll(asList(evt1, evt2), evt -> {
                state.update(evt);
                if (evt.equals(evt2)) {
                    // broadcast event on eventstream 发布该事件
                    getContext().system().eventStream().publish(evt);
                }
            });
        } else if (msg.equals("snap")) {
            // IMPORTANT: create a copy of snapshot
            // because ExampleState is mutable !!!
            saveSnapshot(state.copy());
        } else if (msg.equals("print")) {
            System.out.println("state:  " + state);
        } else {
            unhandled(msg);
        }

    }

    @Override
    public String persistenceId() {
        return "sample-id-1";
    }
}
