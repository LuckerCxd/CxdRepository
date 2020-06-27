package application;
import java.text.SimpleDateFormat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class Receiver { 
	private int receiverNumber = 5;  
    public Receiver(int receiverNumber) {
		this.receiverNumber = receiverNumber;
	}
    
    public void ConnectMQAndReceiveMessage() {
    	// ConnectionFactory ：连接工厂，JMS 用它创建连接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 客户端到JMS Provider 的连接  
        Connection connection = null;  
        // Session： 一个发送或接收消息的线程  
        Session session;  
        // Destination ：消息的目的地;消息发送给谁.  
        Destination destination;  
        // 消费者，消息接收者  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
       
        try {  
            // 构造从工厂得到连接对象  
            connection = connectionFactory.createConnection();  
            // 启动  
            connection.start();  
            // 获取操作连接  
            session = connection.createSession(Boolean.FALSE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
            destination = session.createQueue("FirstQueue");  
            consumer = session.createConsumer(destination);  
            reieveMessage( consumer);
            
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
    
    public void reieveMessage(MessageConsumer consumer)  {  
    	
    	try {
    		int okCount = 0;
            for (int i = 1; i <= receiverNumber; i++) {  	
            	 TextMessage message = (TextMessage) consumer.receive(10000);  
                 if (null != message) {  
                	 String tempNum = message.getText();
//                     System.out.println("收到消息" + Thread.currentThread() +tempNum);  
//                     judgeRandomPrimed(Integer.valueOf(tempNum));
                     okCount ++;
                     //System.out.println("i = "+i + " tempNum: "+tempNum);
                 }else {
                	 break;
                 }
                 if(i == receiverNumber) {
                	 System.out.println("receive: "+okCount);
                	 if(RecieveFromMQ.finishConsumer == false) {
                		 RecieveFromMQ.finishConsumer = true;
                		 System.out.println("end "+new SimpleDateFormat("HH:mm:ss.SSS").format(System.currentTimeMillis()));
                	 }
                		 
                	 
                 }
            } 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    }
    
} 


