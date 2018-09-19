package com.mys.akka7;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import com.typesafe.config.ConfigFactory;

public class ProcedureTest extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        PLAY, SLEEP;
    }

    Procedure<Object> happy = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            log.info("i am happy! " + o);
            if (o == Msg.PLAY) {
                getSender().tell("i am already happy!!", getSelf());
                log.info("i am already happy!!");
            } else if (o == Msg.SLEEP) {
                log.info("i do not like sleep!");
                getContext().become(angray);
            } else {
                unhandled(o);
            }
        }
    };
    Procedure<Object> angray = new Procedure<Object>() {
        @Override
        public void apply(Object o) throws Exception {
            log.info("i am angray! " + o);
            if (o == Msg.SLEEP) {
                getSender().tell("i am already angry!!", getSelf());
                log.info("i am already angry!!");
            } else if (o == Msg.PLAY) {
                log.info("i like play.");
                getContext().become(happy);
            } else {
                unhandled(o);
            }
        }
    };

    /**
     * #onReceive这个方法只被调用一次
     */
    @Override
    public void onReceive(Object o) throws Exception {
        log.info("onReceive msg: " + o);
        if (o == Msg.SLEEP) {
            getContext().become(angray);
        } else if (o == Msg.PLAY) {
            getContext().become(happy);
        } else {
            unhandled(o);
        }
    }

    /**
     * https://blog.csdn.net/liubenlong007/article/details/54574878
     * akka学习教程(七) 内置状态转换Procedure
     */
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef procedureTest = system.actorOf(Props.create(ProcedureTest.class), "ProcedureTest");

        procedureTest.tell(Msg.PLAY, ActorRef.noSender());
        procedureTest.tell(Msg.SLEEP, ActorRef.noSender());
        procedureTest.tell(Msg.PLAY, ActorRef.noSender());
        procedureTest.tell(Msg.PLAY, ActorRef.noSender());

        procedureTest.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
