package objectsorter.structure.temp;

public class ElementComponent <T> extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259610508388342031L;
	private T elementInfo;
	
	public ElementComponent() {
		super();
	}
	
	public ElementComponent(T elementInfo) {
		super();
		this.elementInfo=elementInfo;
	}
	
	public ElementComponent(String elementName, T elementInfo) {
		super(elementName);
		this.elementInfo = elementInfo;
	}

	public T getElementComponent() {
		return elementInfo;
	}

	public void setComponentInfo(T elementInfo) {
		super.updateTime();
		this.elementInfo = elementInfo;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ElementComponent Name: "+super.toString());
		stringBuilder.append("Info: "+elementInfo+"\n");
		
		return stringBuilder.toString();
	}

}
