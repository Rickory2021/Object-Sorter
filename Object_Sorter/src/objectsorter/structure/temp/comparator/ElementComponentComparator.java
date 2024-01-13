package objectsorter.structure.temp.comparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.comparator.comparerule.CompareRule.OrderType;
import objectsorter.structure.temp.comparator.comparerule.ElementComponentCompareRule;
import objectsorter.structure.temp.comparator.comparerule.ElementComponentCompareRule.ElementComponentCompareType;

public class ElementComponentComparator <T extends ElementComponent<?>> extends Element implements Comparator<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1221930417558413095L;
	private ArrayList<ElementComponentCompareRule> ruleList;
	private ArrayList<ElementComponentList<?>> usedList;

	public ElementComponentComparator() {
		super();
		this.ruleList = new ArrayList<>();
		ruleList.add(new ElementComponentCompareRule(ElementComponentCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();
	}

	public ElementComponentComparator(ElementComponentCompareType compareType) {
		super();
		ruleList.add(new ElementComponentCompareRule(ElementComponentCompareType.NAME,OrderType.ASCENDING));
		this.usedList=new ArrayList<>();
	}

	public ElementComponentComparator(ElementComponentCompareType compareType, OrderType orderType) {
		super();
		ruleList.add(new ElementComponentCompareRule(ElementComponentCompareType.NAME,OrderType.ASCENDING));
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

	public void setRuleList(ArrayList<ElementComponentCompareRule> ruleList) {
		this.ruleList = ruleList;
		for(ElementComponentList<?> ElementComponentList:usedList) {
			ElementComponentList.updateList();
			//System.out.println("UPDATED??");
		}
		super.updateTime();
	}

	public ArrayList<ElementComponentCompareRule> getRuleList(){
		return ruleList;
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
		for(ElementComponentCompareRule rule : ruleList) {
			if(elementInfo1==null && elementInfo2!=null)return 1;
			if(elementInfo1==null && elementInfo2==null)continue;
			if(elementInfo1!=null && elementInfo2==null)return -1;
			try {
				int result = ElementComparatorHelper.compareElementRule(elementInfo1,elementInfo2,rule.getCompareType(),rule.getOrderType());
				if(result!=0)return result;
			}catch(Exception e){}
			if(rule.getCompareType().equals(ElementComponentCompareType.INFO_COMPARISION)) {
				if(elementInfo1.getElementInfo()==null && elementInfo2.getElementInfo()!=null)return 1;
				if(elementInfo1.getElementInfo()==null && elementInfo2.getElementInfo()==null)continue;
				if(elementInfo1.getElementInfo()!=null && elementInfo2.getElementInfo()==null)return -1;
				if (elementInfo1.getElementInfo().getClass().getName().equals(elementInfo2.getElementInfo().getClass().getName()) && elementInfo1.getElementInfo() instanceof Comparable && elementInfo2.getElementInfo() instanceof Comparable) {
					//System.out.println(elementInfo1.getClass().getName());
					//System.out.println(elementInfo2.getClass().getName());
					Method compareToMethod;
					try {
						compareToMethod = Comparable.class.getMethod("compareTo", Object.class);
						return (Integer) compareToMethod.invoke(elementInfo1.getElementInfo(), elementInfo2.getElementInfo())*rule.getOrderType().getIntegerRepresentation();
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}else{
					int result = elementInfo1.getElementInfo().getClass().getName().compareTo(elementInfo2.getElementInfo().getClass().getName());
					if(result!=0)return result;
				}
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
}
