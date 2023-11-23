package objectsorter.structure;

import java.io.Serializable;

public class ElementComponentInfo <T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2517778541264752679L;
	public T info;
	
	public ElementComponentInfo(T info) {
		this.info=info;
	}
	
	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	@Override
	public String toString() {
		if(info==null)return null;
		return info.toString();
	}
}
