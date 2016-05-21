package com.socket2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TestSockServer {
	static List<Client> clientList = new ArrayList<Client>();

	public static void main(String[] args) {

		Socket socket = null;
		ServerSocket s = null;

		try {
			s = new ServerSocket(8888);
			// ׼��
			System.out.println("�������������");
			while (true) {
				// ����
				socket = s.accept();
				Client c = new TestSockServer().new Client(socket);
				clientList.add(c);
				c.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
				System.out.println("Close!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Client extends Thread {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket socket = null;

		public Client(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			String str1 = null;
			try {
				// ������������棬���Խ�ʡ�ڴ�
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());

				while (true) {

					// ѭ��������������Ϣ�����ҽ���Щ��Ϣȫ�����ظ��ͻ���
					if ((str1 = dis.readUTF()) != null) {
						// System.out.println(socket.getPort() + "speak:" +
						// str1);
						for (int i = 0; i < clientList.size(); i++) {
							new DataOutputStream(
									clientList.get(i).socket.getOutputStream())
									.writeUTF(socket.getPort() + "speak:"
											+ str1);
						}

					}
					// dos.writeUTF("Sever speak:" + str1);
				}
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

}
