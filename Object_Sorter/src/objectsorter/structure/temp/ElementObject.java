package objectsorter.structure.temp;

import java.util.ArrayList;
import java.util.Collections;

import objectsorter.structure.temp.comparator.ElementEnum;
import objectsorter.structure.temp.comparator.ElementObjectComparator;

public class ElementObject extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407672074280243099L;
	private ElementEnum.StructureType structType;
	// The Tree Structure
	private ArrayList<ElementObject> objectList;
	private ElementObjectComparator<ElementObject> activeObjectComparator;
	
	// The Data of the Nodes
	private ElementComponentList<Integer> integerList;
	private ElementComponentList<Double> doubleList;
	private ElementComponentList<String> stringList;
	private ElementComponentList<?> unknownList;
	
	public ElementObjectComparator<ElementObject> getActiveComponentComparator() {
		return activeObjectComparator;
	}
	
	
	public void updateList() {
		if(activeObjectComparator != null) {
			Collections.sort(this.objectList, activeObjectComparator);
			super.updateTime();
		}
		
	}
}
