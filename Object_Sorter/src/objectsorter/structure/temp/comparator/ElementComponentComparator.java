package objectsorter.structure.temp.comparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.ElementEnum;
import objectsorter.structure.temp.ElementEnum.ElementComponentCompareType;
import objectsorter.structure.temp.ElementEnum.OrderType;

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
			//System.out.println("UPDATED??");
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
		if(elementInfoComparator==null) return;
		if(elementInfoComparator.getUsedList().indexOf(elementComponentList)==-1) {
			elementInfoComparator.getUsedList().add(elementComponentList);
			elementInfoComparator.updateTime();
		}
	}
	
	public static <E> void swapPointerFromElementComponentList(ElementComponentList<E> ElementComponentList, ElementComponentComparator<ElementComponent<E>> newElementComponentComparator) {
		removePointerFromElementComponentList(ElementComponentList, ElementComponentList.getActiveComponentComparator());
		addPointerToElementComponentList(ElementComponentList,newElementComponentComparator);
		ElementComponentList.updateList();
	}
	
	@Override
	public int compare(T elementInfo1, T elementInfo2) {
		if(elementInfo1==null && elementInfo2!=null)return 1;
		if(elementInfo1==null && elementInfo2==null)return 0;
		if(elementInfo1!=null && elementInfo2==null)return -1;
		if(elementInfo1.getElementInfo()==null && elementInfo2.getElementInfo()!=null)return 1;
		if(elementInfo1.getElementInfo()==null && elementInfo2.getElementInfo()==null)return 0;
		if(elementInfo1.getElementInfo()!=null && elementInfo2.getElementInfo()==null)return -1;
		try {
			return ElementComparator.compare(elementInfo1,elementInfo2,compareType,orderType);
		}catch(Exception e){}
		if(compareType.equals(ElementComponentCompareType.INFO_COMPARISION)) {
			if (elementInfo1.getElementInfo().getClass().getName().equals(elementInfo2.getElementInfo().getClass().getName()) && elementInfo1.getElementInfo() instanceof Comparable && elementInfo2.getElementInfo() instanceof Comparable) {
				//System.out.println(elementInfo1.getClass().getName());
				//System.out.println(elementInfo2.getClass().getName());
				Method compareToMethod;
				try {
					compareToMethod = Comparable.class.getMethod("compareTo", Object.class);
					return (Integer) compareToMethod.invoke(elementInfo1.getElementInfo(), elementInfo2.getElementInfo())*orderType.getIntegerRepresentation();
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
				return elementInfo1.getElementInfo().getClass().getName().compareTo(elementInfo2.getElementInfo().getClass().getName());
		    }
		}
		return 0;
	}
	
	/*@Override
	public boolean equals(Object obj) {
		System.out.println(this.getElementName()+" == "+((ElementComponent<?>) obj).getElementName());
		if(obj instanceof ElementComponent<?>) {
			return this.getElementName().equals(((ElementComponent<?>) obj).getElementName());
		}
		
		return this==obj;
		
	}*/
	
	@Override
	public String toString() {
		return compareType.toString();
	}
}
