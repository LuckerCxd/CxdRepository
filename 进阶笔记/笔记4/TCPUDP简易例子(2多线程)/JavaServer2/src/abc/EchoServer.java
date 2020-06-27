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

	public static String getString(String prompt) throws Exception { // ������Ϣ����
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(12345);// ���÷�������˿�

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
			// ������Ҫ�Ƚ��տͻ��˷���������Ϣ������ſ��Խ���Ϣ����֮���ͻؿͻ���
			scan = new Scanner(client.getInputStream()); // �ͻ���������
			scan.useDelimiter("\n"); // ���÷ָ���
			out = new PrintStream(client.getOutputStream()); // �ͻ��������
			boolean flag = true; // ѭ�����
			while (flag) {
				if (scan.hasNext()) { // �����ݽ���
					String val = scan.next().trim(); // ������������
					if ("byebye".equalsIgnoreCase(val)) { // �������
						out.println("ByeByeBye...."); // ��Ӧ��Ϣ
						flag = false; // ����ѭ��
					} else {
						out.println("��ECHO��" + val); // Echo��Ϣ
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close(); // �ر�������
			out.close(); // �ر������
			try {
				client.close(); // �رտͻ���
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
			System.out.println("�ȴ��ͻ�������............."); // ��ӡ��ʾ��Ϣ
			while (true) {
				// �����ͻ��˵�����,��������ǰ���߳�
				Socket socket = server.accept();
				// ��ΪҪ�����ͻ��˽���������Ҫ��socket���ݸ��µ��̣߳����������Ǵ�����socketͨ�ŵ��߳�
				new ChatThread(socket).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
