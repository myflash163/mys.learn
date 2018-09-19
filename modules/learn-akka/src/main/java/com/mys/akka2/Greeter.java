package com.mys.akka2;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object message) throws InterruptedException {
        if (message == Msg.GREET) {
            System.out.println("Hello World!");
            Thread.sleep(1000);
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }

    }
}
