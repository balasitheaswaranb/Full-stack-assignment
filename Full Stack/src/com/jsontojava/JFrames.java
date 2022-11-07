package com.jsontojava;

import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;

public class JFrames {
	JFrame jFrame = new JFrame();
	Panel panel = new Panel();
	
void toJFrame(String[]strArr) {
	JList<String>  jlist=new JList<String>(strArr);
	panel.add(jlist);
	jFrame.add(panel);
	jFrame.setSize(1000,1000);
	jFrame.setVisible(true);
}
}
