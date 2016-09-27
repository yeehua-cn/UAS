package org.uas.hazelcast.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceImpl;
import java.util.Set;

/**
 * @author ylh&lt;yeehua@live.cn>
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        Set set = client().getMap("live30Minutes").keySet();
        System.out.println("set");
        System.out.println(set);
        
        HazelcastInstance client = client();
        
        
        MapConfig mc = new MapConfig();
        mc.setBackupCount(1)
                .setAsyncBackupCount(0)
                .setTimeToLiveSeconds(60)
                .setEvictionPolicy(MapConfig.EvictionPolicy.NONE)                ;
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
