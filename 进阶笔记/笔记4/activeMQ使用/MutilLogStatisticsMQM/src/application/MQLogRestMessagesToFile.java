package application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MQLogRestMessagesToFile {
	public static ObjectName name = null;
	public static MBeanServerConnection connection = null;
	public static Logger log = null;
	public static ExecutorService fService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public static void main(String[] args) throws InterruptedException {

		initConnected();
		log = Logger.getLogger(MQLogRestMessagesToFile.class);  
        PropertyConfigurator.configure("./src/log4j.properties"); 
		new Thread(new Runnable() {
			@Override
			public void run() {
				long currentTimeMillis = System.currentTimeMillis();
				while (true) {
					while (System.currentTimeMillis()-currentTimeMillis<1000) {};
					logRestMessagesNumToFile();
					currentTimeMillis = System.currentTimeMillis();
				}
			}
		}).start();
		
	}
	
	public static void initConnected() {
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1093/jmxrmi");
	        JMXConnector connector = JMXConnectorFactory.connect(url);
	        connector.connect();
	        connection = connector.getMBeanServerConnection();
	        name = new ObjectName("org.apache.activemq:brokerName=localhost,type=Broker"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logRestMessagesNumToFile() {
		try {
	        BrokerViewMBean mBean = MBeanServerInvocationHandler.newProxyInstance(connection,name, BrokerViewMBean.class, true);     
	        for(ObjectName queueName : mBean.getQueues()) {
	            QueueViewMBean queueMBean = MBeanServerInvocationHandler.newProxyInstance(connection, queueName, QueueViewMBean.class, true);
	            log.info("\n------------------------------\n");
	            log.info("消息队列名称--- " + queueMBean.getName());
	            log.info("剩余的消息数 --- " + queueMBean.getQueueSize());
	            log.info("消费者数 --- " + queueMBean.getConsumerCount());
	            log.info("生产者数 --- " + queueMBean.getConsumerCount() );
	            log.info("入队数 --- " + queueMBean.getDequeueCount() );
	            log.info("出队数 --- " + queueMBean.getEnqueueCount() );
	        }
		} catch (Exception e) {
			log.error(e.getCause());
		}
		
	}
	
}

