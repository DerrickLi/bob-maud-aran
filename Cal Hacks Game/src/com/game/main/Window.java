package com.game.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends Canvas {

	private JPanel messageBox;
	private JLabel message;
	
	private static final long serialVersionUID = -1478604005915452565L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		
		JPanel messageBox = new JPanel();
		messageBox.setPreferredSize(new Dimension(600, 200));
		messageBox.setBackground(Color.green);
		frame.getContentPane().add(messageBox, BorderLayout.SOUTH);
		messageBox.setVisible(true);
		
		String myString = 
			    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean eu nulla urna. "
			    + "Donec sit amet risus nisl, a porta enim. Quisque luctus, ligula eu scelerisque gravida,"
			    + " tellus quam vestibulum urna, ut aliquet sapien purus sed erat. Pellentesque consequat vehicula magna,"
			    + " eu aliquam magna interdum porttitor. Class aptent taciti sociosqu ad litora torquent per conubia nostra,"
			    + " per inceptos himenaeos. Sed sollicitudin sapien non leo tempus lobortis. Morbi semper auctor ipsum,"
			    + " a semper quam elementum a. Aliquam eget sem metus.";
		String html1 = "<html><body style='width: ";
	    String html2 = "px'>";
	    
		message = new JLabel(html1 + "580" + html2 + myString);
		
		messageBox.add(message);
		messageBox.setVisible(true);
		
		frame.pack();
		frame.setVisible(true);
		game.start();
	}

	public void setMessageBox(String msg) {
		message.setText(msg);
	}
	
	public void setMessageVisible(boolean visible) {
		messageBox.setVisible(visible);
	}

}
