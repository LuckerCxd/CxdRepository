package abc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket server = new DatagramSocket(8001);
		String str = "Hello, world"; // ������Ϣ
		DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.length(), InetAddress.getByName("localhost"),
				9999); // ��������
		server.send(packet); // ������Ϣ
		System.out.println("���Ͷ���Ϣ�������.....");
		server.close(); // �رշ����
	}
}