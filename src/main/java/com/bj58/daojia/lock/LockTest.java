package com.bj58.daojia.lock;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pangming on 2016/11/21.
 */
public class LockTest {

    public static void main(String[] args) {
//        final Test test = new Test();
//        test.setAge(1);
//        test.setName("hello");
//        change(test);
//        System.out.println(JSONObject.toJSONString(test));
        final Map<String, Object> map = new HashMap<String ,Object>();
        map.put("id", 1);
        map.put("name", "hello");
        changeMap(map);
        System.out.println(map);
    }

    private static Map<String,Object> changeMap(final Map<String,Object> map){
        map.put("id", 17);
        return map;
    }

    private static Test change(Test test){
        test.setAge(18);
        return test;
    }

    static final class Test{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
