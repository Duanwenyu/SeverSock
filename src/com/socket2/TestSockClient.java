package com.socket2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class TestSockClient {
	public static void main(String[] args) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Scanner input = new Scanner(System.in);
		String s = null;
		Socket socket = null;

		try {
			// �ͻ��˳�������
			// ���ݽ���
			socket = new Socket("127.0.0.1", 8888);
			System.out.println("�ͻ��������ӣ�");
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			do{

				System.out.println("YOU:");
				s = input.next();
				dos.writeUTF(s);

				String str2 = null;
				if ((str2 = dis.readUTF()) != null) {
					System.out.println(str2);
				}
			}while(!s.equals("88"));
			System.out.println("�Է�88�ˣ�");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
