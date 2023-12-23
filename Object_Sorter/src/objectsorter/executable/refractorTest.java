package objectsorter.executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import objectsorter.structure.temp.*;
import objectsorter.structure.temp.comparator.*;
//import objectsorter.structure.temp.comparator.ElementEnum.ElementCompareType;
import objectsorter.structure.temp.comparator.ElementEnum.ElementObjectCompareType;


public class refractorTest {
	public static void main (String[] args) {
		ElementComponent<Integer> info1 = new ElementComponent<Integer>(1);
		System.out.println("Info 1");
		System.out.println(info1);
		
		ElementComponent<Integer> info2 = new ElementComponent<Integer>(5);
		ElementComponent<ElementComponent<Integer>> info3 = new ElementComponent<ElementComponent<Integer>>(info2);
		System.out.println("Info 3");
		System.out.println(info3);
		
		ElementComponentList info4 = new ElementComponentList();
		System.out.println("Info 4");
		System.out.println(info4);
		
		ElementComponentList<Integer> info5 = new ElementComponentList<Integer>();
		info5.addData(5);
		System.out.println("Info 5");
		System.out.println(info5);
		
		//System.out.println(Collections.binarySearch(info5.getElementList(),new ElementComponent<Integer>(5),null));
		//ElementComparator<ElementComponent<Integer>> e = new ElementComparator<ElementComponent<Integer>>(ElementCompareType.CREATED_DATE);
		//ElementEnum.ElementCompareType type = ElementEnum.ElementCompareType.CREATED_DATE;
		//type.getIntegerRepresentation();
		
		ElementComponentComparator<ElementComponent<Integer>> eInfo = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementEnum.ElementComponentCompareType.INFO_COMPARISION);
		Class<?>[] interfaceArr = eInfo.getClass().getInterfaces();
		for(Class<?> cls:interfaceArr) {
			System.out.println(cls.toString());
		}
		
