package objectsorter.structure;

import java.io.Serializable;

public class ElementComponentInfoComparable<T extends Comparable<T>> extends ElementComponentInfo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4547529182059763011L;

	public ElementComponentInfoComparable(T info) {
		super(info);
	}

	@Override
	public T getInfo() {
		return super.getInfo();
	}
	
	@Override
	public void setInfo(T info) {
		super.setInfo(info);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(ElementComponentInfo<? extends Comparable<T>> componentInfo) {
		// TODO Auto-generated method stub
		return super.getInfo().compareTo((T) componentInfo.getInfo());
	}
}
