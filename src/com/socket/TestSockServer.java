package com.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class TestSockServer {
	public static void main(String[] args) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket socket = null;
		Scanner input = new Scanner(System.in);
		try {
			// ׼��
			ServerSocket s = new ServerSocket(8888);
			System.out.println("�������������");
			// ѭ����������
			while (true) {
				// ����
				socket = s.accept();
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());

				String str1 = null;
				if ((str1 = dis.readUTF()) != null) {
					System.out.println("Client speak:" + str1);
				}
				System.out.print("YOU:");
				String serverSpeak = input.next();
				dos.writeUTF("Server speak:" + serverSpeak);
			}
		} catch (SocketException e) {
			System.out.println("�Է��ر��ˣ�");
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

class Client extends Thread {
	public void run() {

	}
}
