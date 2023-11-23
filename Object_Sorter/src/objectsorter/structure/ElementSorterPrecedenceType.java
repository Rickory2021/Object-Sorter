package objectsorter.structure;

import java.io.Serializable;

public class ElementSorterPrecedenceType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8342222712478425826L;
	public String componentName;
	public boolean isAscending;
	
	public ElementSorterPrecedenceType() {
		this.componentName=null;
		this.isAscending=true;
	}
	
	public ElementSorterPrecedenceType(String componentName, boolean isAscending) {
		this.componentName=componentName;
		this.isAscending=isAscending;
	}
	
	@Override 
	public String toString(){
		return componentName + "\tAscending: " + isAscending;
	}
}
