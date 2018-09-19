package com.mys.akka10;

import akka.actor.UntypedActor;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.Future;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class AgentTest extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Integer) {
            for (int i = 0; i < 10000; i++) {
                Future<Integer> future = countAgent.alter(new Mapper<Integer, Integer>(){
                    @Override
                    public Integer apply(Integer parameter) {
                        return parameter + 1;
                    }
                });
                queue.add(future);
            }
            getContext().stop(getSelf());//完成任务，关闭自己
        }

    }

    public static CountDownLatch latch = new CountDownLatch(10);
    public static Agent<Integer> countAgent = Agent.create(0, ExecutionContexts.global());

    public static ConcurrentLinkedQueue<Future<Integer>> queue = new ConcurrentLinkedQueue<>();

}
