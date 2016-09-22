package com.zhuyiqi.hazelcast.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceImpl;
import java.util.Set;

/**
 * @author ven
 * @email ven.malloc@gmail.com
 * @createdate 2013-12-13 14:17:01
 */
public class ZyqClient {

    /**
     *    <backup-count>1</backup-count>
        <time-to-live-seconds>600</time-to-live-seconds>
        <max-idle-seconds>0</max-idle-seconds>
        <eviction-policy>NONE</eviction-policy>
        <max-size policy="PER_NODE">0</max-size>
        <eviction-percentage>25</eviction-percentage>
        <merge-policy>com.hazelcast.map.merge.PassThroughMergePolicy</merge-policy>
    </map>
     * @param args 
     */
    public static void main(String[] args) {
        Set set = client().getMap("live30Minutes").keySet();
        System.out.println("set");
        System.out.println(set);
        
        HazelcastInstance client = client();
        
        
        MapConfig mc = new MapConfig();
        mc.setBackupCount(1)
                .setAsyncBackupCount(0)
                .setTimeToLiveSeconds(60)
                .setEvictionPolicy(MapConfig.EvictionPolicy.NONE)
                ;
       client.getConfig().addMapConfig(mc); 
    }

    public static HazelcastInstance client() {
        ClientConfig conf = new ClientConfig();
        conf.getGroupConfig().setName("zhuyiqi.com").setPassword("zyq321");
        conf.addAddress("192.168.18.171","192.168.18.162:5702");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(conf);
        return client;
    }

}
