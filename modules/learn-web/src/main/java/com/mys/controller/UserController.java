package com.mys.controller;


import com.mys.dto.Demo;
import javassist.*;
import org.joor.Reflect;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by mengyusheng on 2017/5/3.
 */

public class UserController {
    public static void main(String[] args) {
        Map<String,Demo> aa = new HashMap<String,Demo>();
        aa.put("bb",null);
        System.out.println(aa.size());
        Demo cc = aa.get("bb");
        System.out.println(cc);
        cc = new Demo();
        System.out.println(cc);

        System.out.println(aa.get("bb"));

    }


}
