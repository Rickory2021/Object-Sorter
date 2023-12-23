package objectsorter.structure.temp.comparator;

import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementObject;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectCompareType;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectInternalListSearchType;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectListType;
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
	private ElementObjectListType listType;
	private String nameSearch;
	// Used when asking for Custom Comparator
	private ElementObjectInternalListSearchType internalCompareType;
	
	public ElementObjectComparator() {
		super();
		this.compareType=ElementObjectCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementObjectListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType) {
		super();
		this.compareType=compareType;
		this.orderType=OrderType.ASCENDING;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementObjectListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
		
		this.listType=ElementObjectListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementObjectListType infoListType, String infoName) {
		super();
		this.compareType=compareType;
		this.orderType=orderType;
		this.usedList=new ArrayList<>();
		
		this.listType=infoListType;
		this.nameSearch=infoName;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}
	
	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementObjectListType infoListType, String infoName, ElementObjectInternalListSearchType internalCompareType) {
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
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
