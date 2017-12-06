import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
	
	ServerSocket ss = null;
	boolean serverStarted = false;
	List<Client> clients = new ArrayList<Client>();
	
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
System.out.println("a client connected");				
				new Thread(c).start();
				clients.add(c);
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

	class Client implements Runnable {
		Socket s = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		boolean bconnect = false;
		
		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				bconnect = true;
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		@Override
		public void run() {	 
			try {
				while(bconnect) {
					String str = dis.readUTF();
System.out.println(str);
					for(int i =0;i<clients.size();i++) {
						Client c =clients.get(i);
						c.send(str);
					}
				}				
			} catch(EOFException e){
				System.out.println("Close Client");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					dis.close();
					dos.close();
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
			}
				
	}
		private void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}	
}
