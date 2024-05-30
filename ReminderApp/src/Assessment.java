import java.util.Date;

public class Assessment extends Reminder{
	
	private String subject;
	
	public Assessment(Date date, String title, String description, boolean urgent, String subject) {
		super(date, title, description, urgent);
		this.subject = subject;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String newSub) {
		subject = newSub;
	}
}
