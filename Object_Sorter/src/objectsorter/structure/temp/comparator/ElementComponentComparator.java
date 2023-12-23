package objectsorter.structure.temp.comparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.comparator.ElementEnum.ElementComponentCompareType;
import objectsorter.structure.temp.comparator.ElementEnum.OrderType;

public class ElementComponentComparator <T extends ElementComponent<?>> extends Element implements Comparator<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1221930417558413095L;
	private ElementComponentCompareType compareType;
	private OrderType orderType;
	private ArrayList<ElementComponentList<?>> usedList;
	
	public ElementComponentComparator() {
		super();
		this.compareType=ElementComponentCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
	}
	
	public ElementComponentComparator(ElementComponentCompareType compareType) {
		super();
		this.compareType=compareType;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
	}
	
	public ElementComponentComparator(ElementComponentCompareType compareType, OrderType orderType) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
	}
	
	/*public void setCompareType(ElementCompareType compareType) {
		switch(compareType) {
		case NAME:
			this.compareType = ElementComponentCompareType.NAME;
			return;
		case CREATED_DATE:
			this.compareType = ElementComponentCompareType.CREATED_DATE;
			return;
		case MODIFIED_DATE:
			this.compareType = ElementComponentCompareType.MODIFIED_DATE;
			return;
		case UNIQUE_IDENTIFER:
			this.compareType = ElementComponentCompareType.UNIQUE_IDENTIFER;
			return;
		}
	}*/

	public void setCompareType(ElementComponentCompareType compareType) {
		this.compareType = compareType;
		for(ElementComponentList<?> ElementComponentList:usedList) {
			ElementComponentList.updateList();
		}
		super.updateTime();
	}
	
	public int getOrderType() {
		return orderType.getIntegerRepresentation();
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
		for(ElementComponentList<?> ElementComponentList:usedList) {
			ElementComponentList.updateList();
		}
		super.updateTime();
	}
	
	public ArrayList<ElementComponentList<?>> getUsedList() {
		return usedList;
	}
	
	public static void removePointerFromElementComponentList(ElementComponentList<?> elementComponentList, ElementComponentComparator<?> elementInfoComparator) {
		if(elementInfoComparator==null)return;
		int oldComparatorObjectIndex = elementInfoComparator.getUsedList().indexOf(elementComponentList);
		if(oldComparatorObjectIndex>=0) {
			// Remove from oldComparatorUsedList
			elementInfoComparator.getUsedList().remove(oldComparatorObjectIndex);
			elementInfoComparator.updateTime();
		}
	}
	
	public static <E> void addPointerToElementComponentList(ElementComponentList<E> elementComponentList, ElementComponentComparator<ElementComponent<E>> elementInfoComparator) {
		if(elementInfoComparator.getUsedList().indexOf(elementComponentList)==-1) {
			elementInfoComparator.getUsedList().add(elementComponentList);
			elementInfoComparator.updateTime();
		}
	}
	
	public static <E> void swapPointerFromElementComponentList(ElementComponentList<E> ElementComponentList, ElementComponentComparator<ElementComponent<E>> newElementComponentComparator) {
		removePointerFromElementComponentList(ElementComponentList, ElementComponentList.getActiveComponentComparator());
		addPointerToElementComponentList(ElementComponentList,newElementComponentComparator);
	}
	
	@Override
	public int compare(T elementInfo1, T elementInfo2) {
		try {
			return ElementEnum.compare(elementInfo1,elementInfo2,compareType,orderType);
		}catch(Exception e){}
		if(compareType.equals(ElementComponentCompareType.INFO_COMPARISION)) {
			if (elementInfo1.getElementComponent() instanceof Comparable && elementInfo2.getElementComponent() instanceof Comparable) {
				Method compareToMethod;
				try {
					compareToMethod = Comparable.class.getMethod("compareTo", Object.class);
					return (Integer) compareToMethod.invoke(elementInfo1.getElementComponent(), elementInfo2.getElementComponent())*orderType.getIntegerRepresentation();
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
		    	   System.out.println("Can't Compare");
		    }
		}
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println(this.getElementName()+" == "+((ElementComponent<?>) obj).getElementName());
		if(obj instanceof ElementComponent<?>) {
			return this.getElementName().equals(((ElementComponent<?>) obj).getElementName());
		}
		return this==obj;
		
	}
	
	@Override
	public String toString() {
		return compareType.toString();
	}
}
