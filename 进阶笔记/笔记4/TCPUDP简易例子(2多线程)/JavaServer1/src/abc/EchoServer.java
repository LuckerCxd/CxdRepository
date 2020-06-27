package abc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
	public static String getString(String prompt) throws Exception { // 键盘信息输入
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket server = new ServerSocket(8002); // 设置服务监听端口
		System.out.println("等待客户端连接............."); // 打印提示信息
		Socket client = server.accept(); // 等待客户端连接
// 首先需要先接收客户端发送来的信息，而后才可以将信息处理之后发送回客户端
		Scanner scan = new Scanner(client.getInputStream()); // 客户端输入流
		scan.useDelimiter("\n"); // 设置分隔符
		PrintStream out = new PrintStream(client.getOutputStream()); // 客户端输出流
		boolean flag = true; // 循环标记
		
		while (flag) { // 循环处理
			String input = getString("请输入要发送的内容：").trim(); // 获取键盘输入数据
			out.println(input); // 加换行
			if (scan.hasNext()) { // 客户端有回应
				System.out.println(scan.next()); // 输出回应信息
			}
			if ("byebye".equalsIgnoreCase(input)) { // 结束判断
				flag = false; // 修改循环标记
			}
		}
		scan.close(); // 关闭输入流
		out.close(); // 关闭输出流
		client.close(); // 关闭客户端
		server.close(); // 关闭服务端
	}
}
