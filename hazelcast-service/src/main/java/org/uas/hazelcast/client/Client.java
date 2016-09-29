package org.uas.hazelcast.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * * Hazelcast client instance.
 * 服务端客户端类
 *
 * @author ylh&lt;yeehua@live.cn&gt;
 * @since 1.0
 */
public class Client {

  /**
   * 获取HazelcastInstance
   *
   * @param name     名称
   * @param password 密码
   * @param ips      IP地址数组
   * @return HazelcastInstance
   */
  public static HazelcastInstance getClient(String name, String password, String[] ips) {
    ClientConfig conf = new ClientConfig();
    conf.getGroupConfig().setName(name).setPassword(password);
    conf.getNetworkConfig().addAddress(ips);

    return HazelcastClient.newHazelcastClient(conf);
  }

}
