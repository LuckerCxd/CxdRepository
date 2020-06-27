package application;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.DeliveryMode;  
import javax.jms.Destination;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import org.apache.activemq.ActiveMQConnection;  
import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class Sender {  
    private int sendNumber = 5;  
    public Sender(int sendNumber) {
		this.sendNumber = sendNumber;
	}
    
    
    public void ConnectMQAndSendMessage() {
    	// ConnectionFactory ：连接工厂，JMS 用它创建连接  
        ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS  
        // Provider 的连接  
        Connection connection = null; // Session： 一个发送或接收消息的线程  
        Session session; // Destination ：消息的目的地;消息发送给谁.  
        Destination destination; // MessageProducer：消息发送者  
        MessageProducer producer; // TextMessage message;  
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar  
        connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try { // 构造从工厂得到连接对象  
            connection = connectionFactory.createConnection();  
            // 启动  
            connection.start();  
            // 获取操作连接  
            session = connection.createSession(Boolean.TRUE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
            destination = session.createQueue("FirstQueue");  
            // 得到消息生成者【发送者】  
            producer = session.createProducer(destination);  
            // 设置不持久化，此处学习，实际根据项目决定  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            // 构造消息，此处写死，项目就是参数，或者方法获取  
            sendMessage(session, producer);  
            session.commit();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection)  
                    connection.close();  
            } catch (Throwable ignore) {  
            }  
        } 
    }
    public void sendMessage(Session session, MessageProducer producer)  
            throws Exception {
    	int okCount = 0;
        for (int i = 1; i <= sendNumber; i++) {  
        	int nextInt = ThreadLocalRandom.current().nextInt(2000000000,Integer.MAX_VALUE);
            TextMessage message = session.createTextMessage(String.valueOf(nextInt));  
            //System.out.println("发送消息：" + Thread.currentThread()+" " + nextInt);  
            producer.send(message);  
            okCount++;
            if(i == sendNumber) {
           	 System.out.println("sendMessage: "+okCount);
           	 if(SenderToMQ.finishGenerate == false) {
           		SenderToMQ.finishGenerate = true;
           		 System.out.println("end "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
           	 }
           		 
           	 
            }
        }  
        
    }  
} 