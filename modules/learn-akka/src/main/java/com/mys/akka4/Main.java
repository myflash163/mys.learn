package com.mys.akka4;

import akka.actor.*;
import com.mys.akka4_1.RestartActor;
import com.mys.akka4_1.SuperVisor;
import com.typesafe.config.ConfigFactory;

public class Main {
    /**
     * akka学习教程(四) actor生命周期
     * https://blog.csdn.net/liubenlong007/article/details/54093889
     */
    public static void main(String[] args) {
        //创建ActorSystem。一般来说，一个系统只需要一个ActorSystem。
        //参数1：系统名称。参数2：配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("akka.config"));
        ActorRef myWork = system.actorOf(Props.create(MyWorker.class), "MyWork");
        ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "WatchActor");

        myWork.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        myWork.tell(MyWorker.Msg.DONE, ActorRef.noSender());
        //myWork.tell(MyWorker.Msg.CLOSE, ActorRef.noSender());
        //中断myWork
        myWork.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
