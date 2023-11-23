package objectsorter.structure;

import java.util.ArrayList;
import java.util.Collections;

public class ElementListQoL {
	public static final int DUPLICATENAMEUNSORTED=1,DUPLICATENAMESORTED=2, UNIQUEOVERRIDE = 3, UNIQUEFINAL=4;
	
	public static <T extends Comparable<T>> T getElement(String objectName, ArrayList<T> arrayList, int addType) {
		int index = arrayList.indexOf(new ElementObject(objectName));
		//System.out.println(index);
		if (index==-1) {
			return null;
		}else {
			return arrayList.get(index);
		}
	}
	
	public static <T extends Comparable<T>> boolean addElement(T element, ArrayList<T> arrayList, int addType) {
		if(addType==DUPLICATENAMEUNSORTED) {
			arrayList.add(element);
			return true;
		}else if(addType==DUPLICATENAMESORTED) {
			arrayList.add(element);
			Collections.sort(arrayList);
			return true;
		}else if(addType==UNIQUEOVERRIDE) {
			int index = Collections.binarySearch(arrayList, element);
			if (index<0) {
				arrayList.add((-(index) - 1),element);
				return true;
			}
			arrayList.remove(index);
			arrayList.add(index,element);
			return true;
		}else if(addType==UNIQUEFINAL){
			int index = Collections.binarySearch(arrayList, element);
			if (index<0) {
				arrayList.add((-(index) - 1),element);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static <T> boolean addElement(T element, ArrayList<T> arrayList, int addType) {
		int index = arrayList.indexOf(element);
		if (index<0) {
			arrayList.add(element);
			return true;
		}else if(addType==DUPLICATENAMEUNSORTED) {
			return false;
		}else if(addType==DUPLICATENAMEUNSORTED) {
			arrayList.add(element);
			return true;
		}else if(addType==UNIQUEOVERRIDE) {
			arrayList.remove(index);
			arrayList.add(index,element);
			return true;
		}else if(addType==UNIQUEFINAL){
			return false;
		}
		return false;
	}
	
	public static <T extends Comparable<T>> boolean removeElement(T element, ArrayList<T> arrayList) {
		int index = Collections.binarySearch(arrayList, element);
		if (index==-1) {
			return false;
		}else{
			arrayList.remove(index);
			return true;
		}
	}
	
	public static <T> boolean removeElement(T element, ArrayList<T> arrayList) {
		int index = arrayList.indexOf(element);
		if (index==-1) {
			return false;
		}else{
			arrayList.remove(index);
			return true;
		}
	}
	
	public static String getTableString(ElementObject table, int textSpacing) {
		ArrayList<ElementObject> objectList = table.getObjectList();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(table.getObjectName()+"\n");
		for(ElementObject row : objectList) {
			//System.out.println(row);
			for(ElementComponent component:row.getComponentList()) {
				String strFormat = "%"+textSpacing+"s";
				stringBuilder.append(String.format(strFormat, component.getcomponentInfo().toString()));
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
