package objectsorter.structure.temp.comparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.ElementObject;
import objectsorter.structure.temp.comparator.comparerule.ElementComponentCompareRule;
import objectsorter.structure.temp.comparator.comparerule.ElementObjectCompareRule;
import objectsorter.structure.temp.comparator.comparerule.ElementObjectCompareRule.ElementComponentListType;
import objectsorter.structure.temp.comparator.comparerule.ElementObjectCompareRule.ElementObjectCompareType;
import objectsorter.structure.temp.comparator.comparerule.ElementObjectCompareRule.ElementObjectInternalListSearchType;
import objectsorter.structure.temp.comparator.comparerule.CompareRule.OrderType;
import objectsorter.structure.temp.comparator.comparerule.ElementComponentCompareRule.ElementComponentCompareType;

public class ElementObjectComparator <T extends ElementObject> extends Element implements Comparator<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3142510441170739233L;
	private ArrayList<ElementObjectCompareRule> ruleList;
	private ArrayList<T> usedList;

	// Used when Internal Search
	private ElementComponentListType listType;
	private String nameSearch;
	// Used when asking for Custom Comparator
	private ElementObjectInternalListSearchType internalCompareType;

	public ElementObjectComparator() {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementObjectCompareRule(ElementObjectCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();

		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}

	public ElementObjectComparator(ElementObjectCompareType compareType) {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementObjectCompareRule(ElementObjectCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();

		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}

	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType) {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementObjectCompareRule(ElementObjectCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();

		this.listType=ElementComponentListType.UNKNOWN;
		this.nameSearch=null;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}

	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementComponentListType infoListType, String infoName) {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementObjectCompareRule(ElementObjectCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();

		this.listType=infoListType;
		this.nameSearch=infoName;
		this.internalCompareType=ElementObjectInternalListSearchType.NULL;
	}

	public ElementObjectComparator(ElementObjectCompareType compareType, OrderType orderType, ElementComponentListType infoListType, String infoName, ElementObjectInternalListSearchType internalCompareType) {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementObjectCompareRule(ElementObjectCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();

		this.listType=infoListType;
		this.nameSearch=infoName;
		this.internalCompareType=internalCompareType;
	}

	public void setRuleList(ArrayList<ElementObjectCompareRule> ruleList) {
		this.ruleList = ruleList;
		for(ElementObject ElementObject:usedList) {
			ElementObject.updateList();
		}
		super.updateTime();
	}

	public ArrayList<ElementObjectCompareRule> getRuleList() {
		return this.ruleList;
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
		for(ElementObjectCompareRule rule : ruleList) {
			if(elementObject1==null && elementObject2!=null)return 1;
			if(elementObject1==null && elementObject2==null)continue;
			if(elementObject1!=null && elementObject2==null)return -1;
			try {
				int result = ElementComparatorHelper.compareElementRule(elementObject1,elementObject2,rule.getCompareType(),rule.getOrderType());
				if(result!=0)return result;
			}catch(Exception e){}
			if(rule.getCompareType().equals(ElementObjectCompareType.INTERNAL_INFO)) {
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

						if(componentListNamed1==null && componentListNamed2!=null)return 1;
						if(componentListNamed1==null && componentListNamed2==null)return 0;
						if(componentListNamed1!=null && componentListNamed2==null)return -1;
						if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_FIRST)) {
							ElementComponent<Integer> 
							component1 = componentListNamed1.get(0),
							component2 = componentListNamed2.get(0);
							if(componentList1.getActiveComponentComparator()==null) 
								return component1.getElementInfo().compareTo(component2.getElementInfo());
							return componentList1.getActiveComponentComparator().compare(component1, component2);
						}else if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_LAST)) {
							ElementComponent<Integer> 
							component1 = componentListNamed1.get(componentListNamed1.size()-1),
							component2 = componentListNamed2.get(componentListNamed2.size()-1);
							if(componentList1.getActiveComponentComparator()==null) return component1.getElementInfo().compareTo(component2.getElementInfo())*-1;
							return componentList1.getActiveComponentComparator().compare(component1, component2)*-1;
						}
					}
				}else if(listType.equals(ElementComponentListType.DOUBLE)) {
					ElementComponentList<Double> 
					componentList1 = elementObject1.getDoubleList(), 
					componentList2 = elementObject2.getDoubleList(); 

					if(nameSearch==null) {
						System.out.println("CANNOT GO INTERNAL WITHOUT A NAME");
						return 0;
					}else {
						ArrayList<ElementComponent<Double>> 
						componentListNamed1 = componentList1.getElementObjectNamedList(nameSearch),
						componentListNamed2 = componentList2.getElementObjectNamedList(nameSearch);

						if(componentListNamed1==null && componentListNamed2!=null)return 1;
						if(componentListNamed1==null && componentListNamed2==null)return 0;
						if(componentListNamed1!=null && componentListNamed2==null)return -1;
						if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_FIRST)) {
							ElementComponent<Double> 
							component1 = componentListNamed1.get(0),
							component2 = componentListNamed2.get(0);

							if(componentList1.getActiveComponentComparator()==null) 
								return component1.getElementInfo().compareTo(component2.getElementInfo());
							return componentList1.getActiveComponentComparator().compare(component1, component2);
						}else if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_LAST)) {
							ElementComponent<Double> 
							component1 = componentListNamed1.get(componentListNamed1.size()-1),
							component2 = componentListNamed2.get(componentListNamed2.size()-1);

							if(componentList1.getActiveComponentComparator()==null) return component1.getElementInfo().compareTo(component2.getElementInfo())*-1;
							return componentList1.getActiveComponentComparator().compare(component1, component2)*-1;
						}
					}
				}else if(listType.equals(ElementComponentListType.STRING)) {
					ElementComponentList<String> 
					componentList1 = elementObject1.getStringList(), 
					componentList2 = elementObject2.getStringList(); 

					if(nameSearch==null) {
						System.out.println("CANNOT GO INTERNAL WITHOUT A NAME");
						return 0;
					}else {
						ArrayList<ElementComponent<String>> 
						componentListNamed1 = componentList1.getElementObjectNamedList(nameSearch),
						componentListNamed2 = componentList2.getElementObjectNamedList(nameSearch);

						if(componentListNamed1==null && componentListNamed2!=null)return 1;
						if(componentListNamed1==null && componentListNamed2==null)return 0;
						if(componentListNamed1!=null && componentListNamed2==null)return -1;
						if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_FIRST)) {
							ElementComponent<String> 
							component1 = componentListNamed1.get(0),
							component2 = componentListNamed2.get(0);

							if(componentList1.getActiveComponentComparator()==null) 
								return component1.getElementInfo().compareTo(component2.getElementInfo());
							return componentList1.getActiveComponentComparator().compare(component1, component2);
						}else if(internalCompareType.equals(ElementObjectInternalListSearchType.DEFAULT_COMPARATOR_LAST)) {
							ElementComponent<String> 
							component1 = componentListNamed1.get(componentListNamed1.size()-1),
							component2 = componentListNamed2.get(componentListNamed2.size()-1);

							if(componentList1.getActiveComponentComparator()==null) return component1.getElementInfo().compareTo(component2.getElementInfo())*-1;
							return componentList1.getActiveComponentComparator().compare(component1, component2)*-1;
						}
					}
				}else if(listType.equals(ElementComponentListType.UNKNOWN)) {
					System.out.println("CANNOT COMPARE UNKNOWN COMPONENTLIST");
					return 0;
				}
			}
		}
		return 0;
	}

}
