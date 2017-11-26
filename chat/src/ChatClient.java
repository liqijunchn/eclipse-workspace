import java.awt.*;
import java.awt.event.*;

public class ChatClient extends Frame {
	
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
				System.exit(0);
			}			
		});
		tf.addActionListener(new TfListener());
		setVisible(true);
	}
	
	private class TfListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tf.getText();
			ta.setText(s);
			tf.setText("");
			
		}
		
		
	}
}
