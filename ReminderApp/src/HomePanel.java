import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HomePanel extends JPanel implements ActionListener{
	
	private JButton addR, removeR, exit, completed, all;
	public JPanel p, p1;
	public static ArrayList<Reminder> reminderlist;
	public static ArrayList<JButton> buttons;
	public static ArrayList<String> titles;
	public static JDateChooser calendar;
	ImageIcon bg;
	
	public HomePanel() {
		
		reminderlist = new ArrayList<Reminder>();
		buttons = new ArrayList<JButton>();
		titles = new ArrayList<String>();
		
		reminderlist.add(null);
		buttons.add(null);
		titles.add(null);
		
		// exit button
		exit = new JButton("Exit");
		exit.setBounds(0, 0, 60, 20);
		exit.setFocusable(false);
		this.add(exit);
		exit.addActionListener(this);
		
		completed = new JButton("Check Completed");
		completed.setBounds(225, 0, 150, 20);
		completed.setFocusable(false);
		this.add(completed);
		completed.addActionListener(this);
		
		all = new JButton("View All");
		all.setBounds(525, 0, 100, 20);
		all.setFocusable(false);
		this.add(all);
		all.addActionListener(this);
		
		
		calendar = new JDateChooser();
		calendar.setBounds(525, 100, 200, 20);
		calendar.getJCalendar().setPreferredSize(new Dimension(500, 400));

		calendar.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName()) && e.getNewValue() != null) {
			                System.out.println(e.getPropertyName()
			                    + ": " + (Date) e.getNewValue());
			                
			                Calendar c = Calendar.getInstance();
			                c.setTime((Date)e.getNewValue());
			                
			                ArrayList<String> currentTitles = new ArrayList<String>();
					        ArrayList<String> currentDescs = new ArrayList<String>();
					        
					        for (int i = 0; i < reminderlist.size()-1; i++) {
					        	Calendar cal = Calendar.getInstance();
					        	cal.setTime(reminderlist.get(i).getDate());
					        	if(c.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && c.get(Calendar.MONTH) == cal.get(Calendar.MONTH) 
					        			&& c.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
					        		currentTitles.add(reminderlist.get(i).getTitle());
					        		currentDescs.add(reminderlist.get(i).getDesc());
					        	}
					        }
					        
					        String[] columnNames = {"Title", "Description"};
					        String[][] data = new String[currentTitles.size()][2];
					        for (int j = 0; j < currentTitles.size(); j++) {
					        	data[j][0] = currentTitles.get(j);
					        	data[j][1] = currentDescs.get(j);
					        }
					        
					        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
					        JTable table = new JTable(tableModel);
					        
					        JPanel currentR = new JPanel();
					        currentR.setLayout(new BoxLayout(currentR, BoxLayout.Y_AXIS));
					        currentR.add(new JScrollPane(table));
					        
					        if (currentTitles.size() > 0) {
					        	JOptionPane.showConfirmDialog(null, currentR, 
							               "Current Reminders", JOptionPane.OK_CANCEL_OPTION);
					        	calendar.setCalendar(null);
					        }
					        calendar.setCalendar(null);
			            }
			        }
			    });
		this.add(calendar);
		
		this.setLayout(new BorderLayout());
		
		p = new JPanel();
		p.setOpaque(false);
		
		// set up sidebar with boxlayout inside right section of borderlayout
		this.add(p, BorderLayout.EAST);
		BoxLayout boxlayout = new BoxLayout(p, BoxLayout.Y_AXIS);
		p.setLayout(boxlayout);
		
		
		// add reminder button
		addR = new JButton("Add reminder");
		p.add(addR);
		p.setPreferredSize(new Dimension(150, 800));
		p.add(Box.createRigidArea(new Dimension(0,5)));
		addR.addActionListener(this);
		
		// remove reminder button
		removeR = new JButton("Delete reminder");
		p.add(removeR);
		p.add(Box.createRigidArea(new Dimension(0,40)));
		p1 = new JPanel();
		JScrollPane pane1 = new JScrollPane(p1);
		pane1.setOpaque(false);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p.add(pane1);
		
		removeR.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// add reminder
		if(e.getSource() == addR) {
			Component e3 = (Component)e.getSource();
			Object[] titleList = {"General", "Meeting", "Assessment", "Activity"};
	        Object rType = JOptionPane.showInputDialog(e3, "Choose Reminder Type", 
	        		"Remove Reminder", JOptionPane.QUESTION_MESSAGE, null, titleList, titleList[0]);
			
	        // create components to be added to dialog 
			JTextField titleF = new JTextField(10);
			JLabel titleL = new JLabel("Title: ");
		    JTextArea descF = new JTextArea(10, 10);
		    JLabel descL = new JLabel("Description: ");
		    descF.setLineWrap(true);
		    descF.setWrapStyleWord(true);
		    
		    JCheckBox urgent = new JCheckBox("Urgent");
			
		    // start adding components to dialog
			JPanel createR = new JPanel();
			createR.setLayout(new BoxLayout(createR, BoxLayout.Y_AXIS));
			createR.add(new JLabel("Choose a Date: "));
			JDateChooser dateChoose = new JDateChooser();
			createR.add(dateChoose);
			createR.add(Box.createRigidArea(new Dimension(0, 20)));
			createR.add(new JScrollPane(titleL));
		    createR.add(titleF);
		    createR.add(Box.createRigidArea(new Dimension(0, 20))); // a spacer
		    
		    // create components for Meeting, and add if it is a Meeting object
		    JTextField ascF = new JTextField(10);
			JLabel ascL = new JLabel("Meeting With: ");
		    if (rType.equals("Meeting")) {
				createR.add(new JScrollPane(ascL));
				createR.add(ascF);
				createR.add(Box.createRigidArea(new Dimension(0, 20)));
		    }
		    
		    // create components for Assessment, and add if it is an Assessment object
		    JTextField subF = new JTextField(10);
			JLabel subL = new JLabel("Subject: ");
		    if (rType.equals("Assessment")) {
				createR.add(new JScrollPane(subL));
				createR.add(subF);
				createR.add(Box.createRigidArea(new Dimension(0, 20)));
		    }
		    
		    // create components for Activity, and add if it is an Activity object
		    JTextField locF = new JTextField(10);
			JLabel locL = new JLabel("Location: ");
		    if (rType.equals("Activity")) {
				createR.add(new JScrollPane(locL));
				createR.add(locF);
				createR.add(Box.createRigidArea(new Dimension(0, 20)));
		    }
		    
		    // add the rest of the components (description, urgent)
		    createR.add(new JScrollPane(descL));
		    createR.add(descF);
		    createR.add(Box.createRigidArea(new Dimension(0,20)));
		    createR.add(urgent);
		    
		    // get result of dialog
		    int result = JOptionPane.showConfirmDialog(null, createR, 
		               "Enter Information: ", JOptionPane.OK_CANCEL_OPTION);
		    
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	// if user presses ok, then retrieve data
		    	java.util.Date date = dateChoose.getDate();
		    	String title = titleF.getText();
		    	String associate = ascF.getText();
		    	String subject = subF.getText();
		    	String location = locF.getText();
		    	String desc = descF.getText();
		    	
		    	boolean isUrgent = false;
		    	if (urgent.isSelected()) {
		    		isUrgent = true;
		    	}
		    	
		    	// create specific reminder using inputted data
		    	Reminder newR = new Reminder(null, null, null, false);
		    	if (rType.equals("General")) {
		    		newR = new Reminder(date, title, desc, isUrgent);
		    	}
		    	
		    	else if (rType.equals("Meeting")) {
		    		newR = new Meeting(date, title, desc, isUrgent, associate);
		    	}
		    	
		    	else if (rType.equals("Assessment")) {
		    		newR = new Assessment(date, title, desc, isUrgent, subject);
		    	}
		    	
		    	else if (rType.equals("Activity")) {
		    		newR = new Activity(date, title, desc, isUrgent, location);
		    	}
				
		    	// create button
		    	MyButton newButton = new MyButton(title, p1, CompletePanel.tpanel);
				newButton.addActionListener(this);
				
				// add reminder/button/title to respective arraylist
				reminderlist.add(newR);
				buttons.add(newButton);
				titles.add(title);
				
				// shuffle around arraylists so that the last index is always null
				int bIdx = buttons.indexOf(newButton);
				int nIdx = buttons.indexOf(null);
				
				Collections.swap(reminderlist, bIdx, nIdx);
				Collections.swap(buttons, bIdx, nIdx);
				Collections.swap(titles, bIdx, nIdx);
				
				// add the button to the panel
				p1.add(newButton);
				
				// red text on button if urgent
				if (newR.isUrgent()) {
					newButton.setForeground(Color.RED);
				}
				
				// buttons are a specific colour for each type (blue, purple, green respectively)
				if (newR instanceof Meeting) {
					newButton.setBackground(new Color(142, 176, 250));
				}
				
				else if (newR instanceof Assessment) {
					newButton.setBackground(new Color(166, 131, 252));
				}
				
				else if (newR instanceof Activity) {
					newButton.setBackground(new Color(176, 255, 168));
				}
				
				// update tables in AllPanel
				AllPanel.tableUpdate(AllPanel.gtable, AllPanel.mtable, AllPanel.astable, AllPanel.actable);
				
				// validate so that changes apply to the panel
			    p1.validate();
			    
			    // Highlight the date
			    Calendar c = Calendar.getInstance();
			    c.setTime(date);
				HighlightEvaluator evaluator = new HighlightEvaluator();
				evaluator.add(c.getTime());    
				calendar.getJCalendar().getDayChooser().addDateEvaluator(evaluator);
		    }
		}
		
		else if(e.getSource() == exit) {
			
			System.out.println("exit");
			System.exit(0);
		}
		
		else if (e.getSource() == completed) {
			MyFrame.showDone();
		}
		
		else if (e.getSource() == all) {
			MyFrame.showAll();
		}
		
		else if (e.getSource() == removeR) {

			Component e2 = (Component)e.getSource();
			Object[] titleList = titles.toArray();
	        Object removed = JOptionPane.showInputDialog(e2, "Select Reminder", 
	        		"Remove Reminder", JOptionPane.QUESTION_MESSAGE, null, titleList, titleList[0]);
	        String removeName = (String)removed;
	        System.out.println(removed);
	        
	        Component[] componentList = p1.getComponents();
	        
	        for (Component c: componentList) {
	        	if (c instanceof JButton) {
	        		if(((JButton)c).getText().equals(removeName)) {
	        			p1.remove(c);
	        			p1.remove(Box.createRigidArea(new Dimension(5, 0)));
	        			
	        			int idx = buttons.indexOf((JButton)c);
	        			buttons.remove(idx);
	        			reminderlist.remove(idx);
	        			titles.remove(idx);
	        			
	        			AllPanel.tableUpdate(AllPanel.gtable, AllPanel.mtable, AllPanel.astable, AllPanel.actable);
	        			
		        		p1.validate();
		        		p1.repaint();
	        		}
	        	}
	        }
	        
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.lightGray);
		java.net.URL url = getClass().getResource("/imgs/bgimg2.jpg");
		bg = new ImageIcon(url);
		
		bg.paintIcon(this, g, 0,0);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 48));
		g.drawString("Welcome...", 350, 675);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		g.drawString("Current Reminders:", 835, 82);
		g.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		g.drawString("Search for a Date:", 300, 116);
	}
	
	public static ArrayList<Reminder> getReminders(){
		return reminderlist;
	}
	
	public static ArrayList<JButton> getButtons(){
		return buttons;
	}
	public static ArrayList<String> getTitles(){
		return titles;
	}
	
}
