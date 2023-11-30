package objectsorter.structure.temp;

import java.io.Serializable;
import java.util.Date;

public class Element implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6412693892866734312L;
	
	private String elementName;
	private Date createdDate, lastModifiedDate;
	
	public Element() {
		this.createdDate = new Date();
		this.elementName = "Default Object " + this.createdDate.toString();
		this.lastModifiedDate = new Date();
	}
	
	public Element(String elementName) {
		this.createdDate = new Date();
		this.elementName = elementName;
		this.lastModifiedDate = new Date();
	}
	
	public Date updateTime() {
		return (this.lastModifiedDate = new Date());
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(elementName+"\n");
		stringBuilder.append("Created @ : " + createdDate+"\t");
		stringBuilder.append("Modified @: " + lastModifiedDate+"\n");
		return stringBuilder.toString();
	}
	
}
