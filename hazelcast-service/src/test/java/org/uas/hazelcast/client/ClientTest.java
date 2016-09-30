package org.uas.hazelcast.client;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uas.hazelcast.server.Server;

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

  private static final String NAME = "uas.org";
  private static final String PASSWORD = "uas_123456";

  private String[] ipArray = {"127.0.0.1:5701"};

  @Before
  public void setUp() {
    String[] args = {"arg"};
    Server.main(args);
  }

  @After
  public void after() {
    Server.getInstance().shutdown();
  }


  @Test
  public void testGetClient() throws Exception {
    HazelcastInstance client = Client.getClient(NAME, PASSWORD, ipArray);

    IMap<String, String> live5Minutes = client.getMap("live5Minutes");

    String key = "test";

    live5Minutes.set(key, key);

    System.out.println("key set = " + live5Minutes.keySet());
    System.out.println("values = " + live5Minutes.values());

    Assert.assertEquals(key, live5Minutes.get(key));

    Thread.sleep(5 * 60 * 1000);

    Assert.assertNull(live5Minutes.get(key));

  }

}