package com.mys.akka4;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;

public class Main {
    public static void main(String[] args) {
        //创建ActorSystem。一般来说，一个系统只需要一个ActorSystem。
        //参数1：系统名称。参数2：配置文件
/*        ActorSystem system = ActorSystem.create("Hello");
        ActorRef myWork = system.actorOf(Props.create(MyWorker.class), "MyWork");
        ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "WatchActor");

        myWork.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        myWork.tell(MyWorker.Msg.DONE, ActorRef.noSender());

        myWork.tell(PoisonPill.getInstance(), ActorRef.noSender());*/

        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef superVisor = system.actorOf(Props.create(com.mys.akka4.SuperVisor.class), "SuperVisor");
        superVisor.tell(Props.create(com.mys.akka4.RestartActor.class), ActorRef.noSender());

        ActorSelection actorSelection = system.actorSelection("akka://strategy/user/SuperVisor/restartActor");//这是akka的路径。restartActor是在SuperVisor中创建的。

        for(int i = 0 ; i < 100 ; i ++){
            actorSelection.tell(com.mys.akka4.RestartActor.Msg.RESTART, ActorRef.noSender());
        }
    }
}
