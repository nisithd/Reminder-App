import java.util.Date;

public class Activity extends Reminder {
	
	private String location;
	
	public Activity(Date date, String title, String description, boolean urgent, String location) {
		super(date, title, description, urgent);
		this.location = location;
	}
	
	public String getLoc() {
		return location;
	}
	
	public void setLoc(String newLoc) {
		location = newLoc;
	}
}
