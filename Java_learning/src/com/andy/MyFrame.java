package com.andy;

import java.awt.Frame;

public class MyFrame extends Frame {
	public void launchFrame() {
		this.setBounds(400, 400, 300, 300);
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new MyFrame().launchFrame();

	}

}
