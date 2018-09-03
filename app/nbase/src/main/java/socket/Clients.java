package socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Clients implements Runnable {

	private static int num = 3;
	String SERVER_IP = "";
	int SERVER_PORT = 8888;

	public static void main(String[] args) {
		Clients g = new Clients();

		for (int i = 0; i < num; i++) {
			new Thread(g).start();
		}

	}

	@Override
	public void run() {
		Socket socket = null;
		OutputStream os = null;
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			os = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("socket error");
			e.printStackTrace();
			;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		for (int i = 0; i < 20; i++) {
			try {
				bw.write("" + Math.random());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			socket.close();
		} catch (Exception e) {
		}
	}

}
