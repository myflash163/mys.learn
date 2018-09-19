package com.mys.akka2;

import akka.actor.*;
import com.mys.akka4.MyWorker;
import com.mys.akka4.WatchActor;
import com.typesafe.config.ConfigFactory;

public class Main {
    /**
     * akka学习教程(二)HelloWord
     * https://blog.csdn.net/liubenlong007/article/details/54093889
     */
    public static void main(String[] args) {
        //akka.Main.main(new String[] { HelloWorld.class.getName() });
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println(a.path());
    }
}
