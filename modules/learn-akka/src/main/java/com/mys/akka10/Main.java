package com.mys.akka10;

import akka.actor.*;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws TimeoutException {
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("akka.conf"));

        ActorRef[] actorRefs = new ActorRef[10];
        for (int i = 0; i < 10; i++) {
            actorRefs[i] = system.actorOf(Props.create(AgentTest.class), "AgentTest" + i);
        }

        Inbox inbox = Inbox.create(system);
        for (ActorRef ref : actorRefs) {
            inbox.send(ref, 1);
            inbox.watch(ref);
        }

        System.out.println("countAgent 1:" + AgentTest.countAgent.get());
        /*
         * 这里本来想用JDK里的AtomicInteger, 但是不行，不适用于这里的actor
         */


        //等待所有actor执行完毕
        int closeCount = 0;
        while (true) {
            Object o = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (o instanceof Terminated) {
                closeCount++;
                if (closeCount == actorRefs.length) {
                    break;
                }
            } else {
                System.out.println("o:" + o);
            }
        }

        System.out.println("countAgent 2:" + AgentTest.countAgent.get());
        //等待所有累加线程完成
        Futures.sequence(AgentTest.queue, system.dispatcher()).onComplete(new OnComplete<Iterable<Integer>>() {
            @Override
            public void onComplete(Throwable throwable, Iterable<Integer> integers) throws Throwable {
                System.out.println("countAgent 3:" + AgentTest.countAgent.get());
                system.shutdown();
            }
        }, system.dispatcher());
    }
}
