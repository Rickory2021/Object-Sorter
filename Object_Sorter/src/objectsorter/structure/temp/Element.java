package objectsorter.structure.temp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Element implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6412693892866734312L;
	private static int elementCount = 0;
	private static String lastElementConstruct = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	private int elementNumber;
	private String elementName;
	private Date createdDate, lastModifiedDate;
	private String uniqueIndentifer;
	
	public Element() {
		this.createdDate=new Date();
		if(lastElementConstruct.equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.createdDate))) {
			elementCount+=Math.random()*1000+1;
			this.elementNumber=elementCount;
		}else {
			lastElementConstruct = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			elementCount=0;
			this.elementNumber=elementCount;
		}
		this.elementName = (this.uniqueIndentifer="Object " + 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.createdDate) 
				+" #"+ String.format("%010d",this.elementNumber));
		this.lastModifiedDate = new Date();
	}
	
	public Element(String elementName) {
		this.createdDate=new Date();
		if(lastElementConstruct.equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.createdDate))) {
			elementCount+=Math.random()*1000+1;
			this.elementNumber=elementCount;
		}else {
			elementCount=0;
			this.elementNumber=elementCount;
			lastElementConstruct = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		}
		this.elementName = elementName;
		this.uniqueIndentifer="Object " + 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.createdDate) 
				+" #"+ String.format("%010d",this.elementNumber);
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

	public static void setElementCount(int elementCount) {
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

	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
		updateTime();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(elementName+"\n");
		stringBuilder.append("Created @ : " + createdDate+"\t");
		stringBuilder.append("Modified @: " + lastModifiedDate+"\n");
		stringBuilder.append("Unique Identifier: " + uniqueIndentifer+"\n");
		return stringBuilder.toString();
		
		//return elementName+"\n";
	}
	
	public String toStringExtensive() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(elementName+"\n");
		stringBuilder.append("Created @ : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(createdDate)+"\t");
		stringBuilder.append("Modified @: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(lastModifiedDate)+"\n");
		stringBuilder.append("Unique Identifier: " + uniqueIndentifer+"\n");
		return stringBuilder.toString();
	}
}
