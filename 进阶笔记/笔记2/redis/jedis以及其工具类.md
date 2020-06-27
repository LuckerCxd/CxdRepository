jedis以及其工具类:
    
    jedis:使用java代码操作redis:
    jedis工具类：通过jedis连接池进行一些配置后，
            获取jedis之后操作redis,操作完成后,将连接归还于连接池

1.jedis:
-

    步骤：
        1.打开redis-server.exe
        2.在工程中导入jar包：
            commons-pool2-2.7.0.jar
            jedis-3.1.0.jar
        3.创建jedis对象,操作redis,释放连接
            // 创建jedis对象
            Jedis jedis = new Jedis("localhost",6379);
            // 操作redis
            ...
            // 释放连接
            jedis.close();

举例：操作(string,hash,set,sortedset)

    package JedDemo;
    import static org.junit.Assert.*;
    import org.junit.Test;
    import redis.clients.jedis.Jedis;

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
    }


2.jedis工具类:
-
    
    步骤
    1.打开redis-server.exe
    2.在工程中导入jar包：
        commons-pool2-2.7.0.jar
        jedis-3.1.0.jar
        scijava-log-slf4j-1.0.1.jar
        slf4j-api-1.7.3.jar
    3.在src下创建properties文件，写入配置信息
    4.创建jedis工具类,有jedisPool静态变量，使用静态代码块配置:
            // 1.加载资源进入能内存
            // 2.创建JedisPoolConfig对象,并对其进行设置
            // 3.传入config对象以及host,port创建出JedisPool连接池对象
        和获取jedis连接(也就是资源)的静态方法
        
    5.客户端使用工具类获取jedis对象，并执行redis操作,以及归还连接
        

jedis.properties
-
host=127.0.0.1
port=6379
maxTotal=10



JedisUnit
-
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

客户端使用：
    
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




