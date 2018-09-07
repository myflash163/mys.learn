package com.mys.akka3;

import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSON;

public class Greeter extends UntypedActor {


    @Override
    public void onReceive(Object message){
        System.out.println("Greeter收到的数据为：" + JSON.toJSON(message));
        getSender().tell("Greeter工作完成。", getSelf());

    }
}
