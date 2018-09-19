package com.mys.akka6;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.mys.akka5.InboxTest;
import com.typesafe.config.ConfigFactory;

public class Main {
    /**
     * akka学习教程(六) 路由器Router
     * https://blog.csdn.net/liubenlong007/article/details/54574064
     */
    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterTest.class), "RouterTest");

        int i = 1;
        while (RouterTest.flag.get()) {
            routerTest.tell(InboxTest.Msg.WORKING, ActorRef.noSender());
            if (i % 10 == 0) routerTest.tell(InboxTest.Msg.CLOSE, ActorRef.noSender());
            Thread.sleep(500);
            i++;
        }
    }
}
