package com.mys.akka4;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WatchActor extends UntypedActor {
    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public WatchActor(ActorRef actorRef) {
        getContext().watch(actorRef);
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        if(msg instanceof Terminated){
            //这里简单打印一下，然后停止system
            logger.error(((Terminated)msg).getActor().path() + " has Terminated. now shutdown the system");
           // getContext().system().shutdown();
        }
    }
}
