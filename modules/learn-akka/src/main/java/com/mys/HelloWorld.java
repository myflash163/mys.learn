package com.mys;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        if (o == Greeter.Msg.DONE) {
            getContext().stop(getSelf());
        }else{
            System.out.println(o);
            unhandled(o);
        }
    }

    @Override
    public void preStart() throws Exception {
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloworld");
        System.out.println(a.path());
//akka2.Main.main(new String[]{HelloWorld.class.getName()});
    }
}
