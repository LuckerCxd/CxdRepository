package abc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.CORBA.IMP_LIMIT;

public class EchoServer {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

	public static String getString(String prompt) throws Exception { // 键盘信息输入
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(12345);// 设置服务监听端口

		ExecutorService fService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		fService.execute(new ThreadForServer(server));
	}
}

class ChatThread extends Thread {

	private Socket socket;

	public ChatThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Socket client = socket;
		Scanner scan = null;
		PrintStream out = null;

		try {
			// 首先需要先接收客户端发送来的信息，而后才可以将信息处理之后发送回客户端
			scan = new Scanner(client.getInputStream()); // 客户端输入流
			scan.useDelimiter("\n"); // 设置分隔符
			out = new PrintStream(client.getOutputStream()); // 客户端输出流
			boolean flag = true; // 循环标记
			while (flag) {
				if (scan.hasNext()) { // 有数据接收
					String val = scan.next().trim(); // 接收数据内容
					if ("byebye".equalsIgnoreCase(val)) { // 结束标记
						out.println("ByeByeBye...."); // 回应信息
						flag = false; // 结束循环
					} else {
						out.println("【ECHO】" + val); // Echo信息
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close(); // 关闭输入流
			out.close(); // 关闭输出流
			try {
				client.close(); // 关闭客户端
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

class ThreadForServer implements Runnable {
	private ServerSocket server;

	public ThreadForServer(ServerSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			System.out.println("等待客户端连接............."); // 打印提示信息
			while (true) {
				// 侦听客户端的连接,会阻塞当前的线程
				Socket socket = server.accept();
				// 因为要与多个客户端交互，所以要将socket传递给新的线程，接下来就是创建与socket通信的线程
				new ChatThread(socket).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
