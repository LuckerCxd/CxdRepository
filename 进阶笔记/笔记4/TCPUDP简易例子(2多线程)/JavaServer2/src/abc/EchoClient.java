package abc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

	public static String getString(String prompt) throws Exception { // 键盘信息输入
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}

	public static void main(String[] args) throws Exception {
		Socket client = new Socket("localhost", 12345); // 定义服务端的连接信息
		// 现在的客户端需要有输入与输出的操作支持，所以依然要准备出Scanner与PrintWriter
		Scanner scan = new Scanner(client.getInputStream()); // 接收服务端输入内容
		scan.useDelimiter("\n");
		PrintStream out = new PrintStream(client.getOutputStream()); // 向服务器端发送内容
		boolean flag = true; // 循环标记
		while (flag) { // 循环处理
			String input = getString("请输入要发送的内容：").trim(); // 获取键盘输入数据
			out.println(input); // 加换行
			if (scan.hasNext()) { // 服务器端有回应
				System.out.println(scan.next()); // 输出回应信息
			}
			if ("byebye".equalsIgnoreCase(input)) { // 结束判断
				flag = false; // 修改循环标记
			}
		}
		scan.close(); // 关闭输入流
		out.close(); // 关闭输 出流
		client.close(); // 关闭客户端
	}
}