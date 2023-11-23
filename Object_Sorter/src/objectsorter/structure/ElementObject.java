package objectsorter.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ElementObject implements Comparable<ElementObject>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -24281506223584869L;
	private String objectName;
	private ArrayList<ElementObject> objectList;
	private ArrayList<ElementComponent> componentList;
	private int objectArrayListSortType;

	public ElementObject(){
		this.objectName = null;
		this.objectList = new ArrayList<ElementObject>();
		this.componentList = new ArrayList<ElementComponent>();
		
	}
	
	public ElementObject(String objectName){
		this.objectName = objectName;
		this.objectList = new ArrayList<ElementObject>();
		this.componentList = new ArrayList<ElementComponent>();
	}
	
	public ElementObject(ArrayList<ElementComponent> componentList){
		this.objectName = null;
		this.objectList = new ArrayList<ElementObject>();
		this.componentList = componentList;
	}
	
	public ElementObject(String objectName, ArrayList<ElementComponent> componentList){
		this.objectName = objectName;
		this.objectList = new ArrayList<ElementObject>();
		this.componentList = componentList;
	}
	
	public ElementObject(String objectName, ArrayList<ElementObject> objectList, ArrayList<ElementComponent> componentList){
		this.objectName = objectName;
		this.objectList = objectList;
		this.componentList = componentList;
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public ArrayList<ElementObject> getObjectList() {
		return objectList;
	}
	
	public ElementObject getLastObject() {
		return objectList.get(objectList.size()-1);
	}
	
	public ElementObject getObject(String objectName, int addType) {
		return ElementListQoL.getElement(objectName, this.objectList, addType);
	}
	
	public ArrayList<ElementObject> getObjectList(String objectName) {
		ArrayList<ElementObject> tempList = new ArrayList<>();
		for(ElementObject obj: objectList) {
			if(obj.objectName.equals(objectName))tempList.add(obj);
		}
		return tempList;
	}
	
	public static ArrayList<ElementObject> getObjectList(ArrayList<ElementObject> objectList, String objectName) {
		ArrayList<ElementObject> tempList = new ArrayList<>();
		for(ElementObject obj: objectList) {
			if(obj.objectName.equals(objectName))tempList.add(obj);
		}
		return tempList;
	}
	
	public boolean addObject(ElementObject object ,int addType) {
		return ElementListQoL.addElement(object, this.objectList, addType);
		/*int index = Collections.binarySearch(objectList, object);
		if (index<0) {
			this.objectList.add((-(index) - 1),object);
			return true;
		}else if(canOverride) {
			this.objectList.remove(index);
			this.objectList.add(index,object);
			return true;
		}else {
			return false;
		}*/
	}
	
	public ArrayList<ElementObject> getObjectGroup(String[] objectNameList, int addType){
		ArrayList<ElementObject> tempObjectList = (ArrayList<ElementObject>) objectList.clone();
		//System.out.println(tempObjectList.get(1));
		/*for(ElementObject obj:tempObjectList) {
			System.out.println(">?"+obj.getObjectName());
		}
		System.out.println();*/
		for(String objectNameRequirement:objectNameList) {
			tempObjectList=getObjectList(tempObjectList,objectNameRequirement);;
			for(ElementObject obj:tempObjectList) {
				//System.out.println(">"+obj.getObjectName());
			}
			ArrayList<ElementObject> innerElementObject = new ArrayList<>();
			for(int i = 0;i<tempObjectList.size();i++) {
				innerElementObject.addAll(tempObjectList.get(i).getObjectList());
			}
			tempObjectList=innerElementObject;
		}
		
		/*for(ElementObject obj:tempObjectList) {
			System.out.println(obj);
		}System.out.println(">>"+tempObjectList.size()/16);*/
		return tempObjectList;
		
	}
	
	public ArrayList<ElementObject> getObjectGroupRecursive(ArrayList<ElementObject> objectList,String objectName){
		ArrayList<ElementObject> tempObjectList = new ArrayList<>();
		for(int i = 0;i<objectList.size();i++) {
			if(objectList.get(i).getObjectName().equals(objectName)) {
				tempObjectList.add(objectList.get(i));
				//System.out.println(">>"+objectList.get(i).getObjectName());
				
			}
		}
		for(ElementObject obj:tempObjectList) {
			//System.out.println(obj.getObjectName());
		}
		return tempObjectList;
	}
	
	public boolean removeObject(String objectName) {
		return ElementListQoL.removeElement(new ElementObject(objectName), this.objectList);
		/*int index = objectList.indexOf(new ElementObject(objectName));
		if (index==-1) {
			return false;
		}else{
			this.objectList.remove(index);
			return true;
		}*/
	}

	public void setObjectList(ArrayList<ElementObject> objectList) {
		this.objectList = objectList;
		Collections.sort(objectList);
	}

	public ArrayList<ElementComponent> getComponentList() {
		return componentList;
	}
	
	public ElementComponent getComponent(String componentName) {
		int index = componentList.indexOf(new ElementComponent(componentName));
		if (index==-1) {
			return null;
		}else {
			return componentList.get(index);
		}
	}
	
	public boolean addComponent(ElementComponent component ,int addType) {
		return ElementListQoL.addElement(component, this.componentList, addType);
		/*int index = Collections.binarySearch(componentList, component);
		if (index<0) {
			this.componentList.add((-(index) - 1),component);
			return true;
		}else if(canOverride) {
			this.componentList.remove(index);
			this.componentList.add(index,component);
			return true;
		}else {
			return false;
		}*/
	}
	
	public <T> boolean addComponent(String componentName, T componentInfo ,int addType) {
		return ElementListQoL.addElement(new ElementComponent(componentName, componentInfo), this.componentList, addType);
		/*int index = Collections.binarySearch(componentList, new ElementComponent(componentName));
		if (index==-1) {
			this.componentList.add(new ElementComponent(componentName, componentInfo));
			return true;
		}else if(canOverride) {
			this.componentList.remove(index);
			this.componentList.add(index,new ElementComponent(componentName, componentInfo));
			return true;
		}else {
			return false;
		}*/
	}
	
	public boolean removeComponent(String componentName) {
		return ElementListQoL.removeElement(new ElementComponent(componentName), this.componentList);
		/*int index = componentList.indexOf(new ElementComponent(componentName));
		if (index==-1) {
			return false;
		}else{
			this.componentList.remove(index);
			return true;
		}*/
	}

	public void setComponentList(ArrayList<ElementComponent> componentList) {
		this.componentList = componentList;
		Collections.sort(componentList);
	}

	@Override
	public int compareTo(ElementObject o)  {
		// TODO Auto-generated method stub
		return this.objectName.compareTo(o.getObjectName());
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println(this.objectName+" == "+((ElementObject) obj).getObjectName());
		if(obj instanceof ElementObject) {
			return this.objectName.equals(((ElementObject) obj).getObjectName());
		}
		return this==obj;
		
	}

	@Override 
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Object Name: "+objectName+"\n");
		strBuilder.append("Components List:\n");
		for(ElementComponent component: componentList) {
			strBuilder.append(component+"\n");
		}
		strBuilder.append("\nObject List:\n");
		for(ElementObject element: objectList) {
			strBuilder.append("Object Origin: "+objectName+">>"+element+"\n\n");
		}
		return strBuilder.toString();
	}
}
