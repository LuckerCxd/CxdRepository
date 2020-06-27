package JedDemo;

import static org.junit.Assert.*;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class Jedemo {
	
	//string
	@Test
	public void test1() throws Exception {
		Jedis jedis = new Jedis("localhost",6379);
		
		jedis.set("username", "libai");
		System.out.println("username: "+jedis.get("username"));
		
		System.out.println("keys * :"+jedis.keys("*"));
		System.out.println("删除: "+jedis.del("username"));
		System.out.println("keys * :"+jedis.keys("*"));
		
		jedis.close();
	}
	
	//hash
	@Test
	public void test2() throws Exception {
		Jedis jedis = new Jedis("localhost",6379);
		
		jedis.hset("hashtype", "username", "libai");
		System.out.println("username: "+jedis.hget("hashtype", "username"));
		System.out.println("keys * :"+jedis.keys("*"));
		System.out.println("删除: "+jedis.hdel("hashtype", "username"));
		System.out.println("keys * :"+jedis.keys("*"));
		
		jedis.close();
	}	
	
	//list
	@Test
	public void test3() throws Exception {
		Jedis jedis = new Jedis("localhost",6379);
		
		jedis.del("listtype");
		jedis.lpush("listtype","libai","dufu","liqingzhao");
		System.out.println("list: " +jedis.lrange("listtype", 0, -1));
		System.out.println("lpop: "+jedis.lpop("listtype"));
		System.out.println("keys * :"+jedis.keys("*"));
		System.out.println("size: "+jedis.lrange("listtype", 0, -1).size());
		jedis.close();
	}
	
	//set
	@Test
	public void test4() throws Exception {
		Jedis jedis = new Jedis("localhost",6379);
		
		jedis.sadd("settype","abc");
		jedis.sadd("settype","def");
		jedis.sadd("settype","ghe");
		
		
		System.out.println("set: "+jedis.smembers("settype"));
		jedis.srem("settype", "abc");
		System.out.println("delete abc:" + jedis.smembers("settype"));
		
		jedis.close();
	}	
	
	//sortedset
	@Test
	public void test5() throws Exception {
		Jedis jedis = new Jedis("localhost",6379);
		
		jedis.zadd("sortedset", 100,"abc");
		jedis.zadd("sortedset", 30,"abc2");
		jedis.zadd("sortedset", 90,"abc3");
		
		System.out.println("sort: "+jedis.zrange("sortedset", 0, -1));
		jedis.zrem("sortedset", "abc");
		System.out.println("delete abc: "+jedis.zrange("sortedset", 0, -1));
		jedis.close();
	}
	
	//jedisUnit
	@Test
	public void test6() throws Exception {
		Jedis jedis = Jedunit.getJedis();
		jedis.set("username", "libai");
		System.out.println("username: "+jedis.get("username"));
		System.out.println("keys * :"+jedis.keys("*"));
		System.out.println("删除: "+jedis.del("username"));
		System.out.println("keys * :"+jedis.keys("*"));
		
		//关闭连接，归还到jedis连接池
		jedis.close();
	}

		
	
	
}
