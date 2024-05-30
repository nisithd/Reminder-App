import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class MyButton extends JButton implements ActionListener{
	
	private JPanel panel, cpanel;
	
	public MyButton(String title, JPanel panel, JPanel cpanel){
		super(title);
		this.panel = panel;
		this.cpanel = cpanel;
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < HomePanel.getReminders().size(); i++){
			if (e.getSource() == HomePanel.getButtons().get(i) && 
					HomePanel.getButtons().get(i).getText().equals(HomePanel.getTitles().get(i))){
				Object[] options = {"Edit", "Mark as Done", "Quit"};
				
				JPanel editP = new JPanel();
				editP.setLayout(new BoxLayout(editP, BoxLayout.Y_AXIS));
				JLabel dateLabel = new JLabel(HomePanel.getReminders().get(i).getDate().toString());
				JLabel titleLabel = new JLabel(HomePanel.getReminders().get(i).getTitle());
				titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
				editP.add(titleLabel);
				
				JLabel ascLabel = new JLabel();
			    if (HomePanel.getReminders().get(i) instanceof Meeting) {
					ascLabel.setText("Meeting With: " + ((Meeting)HomePanel.getReminders().get(i)).getAsc());
					editP.add(ascLabel);
			    }
			    
			    JLabel subLabel = new JLabel();
			    if (HomePanel.getReminders().get(i) instanceof Assessment) {
					subLabel.setText("Subject: " + ((Assessment)HomePanel.getReminders().get(i)).getSubject());
					editP.add(subLabel);
			    }
			    
			    JLabel locLabel = new JLabel();
			    if (HomePanel.getReminders().get(i) instanceof Activity) {
					locLabel.setText("Location: " + ((Activity)HomePanel.getReminders().get(i)).getLoc());
					editP.add(locLabel);
			    }
				
				JLabel descLabel = new JLabel(HomePanel.getReminders().get(i).getDesc());
				descLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
				editP.add(dateLabel);
				editP.add(Box.createRigidArea(new Dimension(0, 15)));
				editP.add(descLabel);
				
				int option = JOptionPane.showOptionDialog(null, editP, "Reminder", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		        
				if (option == JOptionPane.YES_OPTION){
		        	
					JDateChooser dateChoose = new JDateChooser(HomePanel.getReminders().get(i).getDate());
					JTextField titleF = new JTextField(HomePanel.getReminders().get(i).getTitle(), 10);
					JLabel titleL = new JLabel("Title: ");
				    JTextArea descF = new JTextArea(HomePanel.getReminders().get(i).getDesc(), 10, 10);
				    JLabel descL = new JLabel("Description: ");
				    descF.setLineWrap(true);
				    descF.setWrapStyleWord(true);
				    
				    JCheckBox urgent = new JCheckBox("Urgent", HomePanel.getReminders().get(i).isUrgent());
					
					JPanel createR = new JPanel();
					createR.setLayout(new BoxLayout(createR, BoxLayout.Y_AXIS));
					createR.add(new JLabel("Edit Date: "));
					createR.add(dateChoose);
					createR.add(Box.createRigidArea(new Dimension(0, 20)));
					createR.add(new JScrollPane(titleL));
				    createR.add(titleF);
				    createR.add(Box.createRigidArea(new Dimension(0, 20))); // a spacer
				    
				    JTextField ascF = new JTextField(10);
					JLabel ascL = new JLabel("Meeting With: ");
				    if (HomePanel.getReminders().get(i) instanceof Meeting) {
						ascF.setText(((Meeting)HomePanel.getReminders().get(i)).getAsc());
				    	createR.add(new JScrollPane(ascL));
						createR.add(ascF);
						createR.add(Box.createRigidArea(new Dimension(0, 20)));
				    }
				    
				    JTextField subF = new JTextField(10);
					JLabel subL = new JLabel("Subject: ");
				    if (HomePanel.getReminders().get(i) instanceof Assessment) {
						subF.setText(((Assessment)HomePanel.getReminders().get(i)).getSubject());
				    	createR.add(new JScrollPane(subL));
						createR.add(subF);
						createR.add(Box.createRigidArea(new Dimension(0, 20)));
				    }
				    
				    JTextField locF = new JTextField(10);
					JLabel locL = new JLabel("Location: ");
				    if (HomePanel.getReminders().get(i) instanceof Activity) {
						locF.setText(((Activity)HomePanel.getReminders().get(i)).getLoc());
				    	createR.add(new JScrollPane(locL));
						createR.add(locF);
						createR.add(Box.createRigidArea(new Dimension(0, 20)));
				    }
				    
				    createR.add(new JScrollPane(descL));
				    createR.add(descF);
				    createR.add(Box.createRigidArea(new Dimension(0,20)));
				    createR.add(urgent);
				    
				    int result = JOptionPane.showConfirmDialog(null, createR, 
				               "Edit Information: ", JOptionPane.OK_CANCEL_OPTION);
				    
				    if (result == JOptionPane.OK_OPTION) {
				    	String title = titleF.getText();
				    	String associate = ascF.getText();
				    	String subject = subF.getText();
				    	String location = locF.getText();
				    	String desc = descF.getText();
				    	
				    	boolean isUrgent = false;
				    	if (urgent.isSelected()) {
				    		isUrgent = true;
				    	}
				    	
				    	HomePanel.getReminders().get(i).setDate(dateChoose.getDate());
				    	HomePanel.getReminders().get(i).setTitle(title);
				    	
				    	if (HomePanel.getReminders().get(i) instanceof Meeting) {
				    		((Meeting)HomePanel.getReminders().get(i)).setAsc(associate);
				    	}
				    	
				    	else if (HomePanel.getReminders().get(i) instanceof Assessment) {
				    		((Assessment)HomePanel.getReminders().get(i)).setSubject(subject);
				    	}
				    	
				    	else if (HomePanel.getReminders().get(i) instanceof Activity) {
				    		((Activity)HomePanel.getReminders().get(i)).setLoc(location);
				    	}
				    	
				    	HomePanel.getReminders().get(i).setDescription(desc);
				    	HomePanel.getReminders().get(i).setUrgent(isUrgent);
				    	HomePanel.getButtons().get(i).setText(title);
				    	HomePanel.getTitles().set(i, title);
						
						if (HomePanel.getReminders().get(i).isUrgent()) {
							HomePanel.getButtons().get(i).setForeground(Color.RED);
						}
						
						AllPanel.tableUpdate(AllPanel.gtable, AllPanel.mtable, AllPanel.astable, AllPanel.actable);
						
					    panel.validate();
				    }
		        }
				
				else if (option == JOptionPane.NO_OPTION) {
					Component[] componentList = panel.getComponents();
			        
			        for (Component c: componentList) {
			        	if (c instanceof JButton) {
			        		if(((JButton)c).getText().equals(this.getText())) {
			        			panel.remove(c);
			        			panel.remove(Box.createRigidArea(new Dimension(5, 0)));
			        			
			        			int idx = HomePanel.buttons.indexOf((JButton)c);
			        			
			        			CompletePanel.completelist.add(HomePanel.reminderlist.get(idx));
			        			CompletePanel.tableUpdate(CompletePanel.table, CompletePanel.completelist);
			        			
			        			HomePanel.buttons.remove(idx);
			        			HomePanel.reminderlist.remove(idx);
			        			HomePanel.titles.remove(idx);
			        			
			        			AllPanel.tableUpdate(AllPanel.gtable, AllPanel.mtable, AllPanel.astable, AllPanel.actable);
			        			
				        		panel.validate();
				        		panel.repaint();
				        		
				        		cpanel.revalidate();
				        		cpanel.repaint();
			        		}
			        	}
			        }
				}
			}
		}
	}

}