		System.out.println("TREE INPUT");
		ElementComponentList<ElementComponentList<?>> tree = new ElementComponentList<ElementComponentList<?>>("Tree");
		ElementComponentList<Integer> node1 =  new ElementComponentList<Integer>("ATicket");
		System.out.println(node1.addData("Day1", 10));
		node1.addData("Day2",144);
		System.out.println(node1);
		tree.addData("S&S",node1);
		ElementComponentList<String> node2 =  new ElementComponentList<String>("Employee Name");
		node2.addData("Manager","Ricky");
		node2.addData("Head Chef","Mark");
		tree.addData("S&S",node2);
		ElementComponentList<Integer> node3 =  new ElementComponentList<Integer>("Minute");
		node3.addData("Day1", 10000);
		node3.addData("Day2",3454);
		tree.addData("S&S",node3);
		System.out.println("TREE PRINT");
		System.out.println(tree);
		System.out.println(tree.getElementList().get(0).getElementComponent().getElementList().get(0).getElementComponent().getClass());
		ElementComponentComparator<ElementComponent<Integer>> infoComparator = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementEnum.ElementComponentCompareType.INFO_COMPARISION);
		
		ElementComponentList<?> tranverseNode1 = tree.getElementList().get(0).getElementComponent();
		ElementComponentList<?> tranverseNode2 = tree.getElementList().get(1).getElementComponent();
		ElementComponentList<?> tranverseNode3 = tree.getElementList().get(2).getElementComponent();
		System.out.println(node1.getElementName());
		System.out.println(node2.getElementName());
		//System.out.println(objComparator.compare(node1, node2));
		System.out.println("Tranversed Retrival");
		System.out.println(tranverseNode1.getElementName());
		System.out.println(tranverseNode3.getElementName());
		System.out.println("Result of Name of Object");
		//System.out.println(objComparator.compare(tranverseNode1, tranverseNode3));
		System.out.println("Result of Info Comapre");
		System.out.println((ElementComponent<Integer>)tranverseNode1.getElementList().get(0));
		System.out.println((ElementComponent<Integer>)tranverseNode2.getElementList().get(0));
		System.out.println((ElementComponent<Integer>)tranverseNode3.getElementList().get(0));
		System.out.println(infoComparator.compare((ElementComponent<Integer>)tranverseNode1.getElementList().get(0),(ElementComponent<Integer>)tranverseNode3.getElementList().get(0)));
		//Class<T> persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Integer typetest =1;
		Class unknown = Integer.class;
		//ArrayList<Integer.class> testy;
		//Class<unknown> unknownObject;
		
		System.out.println("ToString");
		ElementComponent<Integer> infoTest1 = new ElementComponent<Integer>(5);
		ElementComponent<Integer> infoTest2 = new ElementComponent<Integer>(3);
		ElementComponent<Double> infoTest3 = new ElementComponent<Double>(2.0);
		ElementComponent<Double> infoTest4 = new ElementComponent<Double>(3.0);
		ElementComponent<String> infoTest5 = new ElementComponent<String>("A");
		ElementComponent<String> infoTest6 = new ElementComponent<String>("B");
		System.out.println(eInfo.compare(infoTest1, infoTest2));
		//System.out.println(eInfo);
		//ElementComponentComparator.
		
		System.out.println("ElementComponentComparator");
		ElementComponentComparator<ElementComponent<Integer>> nameInfo = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementEnum.ElementComponentCompareType.NAME);
		ElementComponentComparator<ElementComponent<Integer>> intInfo = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementEnum.ElementComponentCompareType.INFO_COMPARISION);
		ElementComponentComparator<ElementComponent<Double>> doubleInfo = 
				new ElementComponentComparator<ElementComponent<Double>>(ElementEnum.ElementComponentCompareType.INFO_COMPARISION);
		ElementComponentComparator<ElementComponent<String>> strInfo = 
				new ElementComponentComparator<ElementComponent<String>>(ElementEnum.ElementComponentCompareType.INFO_COMPARISION);
		System.out.println(intInfo.compare(infoTest1, infoTest2));
		System.out.println(doubleInfo.compare(infoTest4, infoTest3));
		System.out.println(strInfo.compare(infoTest5, infoTest6));
		System.out.println(eInfo.compare(infoTest1, infoTest1));
		System.out.println(eInfo.compare(infoTest1, infoTest1));
		
		ElementComponentList<Integer> sortedInteger1 = new ElementComponentList<Integer>("Sorted Integer SingleObject 1");
		sortedInteger1.setStructType(null);
		ElementComponentList<Integer> sortedInteger2 = new ElementComponentList<Integer>("Sorted Integer SingleObject 2");
		sortedInteger1.setActiveComponentComparator(intInfo, false);
		sortedInteger2.setActiveComponentComparator(intInfo, false);
		sortedInteger1.addData("Number 1",5);
		sortedInteger1.addData("Number 2",3);
		sortedInteger1.addData("Number 3",6);
		sortedInteger1.addData("Number 4",0);
		sortedInteger1.addData("Number 5",10);
		sortedInteger2.addData("Number 06",5);
		sortedInteger2.addData("Number 07",3);
		sortedInteger2.addData("Number 08",6);
		sortedInteger2.addData("Number 09",0);
		sortedInteger2.addData("Number 10",10);
		
		System.out.println(sortedInteger1);
		System.out.println(sortedInteger2);
		intInfo.setOrderType(ElementEnum.OrderType.DESCENDING);
		//sortedInteger.updateList();
		System.out.println(sortedInteger1);
		System.out.println(sortedInteger2);
		intInfo.setCompareType(ElementEnum.ElementComponentCompareType.NAME);
		intInfo.setOrderType(ElementEnum.OrderType.ASCENDING);
		System.out.println(sortedInteger1);
		System.out.println(sortedInteger2);
		
		System.out.println(new Integer(5).compareTo(6));

	}
}
