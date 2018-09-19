package com.mys.akka4_1;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;
import akka.japi.Function;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/liubenlong007/article/details/54093889
 * 这里可以对actor进行监督，在actor报出异常的时候进行处理。由于这个和生命周期也有关，所以放到这里一起说了。
 *
 * akka监督策略有两种：
 *
 * OneForOneStrategy 只对出问题的子actor进行处理. 这是默认策略
 * AllForOneStrategy 对子actor以及他的所有兄弟actor进行处理
 * 监督者，监督策略
 */
public class SuperVisor extends UntypedActor {

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(3, Duration.create(1,TimeUnit.MINUTES),//一分钟内重试3次，超过则kill掉actor
                new Function<Throwable,SupervisorStrategy.Directive>() {

                    public SupervisorStrategy.Directive apply(Throwable throwable) throws Exception {
                        if(throwable instanceof ArithmeticException){//ArithmeticException是出现异常的运算条件时，抛出此异常。例如，一个整数“除以零”时，抛出此类的一个实例。
                            System.out.println("meet ArithmeticException ,just resume.");
                            return  SupervisorStrategy.resume();//继续; 重新开始; 恢复职位;
                        }else if(throwable instanceof NullPointerException){
                            System.out.println("meet NullPointerException , restart.");
                            return SupervisorStrategy.restart();
                        }else if(throwable instanceof IllegalArgumentException){
                            System.out.println("meet IllegalArgumentException ,stop.");
                            return SupervisorStrategy.stop();
                        }else{
                            System.out.println("escalate.");
                            return SupervisorStrategy.escalate();//使逐步升级; 使逐步上升; 乘自动梯上升;也就是交给更上层的actor处理。抛出异常
                        }
                    }
                });
    }
    @Override
    public void onReceive(Object o){
        if(o instanceof Props){
            getContext().actorOf((Props)o , "restartActor");
        }else{
            unhandled(o);
        }
    }
}
