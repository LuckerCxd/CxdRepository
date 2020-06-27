package abc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) throws Exception { // 接收数据信息
		DatagramSocket client = new DatagramSocket(9999); 
		byte data[] = new byte[1024]; // 保存接收数据
		DatagramPacket packet = new DatagramPacket(data, data.length);// 创建数据报
		System.out.println("接收端等待接收发送的消息........"); // 提示信息
		client.receive(packet); // 接收消息内容
		System.out.println("接收到的消息内容为：" + new String(data, 0, packet.getLength()));
		client.close(); // 关闭连接
	}
}