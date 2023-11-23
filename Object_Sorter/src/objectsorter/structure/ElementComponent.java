package objectsorter.structure;

import java.io.Serializable;

public class ElementComponent implements Comparable<ElementComponent>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 339351579624699522L;
	private String componentName;
	private ElementComponentInfo<?> componentInfo;
	public ElementComponent() {
		this.componentName=null;
		this.componentInfo= null;
	}
	
	public ElementComponent(String componentName) {
		this.componentName=componentName;
		//this.component= null;
	}
	
	public ElementComponent(String componentName, ElementComponentInfo<?> componentInfo) {
		this.componentName=componentName;
		this.componentInfo= componentInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Comparable<?>> ElementComponent(String componentName, T component) {
		this.componentName=componentName;
		this.componentInfo= new ElementComponentInfoComparable(component);
	}
	
	public <T> ElementComponent(String componentName, T component) {
		this.componentName=componentName;
		this.componentInfo= new ElementComponentInfo<T>(component);
	}
	
	

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public ElementComponentInfo<?> getcomponentInfo() {
		return componentInfo;
	}

	public void setcomponentInfo(ElementComponentInfo<?> componentInfo) {
		this.componentInfo = componentInfo;
	}

	@Override
	public int compareTo(ElementComponent o)  {
		// TODO Auto-generated method stub
		return this.getComponentName().compareTo(o.getComponentName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ElementComponent) {
			return this.getComponentName().equals(((ElementComponent) obj).getComponentName());
		}
		return this==obj;
		
	}
	
	@Override 
	public String toString(){
		return componentName + "\t[" + componentInfo+"]";
		//return componentName + "\t[componentInfo]";
	}
}


