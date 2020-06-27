package abc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

	

	public static void main(String[] args) throws Exception {
		Socket server = new Socket("localhost", 8002); // �������˵�������Ϣ
// ���ڵĿͻ�����Ҫ������������Ĳ���֧�֣�������ȻҪ׼����Scanner��PrintWriter
		Scanner scan = new Scanner(server.getInputStream()); // ���շ������������
		scan.useDelimiter("\n");
		PrintStream out = new PrintStream(server.getOutputStream()); // ��������˷�������
		boolean flag = true; // ѭ�����
		while (flag) {
			if (scan.hasNext()) { // �����ݽ���
				String val = scan.next().trim(); // ������������
				if ("byebye".equalsIgnoreCase(val)) { // �������
					out.println("ByeByeBye...."); // ��Ӧ��Ϣ
					flag = false; // ����ѭ��
				} else {
					out.println("��ECHO��" + val); // Echo��Ϣ
					System.out.println("�ͻ����ѽ��գ�"+val);
				}
			}
		}
		scan.close(); // �ر�������
		out.close(); // �ر��� ����
		server.close(); // �رտͻ���
	}
}