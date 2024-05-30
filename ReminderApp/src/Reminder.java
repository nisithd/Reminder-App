import java.util.Date;

public class Reminder {
	private java.util.Date date;
	private String title, description;
	private boolean urgent;
	
	public Reminder(Date date, String title, String description, boolean urgent) {
		this.date = date;
		this.title = title;
		this.description = description;
		this.urgent = urgent;
	}
	
	public boolean isUrgent() {
		if (urgent) {
			return true;
		}
		return false;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDesc() {
		return description;
	}
	
	public void setDate(Date newDate){
		date = newDate;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public void setDescription(String newDesc) {
		description = newDesc;
	}
	
	public void setUrgent(boolean newUrgent) {
		urgent = newUrgent;
	}
	
	public String toString() {
		return title;
	}
}
