import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CompletePanel extends JPanel implements ActionListener{
	
	private JButton back, exit;
	public static ArrayList<Reminder> completelist;
	public static JPanel tpanel;
	public static JTable table;
	ImageIcon bg;
	
	public CompletePanel() {
		this.setLayout(null);
		
		completelist = new ArrayList<Reminder>();
		
		exit = new JButton("Exit");
		exit.setBounds(0, 0, 60, 20);
		exit.setFocusable(false);
		this.add(exit);
		exit.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(904, 0, 80, 20);
		back.setFocusable(false);
		this.add(back);
		back.addActionListener(this);
		
		tpanel = new JPanel();
		tpanel.setBounds(80, 100, 800, 800);
		tpanel.setOpaque(false);
		this.add(tpanel);
		
		ArrayList<String> completeTitles = new ArrayList<String>();
        ArrayList<String> completeDescs = new ArrayList<String>();
        
        for (int i = 0; i < completelist.size(); i++) {
        	completeTitles.add(completelist.get(i).getTitle());
        	completeDescs.add(completelist.get(i).getDesc());
        }
        
        String[] columnNames = {"Title", "Description"};
        String[][] data = new String[completeTitles.size()][2];
        for (int j = 0; j < completeTitles.size(); j++) {
        	data[j][0] = completeTitles.get(j);
        	data[j][1] = completeDescs.get(j);
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        
        JScrollPane pane = new JScrollPane(table);
        
        tpanel.add(pane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			MyFrame.showHome();
		}
		
		if (e.getSource() == exit) {
			System.out.println("exit");
			System.exit(0);
		}
		
	}
	
	public static void tableUpdate(JTable editT, ArrayList<Reminder> complete) {
		ArrayList<String> completeTitles = new ArrayList<String>();
        ArrayList<String> completeDescs = new ArrayList<String>();
        
        for (int i = 0; i < completelist.size(); i++) {
        	completeTitles.add(completelist.get(i).getTitle());
        	completeDescs.add(completelist.get(i).getDesc());
        }
        
        String[] columnNames = {"Title", "Description"};
        String[][] data = new String[completeTitles.size()][2];
        for (int j = 0; j < completeTitles.size(); j++) {
        	data[j][0] = completeTitles.get(j);
        	data[j][1] = completeDescs.get(j);
        }
        
        editT.setModel(new DefaultTableModel(data, columnNames));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.lightGray);
		java.net.URL url = getClass().getResource("/imgs/bgimg2.jpg");
		bg = new ImageIcon(url);
		
		bg.paintIcon(this, g, 0,0);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 48));
		g.drawString("Completed Reminders", 230, 75);
	}

}
