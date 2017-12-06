import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	private boolean bconnected = false;

	TextArea ta = new TextArea(4,40);
	TextField tf = new TextField();	
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}
	
	public void launchFrame() {
		setLocation(400,400);
		setSize(300,300);
		add(ta,BorderLayout.NORTH);
		add(tf,BorderLayout.SOUTH);	
		pack();
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}			
		});
		tf.addActionListener(new TfListener());
		setVisible(true);
		connect();
		new Thread(new RecvThread()).start();
	}
	
	public void connect() {
		try {
			s = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bconnected = true;
System.out.println("connection");			
		} catch (UnknownHostException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private class TfListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tf.getText();
//			ta.setText(str);
			tf.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}						
		}		
	}
	
	class RecvThread implements Runnable{

		@Override
		public void run() {
			try {
				while(bconnected) {
					String str = dis.readUTF();
					System.out.println(str);
					ta.setText(ta.getText()+ str +"\n");
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
