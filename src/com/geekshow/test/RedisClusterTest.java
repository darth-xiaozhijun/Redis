package com.geekshow.test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClusterTest {

	public static void main(String[] args) {
		
		Set<HostAndPort> jedisClusterNode = new HashSet<>();
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7001));
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7002));
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7003));
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7004));
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7005));
		jedisClusterNode.add(new HostAndPort("192.168.154.128", 7006));

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(100);
		jedisPoolConfig.setMaxIdle(20);
		jedisPoolConfig.setMaxWaitMillis(-1);
		jedisPoolConfig.setTestOnBorrow(true);
		
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNode,600,100,jedisPoolConfig);
		
		System.out.println(jedisCluster.set("name", "xiaozhijun"));
		System.out.println(jedisCluster.set("age", "24"));
		System.out.println(jedisCluster.set("sex", "nan"));
		System.out.println(jedisCluster.get("name"));
		System.out.println(jedisCluster.get("age"));
		System.out.println(jedisCluster.get("sex"));

	}
}
