package abc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) throws Exception { // ����������Ϣ
		DatagramSocket client = new DatagramSocket(9999); 
		byte data[] = new byte[1024]; // �����������
		DatagramPacket packet = new DatagramPacket(data, data.length);// �������ݱ�
		System.out.println("���ն˵ȴ����շ��͵���Ϣ........"); // ��ʾ��Ϣ
		client.receive(packet); // ������Ϣ����
		System.out.println("���յ�����Ϣ����Ϊ��" + new String(data, 0, packet.getLength()));
		client.close(); // �ر�����
	}
}