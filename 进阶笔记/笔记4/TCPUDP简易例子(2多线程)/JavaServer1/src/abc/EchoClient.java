package abc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

	

	public static void main(String[] args) throws Exception {
		Socket server = new Socket("localhost", 8002); // 定义服务端的连接信息
// 现在的客户端需要有输入与输出的操作支持，所以依然要准备出Scanner与PrintWriter
		Scanner scan = new Scanner(server.getInputStream()); // 接收服务端输入内容
		scan.useDelimiter("\n");
		PrintStream out = new PrintStream(server.getOutputStream()); // 向服务器端发送内容
		boolean flag = true; // 循环标记
		while (flag) {
			if (scan.hasNext()) { // 有数据接收
				String val = scan.next().trim(); // 接收数据内容
				if ("byebye".equalsIgnoreCase(val)) { // 结束标记
					out.println("ByeByeBye...."); // 回应信息
					flag = false; // 结束循环
				} else {
					out.println("【ECHO】" + val); // Echo信息
					System.out.println("客户端已接收："+val);
				}
			}
		}
		scan.close(); // 关闭输入流
		out.close(); // 关闭输 出流
		server.close(); // 关闭客户端
	}
}