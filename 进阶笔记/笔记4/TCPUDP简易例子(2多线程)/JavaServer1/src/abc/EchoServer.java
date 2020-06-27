package abc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
	public static String getString(String prompt) throws Exception { // ������Ϣ����
		System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket server = new ServerSocket(8002); // ���÷�������˿�
		System.out.println("�ȴ��ͻ�������............."); // ��ӡ��ʾ��Ϣ
		Socket client = server.accept(); // �ȴ��ͻ�������
// ������Ҫ�Ƚ��տͻ��˷���������Ϣ������ſ��Խ���Ϣ����֮���ͻؿͻ���
		Scanner scan = new Scanner(client.getInputStream()); // �ͻ���������
		scan.useDelimiter("\n"); // ���÷ָ���
		PrintStream out = new PrintStream(client.getOutputStream()); // �ͻ��������
		boolean flag = true; // ѭ�����
		
		while (flag) { // ѭ������
			String input = getString("������Ҫ���͵����ݣ�").trim(); // ��ȡ������������
			out.println(input); // �ӻ���
			if (scan.hasNext()) { // �ͻ����л�Ӧ
				System.out.println(scan.next()); // �����Ӧ��Ϣ
			}
			if ("byebye".equalsIgnoreCase(input)) { // �����ж�
				flag = false; // �޸�ѭ�����
			}
		}
		scan.close(); // �ر�������
		out.close(); // �ر������
		client.close(); // �رտͻ���
		server.close(); // �رշ����
	}
}
