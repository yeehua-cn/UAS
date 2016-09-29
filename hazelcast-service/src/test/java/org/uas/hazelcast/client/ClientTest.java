package org.uas.hazelcast.client;

import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Test;

import java.util.Set;

/**
 * ClientTest.class.
 * Title:ClientTest
 * Description:
 * Created in 2016-9-29 下午2:41
 *
 * @author ylh &gt;yeehua@live.cn>
 * @version 1.0
 */
public class ClientTest {

  private String name = "uas.org";
  private String password = "uas_123456";

  private String[] ipArray = {"192.168.26.57:5701"};


  @Test
  public void getClient() throws Exception {
    HazelcastInstance client = Client.getClient(name, password, ipArray);

    Set set = client.getMap("live30Minutes").keySet();
    System.out.println("set");
    System.out.println(set);


    MapConfig mc = new MapConfig();
    mc.setBackupCount(1)
        .setAsyncBackupCount(0)
        .setTimeToLiveSeconds(60)
        .setEvictionPolicy(EvictionPolicy.NONE);

//    client.getConfig().addMapConfig(mc);
  }

}