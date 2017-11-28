import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	
	Socket s = null;
	DataOutputStream dos = null;

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
		connect();
		setVisible(true);
	}
	
	public void connect() {
		try {
			s = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(s.getOutputStream());
//System.out.print("connection");			
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
			ta.setText(str);
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
}
