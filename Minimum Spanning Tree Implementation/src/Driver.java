import gui.GUI;

import java.awt.EventQueue;
import java.io.IOException;



public class Driver {

	static String filePath = "E:\\work\\computer\\College\\Second year\\Second Year Second term\\DataStructures Workspace\\Minimum Spanning Tree Implementation\\src\\";

	public static void main(String[] args) throws IOException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(filePath);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
