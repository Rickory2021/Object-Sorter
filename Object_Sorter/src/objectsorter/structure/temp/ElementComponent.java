package objectsorter.structure.temp;

import java.text.SimpleDateFormat;

public class ElementComponent <T> extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259610508388342031L;
	private T elementInfo;
	
	public ElementComponent() {
		super();
		this.elementInfo=null;
	}
	
	/*	Note that previously String Data Type causes ambiguous error 
	 *  Since it doesn't know to use ElementComponent<String>(T elementInfo) (Which means T is a String Data Type)
	 *  	or ElementComponent<String>(String elementName)
	 *  
	 *  We want to always prioritize adding the elementInfo over getting an elementName;
	 *  however, due how Java chooses the most specific method, it will always prioritize assigning an elementName.
	 *  https://docs.oracle.com/javase/specs/jls/se12/html/jls-15.html#jls-15.12.2.5
	 *  
	 *  To resolve this, we need to make ElementComponent(String elementName) less specific, hence
	 *  	 <E extends String> ElementComponent(E elementName)
	 *  There is a warning since String is a final class (Meaning it can't be a superclass; 
	 *  however, we are still able to have E be a String Data Type, which will suffice
	 *  
	 *  ElementComponent(T elementInfo) will always be prioritized
	 *  
	 *  To assign a ElementComponent<String> with a name but no elementInfo,
	 *  please write in this format:
	 *  	new ElementComponent<>("Name"); // With no Generic in declaration
	 *  
	 */
	/*public ElementComponent(T elementInfo) {
		super();
		this.elementInfo=elementInfo;
	}*/
	
	public ElementComponent(T elementInfo) {
		super();
		//System.out.println("USED GENRIC");
		this.elementInfo=elementInfo;
	}
	
	public <E extends String> ElementComponent(E elementName) {
		super(elementName);
		//System.out.println("USED DEFINED");
		this.elementInfo=null;
	}
	
	public ElementComponent(String elementName, T elementInfo) {
		super(elementName);
		this.elementInfo = elementInfo;
	}

	public T getElementInfo() {
		return elementInfo;
	}

	public void setElementInfo(T elementInfo) {
		this.elementInfo = elementInfo;
		super.updateTime();
	}	
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ElementComponent Name: "+super.toString());
		stringBuilder.append("Info: "+elementInfo+"\n");
		
		return stringBuilder.toString();
	}
	
	@Override
	public String toStringExtensive() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ElementComponent Name: "+super.toString());
		stringBuilder.append("Info: "+elementInfo+"\n");
		
		return stringBuilder.toString();
	}

}
