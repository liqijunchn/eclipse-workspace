import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatServer {
	
	public static void main(String[] args) {
		try {
			boolean serverStarted = false;
			ServerSocket ss = new ServerSocket(8888);
			serverStarted = true;
			while(serverStarted) {
				boolean bconnect = false;
				Socket s = ss.accept();
				bconnect = true;
				DataInputStream dis = new DataInputStream(s.getInputStream());
				while(bconnect) {
					String str = dis.readUTF();
					System.out.println(str);
			}
				dis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
