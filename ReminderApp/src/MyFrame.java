import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame{

	public static CardLayout cl;
	public static Container cont;
	public static HomePanel home;
	public static CompletePanel done;
	public static AllPanel all;
	
	public MyFrame(){
		
		cont = getContentPane();
		cl = new CardLayout();
		cont.setLayout(cl);
		
		home = new HomePanel();
		cont.add("Home", home);
		
		done = new CompletePanel();
		cont.add("Done", done);
		
		all = new AllPanel();
		cont.add("All", all);
		
		// added all the panels to CardLayout, allows for switching between panels
	}

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
	
	// methods to display the relevant panel when needed
	
	public static void showHome() {
		((CardLayout)cont.getLayout()).show(cont, "Home");
	}
	
	public static void showDone() {
		((CardLayout)cont.getLayout()).show(cont, "Done");
	}
	
	public static void showAll() {
		((CardLayout)cont.getLayout()).show(cont, "All");
	}
}
