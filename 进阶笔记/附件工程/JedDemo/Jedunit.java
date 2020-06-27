package JedDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Jedunit {
	
	// jedisPool��̬����
	private static JedisPool jedisPool;
	
	// ʹ�þ�̬���������
	static {
		try {
			// 1.������Դ�������ڴ�
			ClassLoader classLoader = Jedunit.class.getClassLoader();
			InputStream resourceAsStream = classLoader.getResourceAsStream("jedis.properties");
			Properties pro = new Properties();
			pro.load(resourceAsStream);
			
			// 2.����JedisPoolConfig����,�������������
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
			
			// 3.����config�����Լ�host,port������JedisPool���ӳض���
			jedisPool = new JedisPool(config, pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ͨ��JedisPool���ӳض���,��ȡjedis���Ӷ���,����ͻ���ʹ��
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
}
