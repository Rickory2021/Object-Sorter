package objectsorter.structure.temp.comparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.ElementObject;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectCompareType;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectInternalListSearchType;
import objectsorter.structure.temp.comparator.ElementEnum.ElementComponentListType;
import objectsorter.structure.temp.comparator.ElementEnum.OrderType;

public class ElementObjectComparator <T extends ElementObject> extends Element implements Comparator<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3142510441170739233L;
	private ElementObjectCompareType compareType;

	private OrderType orderType;
	private ArrayList<T> usedList;
	
	// Used when Internal Search
	private ElementComponentListType listType;
	private String nameSearch;
	// Used when asking for Custom Comparator
	private ElementObjectInternalListSearchType internalCompareType;
	
	public ElementObjectComparator() {
		super();
		this.compareType=ElementObjectCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType) {
		super();
		this.compareType=compareType;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementComponentListType infoListType, String infoName) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
		
		this.listType=infoListType;
		this.nameSearch=infoName;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementComponentListType infoListType, String infoName, ElementObjectInternalListSearchType internalCompareType) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
		
		this.listType=infoListType;
		this.nameSearch=infoName;
		this.internalCompareType=internalCompareType;
	}
	
	public void setCompareType(ElementObjectCompareType compareType) {
		this.compareType = compareType;
		for(ElementObject ElementObject:usedList) {
			ElementObject.updateList();
		}
		super.updateTime();
	}
	
	public int getOrderType() {
		return orderType.getIntegerRepresentation();
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
		for(ElementObject ElementComponentList:usedList) {
			ElementComponentList.updateList();
		}
		super.updateTime();
	}
	
	public ArrayList<T> getUsedList() {
		return usedList;
	}
	
	public static void removePointerFromElementObject(ElementObject elementComponentList, ElementObjectComparator<?> elementObjectComparator) {
		if(elementObjectComparator==null)return;
		int oldComparatorObjectIndex = elementObjectComparator.getUsedList().indexOf(elementComponentList);
		if(oldComparatorObjectIndex>=0) {
			// Remove from oldComparatorUsedList
			elementObjectComparator.getUsedList().remove(oldComparatorObjectIndex);
			elementObjectComparator.updateTime();
		}
	}
	
	public static <E extends ElementObject> void  addPointerToElementObject(E elementObject, ElementObjectComparator<E> elementInfoComparator) {
		if(elementInfoComparator.getUsedList().indexOf(elementObject)==-1) {
			elementInfoComparator.getUsedList().add(elementObject);
			elementInfoComparator.updateTime();
		}
	}
	
	public static <E extends ElementObject> void swapPointerFromElementComponentList(E elementObject, ElementObjectComparator<E> newElementComponentComparator) {
		removePointerFromElementObject(elementObject, elementObject.getActiveComponentComparator());
		addPointerToElementObject(elementObject,newElementComponentComparator);
	}
	
	@Override
	public int compare(T elementObject1, T elementObject2) {
		try {
			return ElementEnum.compare(elementObject1,elementObject2,compareType,orderType);
		}catch(Exception e){}
		if(compareType.equals(ElementObjectCompareType.INTERNAL_INFO)) {
			if(listType.equals(ElementComponentListType.INTEGER)) {
				ElementComponentList<Integer> 
					componentList1 = elementObject1.getIntegerList(), 
					componentList2 = elementObject2.getIntegerList(); 
				if(nameSearch==null) {
					System.out.println("CANNOT GO INTERNAL WITHOUT A NAME");
					return 0;
				}else {
					ArrayList<ElementComponent<Integer>> 
						componentListNamed1 = componentList1.getElementObjectNamedList(nameSearch),
						componentListNamed2 = componentList2.getElementObjectNamedList(nameSearch);
					if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_FIRST)) {
						ElementComponent<Integer> 
							component1 = componentListNamed1.get(0),
							component2 = componentListNamed2.get(0);
						if(componentList1.getActiveComponentComparator()==null) 
							return component1.getElementComponent().compareTo(component2.getElementComponent());
						return componentList1.getActiveComponentComparator().compare(component1, component2);
					}else if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_LAST)) {
						ElementComponent<Integer> 
							component1 = componentListNamed1.get(componentListNamed1.size()-1),
							component2 = componentListNamed2.get(componentListNamed2.size()-1);
						if(componentList1.getActiveComponentComparator()==null) return component1.getElementComponent().compareTo(component2.getElementComponent())*-1;
						return componentList1.getActiveComponentComparator().compare(component1, component2)*-1;
					}
				}
			}else if(listType.equals(ElementComponentListType.DOUBLE)) {
				ElementComponentList<Double> componentList1, componentList2; 
					componentList1 = elementObject1.getDoubleList();
					componentList2 = elementObject2.getDoubleList();
				System.out.println("NOTE: DOUBLE LIST TYPE HAS NOT BEEN SET UP");
				System.out.println("From ElementObjectComparator");
			}else if(listType.equals(ElementComponentListType.STRING)) {
				ElementComponentList<String> componentList1, componentList2; 
					componentList1 = elementObject1.getStringList();
					componentList2 = elementObject2.getStringList();
				System.out.println("NOTE: STRING LIST TYPE HAS NOT BEEN SET UP");
				System.out.println("From ElementObjectComparator");
			}else if(listType.equals(ElementComponentListType.UNKNOWN)) {
				System.out.println("CANNOT COMPARE UNKNOWN COMPONENTLIST");
				return 0;
			}
			
		}
		return 0;
	}

}
