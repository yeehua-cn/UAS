package com.zhuyiqi.hazelcast.server;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

/**
 * zhuyiqi Hazelcast instance;
 *
 * @author zw373200230@gmail.com
 * @since version 0.1
 *
 */
public class Server {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("startHazelcast via classpath config..");
        Config cfg = new ClasspathXmlConfig("hazelcast.xml");
        cfg.setInstanceName("singlnton");

        Hazelcast.newHazelcastInstance(cfg);
    }
}
