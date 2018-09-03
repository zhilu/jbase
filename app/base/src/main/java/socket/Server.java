package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static int DEFAULT_PORT = 8888;
	private int port;
	
	
	public static void main(String[] args) throws IOException {
		Server srvr = new Server(DEFAULT_PORT);
		srvr.startTask();
	}

	Server(int port) throws IOException {
		this.port = port;
	}

	public void startTask() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				Socket socket = server.accept();
				new TaskHandler(socket).start();
			}
		} catch (IOException e) {
			System.out.println("server stoped");
		} finally{
			try {
				server.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	private class TaskHandler extends Thread {
		private Socket socket;

		private TaskHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			InputStream is = null;
			try {
				is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while(( line = br.readLine()) != null){
					System.out.println(line);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
	}
}
