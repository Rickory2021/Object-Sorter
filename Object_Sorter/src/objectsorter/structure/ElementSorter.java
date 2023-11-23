package objectsorter.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ElementSorter implements Comparator<ElementObject>, Comparable<ElementSorter>{
	private String elementSorterName;
	private ArrayList<ElementSorterPrecedenceType> orderOfPrecedence;
	
	public ElementSorter() {
		this.elementSorterName=null;
		this.orderOfPrecedence=new ArrayList<>();
	}
	
	public ElementSorter(String elementSorterName) {
		this.elementSorterName=elementSorterName;
		this.orderOfPrecedence=new ArrayList<>();
	}
	
	public ElementSorter(String elementSorterName, ArrayList<ElementSorterPrecedenceType> orderOfPrecedence) {
		this.elementSorterName=elementSorterName;
		this.orderOfPrecedence=orderOfPrecedence;
	}

	public String getElementSorterName() {
		return elementSorterName;
	}

	public void setElementSorterName(String elementSorterName) {
		this.elementSorterName = elementSorterName;
	}

	public ArrayList<ElementSorterPrecedenceType> getOrderOfPrecedence() {
		return orderOfPrecedence;
	}

	public void setOrderOfPrecedence(ArrayList<ElementSorterPrecedenceType> orderOfPrecedence) {
		this.orderOfPrecedence = orderOfPrecedence;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public int compare(ElementObject o1, ElementObject o2) {
		ElementComponent temp = new ElementComponent();
		for(ElementSorterPrecedenceType precedence : orderOfPrecedence) {
			int multiple = (precedence.isAscending)?1:-1, value;
			temp.setComponentName(precedence.componentName);
			int precedenceIndex1 = Collections.binarySearch(o1.getComponentList(),temp),
					precedenceIndex2 = Collections.binarySearch(o2.getComponentList(),temp);
			if(precedenceIndex1==precedenceIndex2) {
				// Both elements are missing
				if(precedenceIndex1==-1)continue;
				ElementComponentInfo info1 = o1.getComponentList().get(precedenceIndex1).getcomponentInfo(),
						info2 = o2.getComponentList().get(precedenceIndex2).getcomponentInfo();
				if(info1 instanceof ElementComponentInfoComparable && info2 instanceof ElementComponentInfoComparable) {
					value = ((ElementComponentInfoComparable)info1).compareTo((ElementComponentInfoComparable)info2);
					if(value == 0) continue;
					return value*multiple;
				}else {
					continue;
				}
				
			}else {
				// One of the element is missing
				return ((precedenceIndex1==-1)?-1:1)*multiple;
			}
		}
		return 0;
	}

	@Override
	public int compareTo(ElementSorter o) {
		// TODO Auto-generated method stub
		return this.elementSorterName.compareTo(o.getElementSorterName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ElementSorter) {
			return this.elementSorterName.equals(((ElementSorter) obj).getElementSorterName());
		}
		return this==obj;
	}
	
	@Override 
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Sorter:\n");
		strBuilder.append(String.format("%s\n",elementSorterName));
		for(ElementSorterPrecedenceType orderOfPrecedence: orderOfPrecedence) {
			strBuilder.append(String.format("\t%s\n",orderOfPrecedence));
		}
		return strBuilder.toString();
	}
}
