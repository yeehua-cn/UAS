package org.uas.hazelcast.server;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;

import java.util.logging.Level;

/**
 * Hazelcast server instance.
 * 服务端启动类
 *
 * @author ylh&lt;yeehua@live.cn&gt;
 * @since 1.0
 */
public class Server {
  private static final ILogger LOG = Logger.getLogger(Server.class);

  private static final String INSTANCE_NAME = "singleton";

  private static final String CONFIG_NAME = "config/hazelcast.xml";

  private static Config config;

  private static HazelcastInstance instance;

  /**
   * 启动Hazelcast 实例
   */
  private static void start() {
    if (LOG.isLoggable(Level.INFO)) {
      LOG.info("  *********   Start Hazelcast with classpath config file [" + CONFIG_NAME + "]...    *********  ");
    }

    config = new ClasspathXmlConfig(CONFIG_NAME);
    config.setInstanceName(INSTANCE_NAME);

    instance = Hazelcast.newHazelcastInstance(config);
  }

  public static Config getConfig() {
    return config;
  }

  public static void setConfig(Config config) {
    Server.config = config;
  }

  public static HazelcastInstance getInstance() {
    return instance;
  }

  public static void setInstance(HazelcastInstance instance) {
    Server.instance = instance;
  }

  public static void main(String[] args) {
    start();
  }


}
