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
    	// ConnectionFactory �����ӹ�����JMS ������������  
        ConnectionFactory connectionFactory;  
        // Connection ��JMS �ͻ��˵�JMS Provider ������  
        Connection connection = null;  
        // Session�� һ�����ͻ������Ϣ���߳�  
        Session session;  
        // Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.  
        Destination destination;  
        // �����ߣ���Ϣ������  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
       
        try {  
            // ����ӹ����õ����Ӷ���  
            connection = connectionFactory.createConnection();  
            // ����  
            connection.start();  
            // ��ȡ��������  
            session = connection.createSession(Boolean.FALSE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // ��ȡsessionע�����ֵxingbo.xu-queue��һ����������queue��������ActiveMq��console����  
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
//                     System.out.println("�յ���Ϣ" + Thread.currentThread() +tempNum);  
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


