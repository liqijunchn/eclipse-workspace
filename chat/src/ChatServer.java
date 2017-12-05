import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatServer {
	
	ServerSocket ss = null;
	boolean serverStarted = false;
	
	public static void main(String[] args) {
		new ChatServer().start();

	}
	
	public void start() {
		try {
			ss = new ServerSocket(8888);
			serverStarted = true;
		} catch(BindException e) {
			System.out.println("端口占用中...");
			System.exit(0);
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			while(serverStarted) {

				Socket s = ss.accept();
				Client c = new Client(s);
				new Thread(c).start();
			}
		} catch (EOFException e) {
			System.out.println("client close");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		}	
	}

	class Client implements Runnable {
		Socket s = null;
		DataInputStream dis = null;
		boolean bconnect = false;
		
		public Client(Socket s) {
			this.s = s;
			
		}
		@Override
		public void run() {	
			try {
				dis = new DataInputStream(s.getInputStream());
				bconnect = true;
				while(bconnect) {
					String str = dis.readUTF();
					System.out.println(str);
				}				
			} catch(EOFException e){
				System.out.println("Close Client");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					dis.close();
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
			}
		
		
	}
}	
