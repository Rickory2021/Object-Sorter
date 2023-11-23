package objectsorter.textanalyzer.html;

import objectsorter.structure.ElementComponent;
import objectsorter.structure.ElementComponentInfo;

public class HtmlAttribute extends ElementComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 789818923407263584L;
	public HtmlAttribute() {
		super();
	}
	
	public HtmlAttribute(String attributeName) {
		super(attributeName);
	}
	
	public HtmlAttribute(String attributeName, ElementComponentInfo<?> attributeValue) {
		super(attributeName,attributeValue);
	}
	
	public HtmlAttribute(String attributeName, String attributeValue) {
		super(attributeName,attributeValue);
	}
}
