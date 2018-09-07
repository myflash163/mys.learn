package com.mys.akka3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class HelloWorld extends UntypedActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        try {
            System.out.println("HelloWorld收到的数据为：" + JSON.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void preStart() throws Exception {
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        greeter.tell(new Message(2, Arrays.asList("2","sed")), getSelf());
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloworld");
        System.out.println(a.path());
    }
}
