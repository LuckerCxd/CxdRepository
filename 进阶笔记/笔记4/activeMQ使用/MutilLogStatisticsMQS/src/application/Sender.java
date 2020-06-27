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
    	// ConnectionFactory �����ӹ�����JMS ������������  
        ConnectionFactory connectionFactory; // Connection ��JMS �ͻ��˵�JMS  
        // Provider ������  
        Connection connection = null; // Session�� һ�����ͻ������Ϣ���߳�  
        Session session; // Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.  
        Destination destination; // MessageProducer����Ϣ������  
        MessageProducer producer; // TextMessage message;  
        // ����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��jar  
        connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        try { // ����ӹ����õ����Ӷ���  
            connection = connectionFactory.createConnection();  
            // ����  
            connection.start();  
            // ��ȡ��������  
            session = connection.createSession(Boolean.TRUE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // ��ȡsessionע�����ֵxingbo.xu-queue��һ����������queue��������ActiveMq��console����  
            destination = session.createQueue("FirstQueue");  
            // �õ���Ϣ�����ߡ������ߡ�  
            producer = session.createProducer(destination);  
            // ���ò��־û����˴�ѧϰ��ʵ�ʸ�����Ŀ����  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            // ������Ϣ���˴�д������Ŀ���ǲ��������߷�����ȡ  
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
            //System.out.println("������Ϣ��" + Thread.currentThread()+" " + nextInt);  
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