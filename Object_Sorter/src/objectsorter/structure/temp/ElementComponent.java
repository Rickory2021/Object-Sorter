package objectsorter.structure.temp;

import java.io.Serializable;

public class ElementComponent <T> extends Element implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259610508388342031L;
	private T componentInfo;
	
	public ElementComponent() {
		super();
	}
	
	public ElementComponent(T componentInfo) {
		super();
		this.componentInfo=componentInfo;
	}
	
	public ElementComponent(String elementName, T componentInfo) {
		super(elementName);
		this.componentInfo = componentInfo;
	}

	public T getComponentInfo() {
		return componentInfo;
	}

	public void setComponentInfo(T componentInfo) {
		super.updateTime();
		this.componentInfo = componentInfo;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Component: "+super.toString());
		stringBuilder.append("Info: "+componentInfo+"\n");
		return stringBuilder.toString();
	}
}
