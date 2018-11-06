package fSIM;

import java.io.File;
import java.util.Date;

public class TimeStamp implements java.io.Serializable {

	private static final long serialVersionUID = -2931626867648430105L;
	
	Date date;
	
	public TimeStamp(String path) {
		File file = new File(path);
		this.date = new Date(file.lastModified());
	}
	
	public Date getDate() {
		return date;
	}
	
	
}
