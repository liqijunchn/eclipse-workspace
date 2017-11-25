import java.awt.*;

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
		add(tf,BorderLayout.CENTER);
		setVisible(true);
	}

}
