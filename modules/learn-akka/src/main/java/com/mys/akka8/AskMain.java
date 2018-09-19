package com.mys.akka8;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class AskMain {
    /**
     * akka学习教程(八) Actor中的Future-询问模式
     * https://blog.csdn.net/liubenlong007/article/details/54575312
     * 和java线程中的future挺像的，可以将一个actor的返回结果重定向到另一个actor中进行处理，主actor或者进程无需等待actor的返回结果。
     */
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef printActor = system.actorOf(Props.create(PrintActor.class), "PrintActor");
        ActorRef workerActor = system.actorOf(Props.create(WorkerActor.class), "WorkerActor");

        //等等future返回
        Future<Object> future = Patterns.ask(workerActor, 5, 1000);
        int result = (int) Await.result(future, Duration.create(3,TimeUnit.SECONDS));
        System.out.println("result:" + result);

        //不等待返回值，直接重定向到其他actor，有返回值来的时候将会重定向到printActor
        Future<Object> future1 = Patterns.ask(workerActor, 8, 1000);
        Patterns.pipe(future1, system.dispatcher()).to(printActor);

        workerActor.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
