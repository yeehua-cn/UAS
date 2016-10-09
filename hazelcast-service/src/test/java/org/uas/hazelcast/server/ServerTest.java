package org.uas.hazelcast.server;

import com.hazelcast.config.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Server Tester.
 *
 * @author ylh&gt;yeehua@live.cn&lt;
 * @version 1.0
 * @since <pre>九月 30, 2016</pre>
 */
public class ServerTest {

  @Before
  public void before() throws Exception {
    String[] args = {"arg"};
    Server.main(args);
  }

  @After
  public void after() throws Exception {
    Server.getInstance().shutdown();
  }

  /**
   * Method: getConfig()
   */
  @Test
  public void testGetConfig() throws Exception {
    Config config = Server.getConfig();

    System.out.println("config  = " + config);

    System.out.println("map config = " + config.getMapConfigs().keySet());
  }

  /**
   * Method: setConfig(Config config)
   */
  @Test
  public void testSetConfig() throws Exception {
  }

  /**
   * Method: getInstance()
   */
  @Test
  public void testGetInstance() throws Exception {
  }

  /**
   * Method: setInstance(HazelcastInstance instance)
   */
  @Test
  public void testSetInstance() throws Exception {
  }


}
