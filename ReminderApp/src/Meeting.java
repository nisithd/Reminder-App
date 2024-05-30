import java.util.Date;

public class Meeting extends Reminder{
	
	private String associate;
	
	public Meeting(Date date, String title, String description, boolean urgent, String associate) {
		super(date, title, description, urgent);
		this.associate = associate;
	}
	
	public String getAsc() {
		return associate;
	}
	
	public void setAsc(String newAsc) {
		associate = newAsc;
	}
}
