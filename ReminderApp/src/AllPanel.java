import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AllPanel extends JPanel implements ActionListener{
	
	private JButton back, exit;
	public static JPanel gpanel, mpanel, aspanel, acpanel;
	public static JTable gtable, mtable, astable, actable;
	ImageIcon bg;
	
	public AllPanel() {
		this.setLayout(null);
		
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
		
		gpanel = new JPanel();
		gpanel.setBounds(40, 100, 460, 320);
		gpanel.setOpaque(false);
		this.add(gpanel);
		
		mpanel = new JPanel();
		mpanel.setBounds(500, 100, 460, 320);
		mpanel.setOpaque(false);
		this.add(mpanel);
		
		aspanel = new JPanel();
		aspanel.setBounds(40, 450, 460, 320);
		aspanel.setOpaque(false);
		this.add(aspanel);
		
		acpanel = new JPanel();
		acpanel.setBounds(500, 450, 460, 320);
		acpanel.setOpaque(false);
		this.add(acpanel);
		
		ArrayList<String> genTitles = new ArrayList<String>();
        ArrayList<String> genDescs = new ArrayList<String>();
        ArrayList<String> meetTitles = new ArrayList<String>();
        ArrayList<String> meetDescs = new ArrayList<String>();
        ArrayList<String> asmTitles = new ArrayList<String>();
        ArrayList<String> asmDescs = new ArrayList<String>();
        ArrayList<String> actTitles = new ArrayList<String>();
        ArrayList<String> actDescs = new ArrayList<String>();
        
        for (int i = 1; i < HomePanel.reminderlist.size(); i++) {
        	if (HomePanel.reminderlist.get(i) instanceof Meeting) {
        		meetTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		meetDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	if (HomePanel.reminderlist.get(i) instanceof Assessment) {
        		asmTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		asmDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	if (HomePanel.reminderlist.get(i) instanceof Activity) {
        		actTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		actDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	else {
        		genTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		genDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        }
        
        String[] columnNames = {"Title", "Description"};
        String[][] gdata = new String[genTitles.size()][2];
        String[][] mdata = new String[meetTitles.size()][2];
        String[][] asdata = new String[asmTitles.size()][2];
        String[][] acdata = new String[actTitles.size()][2];
        
        for (int j = 0; j < genTitles.size(); j++) {
        	gdata[j][0] = genTitles.get(j);
        	gdata[j][1] = genDescs.get(j);
        }
        
        for (int j = 0; j < meetTitles.size(); j++) {
        	mdata[j][0] = meetTitles.get(j);
        	mdata[j][1] = meetDescs.get(j);
        }
        
        for (int j = 0; j < asmTitles.size(); j++) {
        	asdata[j][0] = asmTitles.get(j);
        	asdata[j][1] = asmDescs.get(j);
        }
        
        for (int j = 0; j < actTitles.size(); j++) {
        	acdata[j][0] = actTitles.get(j);
        	acdata[j][1] = actDescs.get(j);
        }
        
        DefaultTableModel gtableModel = new DefaultTableModel(gdata, columnNames);
        gtable = new JTable(gtableModel);
        
        DefaultTableModel mtableModel = new DefaultTableModel(mdata, columnNames);
        mtable = new JTable(mtableModel);
        
        DefaultTableModel astableModel = new DefaultTableModel(asdata, columnNames);
        astable = new JTable(astableModel);
        
        DefaultTableModel actableModel = new DefaultTableModel(acdata, columnNames);
        actable = new JTable(actableModel);
        
        JScrollPane gpane = new JScrollPane(gtable);
        JScrollPane mpane = new JScrollPane(mtable);
        JScrollPane aspane = new JScrollPane(astable);
        JScrollPane acpane = new JScrollPane(actable);
        
        gpanel.add(gpane);
        mpanel.add(mpane);
        aspanel.add(aspane);
        acpanel.add(acpane);
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
	
	public static void tableUpdate(JTable editg, JTable editm, JTable editas, JTable editac) {
		ArrayList<String> genTitles = new ArrayList<String>();
        ArrayList<String> genDescs = new ArrayList<String>();
        ArrayList<String> meetTitles = new ArrayList<String>();
        ArrayList<String> meetDescs = new ArrayList<String>();
        ArrayList<String> asmTitles = new ArrayList<String>();
        ArrayList<String> asmDescs = new ArrayList<String>();
        ArrayList<String> actTitles = new ArrayList<String>();
        ArrayList<String> actDescs = new ArrayList<String>();
        
        for (int i = 0; i < HomePanel.reminderlist.size()-1; i++) {
        	if (HomePanel.reminderlist.get(i) instanceof Meeting) {
        		meetTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		meetDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	else if (HomePanel.reminderlist.get(i) instanceof Assessment) {
        		asmTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		asmDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	else if (HomePanel.reminderlist.get(i) instanceof Activity) {
        		actTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		actDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        	else {
        		genTitles.add(HomePanel.reminderlist.get(i).getTitle());
        		genDescs.add(HomePanel.reminderlist.get(i).getDesc());
        	}
        }
        
        String[] columnNames = {"Title", "Description"};
        String[][] gdata = new String[genTitles.size()][2];
        String[][] mdata = new String[meetTitles.size()][2];
        String[][] asdata = new String[asmTitles.size()][2];
        String[][] acdata = new String[actTitles.size()][2];
        
        for (int j = 0; j < genTitles.size(); j++) {
        	gdata[j][0] = genTitles.get(j);
        	gdata[j][1] = genDescs.get(j);
        }
        
        for (int j = 0; j < meetTitles.size(); j++) {
        	mdata[j][0] = meetTitles.get(j);
        	mdata[j][1] = meetDescs.get(j);
        }
        
        for (int j = 0; j < asmTitles.size(); j++) {
        	asdata[j][0] = asmTitles.get(j);
        	asdata[j][1] = asmDescs.get(j);
        }
        
        for (int j = 0; j < actTitles.size(); j++) {
        	acdata[j][0] = actTitles.get(j);
        	acdata[j][1] = actDescs.get(j);
        }
        
        editg.setModel(new DefaultTableModel(gdata, columnNames));
        editm.setModel(new DefaultTableModel(mdata, columnNames));
        editas.setModel(new DefaultTableModel(asdata, columnNames));
        editac.setModel(new DefaultTableModel(acdata, columnNames));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.lightGray);
		java.net.URL url = getClass().getResource("/imgs/bgimg2.jpg");
		bg = new ImageIcon(url);
		
		bg.paintIcon(this, g, 0,0);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 48));
		g.drawString("All Current Reminders", 230, 50);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		g.drawString("General", 240, 95);
		g.drawString("Meetings", 700, 95);
		g.drawString("Assessments", 230, 445);
		g.drawString("Activities", 700, 445);
	}

}
