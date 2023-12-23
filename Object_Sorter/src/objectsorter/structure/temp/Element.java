package objectsorter.structure.temp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Element implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6412693892866734312L;
	private static long elementCount = (long) (Math.random()*1000);
	private long elementNumber;
	private String elementName;
	private Date createdDate, lastModifiedDate;
	private String uniqueIndentifer;
	
	public Element() {
		this.elementName = (this.uniqueIndentifer="Object " + 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((this.createdDate = new Date())) 
				+" #"+ (this.elementNumber= elementCount+=Math.random()*1000));
		this.lastModifiedDate = new Date();
	}
	
	public Element(String elementName) {
		this.uniqueIndentifer = "Object " + 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((this.createdDate = new Date())) 
				+" #"+ (this.elementNumber= elementCount+=Math.random()*1000);
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
		updateTime();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		updateTime();
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
		updateTime();
	}
	
	public static long getElementCount() {
		return elementCount;
	}

	public static void setElementCount(long elementCount) {
		Element.elementCount = elementCount;
	}
	
	public String getUniqueIndentifer() {
		return uniqueIndentifer;
	}

	public void setUniqueIndentifer(String uniqueIndentifer) {
		this.uniqueIndentifer = uniqueIndentifer;
		updateTime();
	}
	
	public long getElementNumber() {
		return elementNumber;
	}

	public void setElementNumber(long elementNumber) {
		this.elementNumber = elementNumber;
		updateTime();
	}
	
	@Override
	public String toString() {
		/*StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(elementName+"\n");
		stringBuilder.append("Created @ : " + createdDate+"\t");
		stringBuilder.append("Modified @: " + lastModifiedDate+"\n");
		return stringBuilder.toString();*/
		
		return elementName+"\n";
	}
}
