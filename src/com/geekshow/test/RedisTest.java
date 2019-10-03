package com.geekshow.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.geekshow.entity.User;

import redis.clients.jedis.Jedis;

public class RedisTest {

	@Test
	public void test(){
		
		Jedis jedis = new Jedis("192.168.154.128", 6379);
//		System.out.println(jedis);
//		List<String> list = jedis.mget("name","age");
//		for (String value : list) {
//			System.out.println(value);
//		}
		
//		jedis.set("sex", "1");
		
//		Map<String, String> map = new HashMap<>();
//		map.put("name", "xiao");
//		map.put("age", "24");
//		map.put("qq", "xiao@qq.com");
//		
//		jedis.hmset("user",map);
		
		//User对象 数据量很大，查询很频繁，需要把User表里的数据都放入缓存中
		
		//多种集合配合使用hash和set类型同时使用
		
		//指定业务 查询业务  SYS_USER_SEL_AGE_25;
		//指定业务 查询业务  SYS_USER_SEL_SEX_m;
		//指定业务 查询业务  SYS_USER_SEL_SEX_w;
		
		final String SYS_USER_SEL_AGE_25 = "SYS_USER_SEL_AGE_25";
		final String SYS_USER_SEL_SEX_m = "SYS_USER_SEL_SEX_m";
		final String SYS_USER_SEL_SEX_w = "SYS_USER_SEL_SEX_w";
		final String SYS_USER_TABLE = "SYS_USER_TABLE";
		
		Set<String> sinter = jedis.sinter(SYS_USER_SEL_AGE_25,SYS_USER_SEL_SEX_m);
		for (String string : sinter) {
			String hget = jedis.hget(SYS_USER_TABLE, string);
			System.out.println(hget);
			User user = JSON.parseObject(hget, User.class);
			System.out.println(user.getId());
		}
		
		/*Set<String> user_ages = jedis.smembers(SYS_USER_SEL_AGE_25);
		for (String user_age : user_ages) {
			String user = jedis.hget(SYS_USER_TABLE, user_age);
			System.out.println(user);
		}*/
		
		//做放入操作
		//UUID
		/*Map<String, String> map = new HashMap<>();
		String u1id = UUID.randomUUID().toString();
		User user1 = new User(u1id, "z1", 20, "m");
		map.put(u1id, JSON.toJSONString(user1));
		jedis.sadd(SYS_USER_SEL_SEX_m, u1id);
		
		String u2id = UUID.randomUUID().toString();
		User user2 = new User(u2id, "z2", 25, "m");
		map.put(u2id, JSON.toJSONString(user2));
		jedis.sadd(SYS_USER_SEL_SEX_m, u2id);
		jedis.sadd(SYS_USER_SEL_AGE_25, u2id);

		String u3id = UUID.randomUUID().toString();
		User user3 = new User(u3id, "z3", 25, "w");
		map.put(u3id, JSON.toJSONString(user3));
		jedis.sadd(SYS_USER_SEL_SEX_w, u3id);
		jedis.sadd(SYS_USER_SEL_AGE_25, u3id);

		String u4id = UUID.randomUUID().toString();
		User user4 = new User(u4id, "z4", 29, "m");
		map.put(u4id, JSON.toJSONString(user3));
		jedis.sadd(SYS_USER_SEL_SEX_m, u4id);

		String u5id = UUID.randomUUID().toString();
		User user5 = new User(u5id, "z5", 35, "w");
		map.put(u5id, JSON.toJSONString(user4));
		jedis.sadd(SYS_USER_SEL_SEX_w, u5id);

		jedis.hmset("SYS_USER_TABLE", map);*/
	}
}
