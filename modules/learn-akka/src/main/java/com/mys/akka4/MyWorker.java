package com.mys.akka4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyWorker extends UntypedActor {
    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static enum Msg{
        WORKING, DONE, CLOSE;
    }
    @Override
    public void onReceive(Object message){

    }



}
