package JedDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Jedunit {
	
	// jedisPool静态变量
	private static JedisPool jedisPool;
	
	// 使用静态代码块配置
	static {
		try {
			// 1.加载资源进入能内存
			ClassLoader classLoader = Jedunit.class.getClassLoader();
			InputStream resourceAsStream = classLoader.getResourceAsStream("jedis.properties");
			Properties pro = new Properties();
			pro.load(resourceAsStream);
			
			// 2.创建JedisPoolConfig对象,并对其进行设置
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
			
			// 3.传入config对象以及host,port创建出JedisPool连接池对象
			jedisPool = new JedisPool(config, pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过JedisPool连接池对象,获取jedis连接对象,方便客户端使用
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
}
