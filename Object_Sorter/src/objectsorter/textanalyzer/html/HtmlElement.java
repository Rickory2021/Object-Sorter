package objectsorter.textanalyzer.html;

import java.util.ArrayList;

import objectsorter.structure.ElementComponent;
import objectsorter.structure.ElementListQoL;
import objectsorter.structure.ElementObject;

public class HtmlElement extends ElementObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 163515340224825108L;
	/*
	private HtmlTag tag; >>> ComponentList
	private ArrayList<HtmlAttribute> attributeList; >>> ComponentList
	private ArrayList<HtmlElement> elementList; >>> ObjectList
	*/
	
	public HtmlElement(){
		super();
	}
	
	public HtmlElement(String htmlName){
		super(htmlName);
	}
	
	public HtmlElement(ArrayList<ElementComponent> attributeList){
		super(attributeList);
	}
	
	public HtmlElement(String htmlName, ArrayList<ElementComponent> attributeList){
		super(htmlName, attributeList);
	}
	
	public HtmlElement(String htmlName, ArrayList<ElementObject> elementList, ArrayList<ElementComponent> attributeList){
		super(htmlName, elementList, attributeList);
	}
	
	public HtmlElement(HtmlTag tag){
		super(tag.getTagName());
		super.addComponent("Tag", tag.getTagName(),ElementListQoL.DUPLICATENAMEUNSORTED);
	}
	
	public HtmlElement(String htmlName, HtmlTag tag){
		super(htmlName);
		super.addComponent("Tag", tag.getTagName(),ElementListQoL.DUPLICATENAMEUNSORTED);
	}
	
	public boolean addHtmlElement(HtmlElement htmlElement) {
		return super.addObject(htmlElement, ElementListQoL.DUPLICATENAMEUNSORTED);
	}
	
	public boolean addHtmlAttribute(HtmlAttribute htmlAttribute) {
		return super.addComponent(htmlAttribute, ElementListQoL.DUPLICATENAMEUNSORTED);
	}
}
