package com.mys.akka3;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class HelloWorld extends UntypedActor {
    @Override
    public void onReceive(Object msg){
        try {
            System.out.println("HelloWorld收到的数据为：" + JSON.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void preStart(){
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter actor path：" + greeter.path());
        // tell it to perform the greeting
        greeter.tell(new Message(2, Arrays.asList("2","sed")), getSelf());
    }

}
