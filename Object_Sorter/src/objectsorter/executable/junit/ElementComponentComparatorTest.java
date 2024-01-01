package objectsorter.executable.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;
import objectsorter.structure.temp.ElementComponentList;
import objectsorter.structure.temp.ElementEnum.ElementComponentCompareType;
import objectsorter.structure.temp.ElementEnum.OrderType;
import objectsorter.structure.temp.comparator.ElementComponentComparator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ElementComponentComparatorTest {
	// Standard Test
	//public int GENERATION_COUNT_PER = 1_000, NAME_MIN_LEN = 1, NAME_MAX_LEN=10;

	// Light Test
	public int GENERATION_COUNT_PER = 1_000, NAME_MIN_LEN = 1, NAME_MAX_LEN=10;
	
	// Debug Test
	//public int GENERATION_COUNT_PER = 5, NAME_MIN_LEN = 1, NAME_MAX_LEN=10;

	// Helper Methods
	String generateRandomString(int minLength, int maxLength) {
		int strLen = (int)(Math.random()*(maxLength-minLength)+minLength);
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i<strLen;i++) {
			char generatedChar = (char)(Math.random()*94+33);
			stringBuilder.append(generatedChar);
		}
		return stringBuilder.toString();
	}


	void printArrayListConcurrently(ArrayList<?> arrayList1, ArrayList<?> arrayList2) {
		for(int i = 0;i<arrayList1.size();i++) {
			System.out.println("FROM ArrayList1: \n"
					+arrayList1.get(i)+"\n"
					+"FROM ArrayList2: \n"
					+arrayList2.get(i));
		}
	}

	int getRandomIndex(ArrayList<?> arrayList) {
		return (int)(Math.random()*arrayList.size());
	}
	
	ElementComponent<Object> generateRandomElementComponent() {
		int randomNum = (int)(Math.random()*4);
		String generatedString;
		switch(randomNum){
		case 0:
			// Generate Int
			int generatedInteger = (int)(Math.random()*10_000_000);
			generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			return new ElementComponent<>(generatedString,generatedInteger);
		case 1:
			// Generate Double
			double generatedDouble = Math.random()*10_000_000;
			generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			return new ElementComponent<>(generatedString,generatedDouble);
		case 2:
			// Generate String
			String generatedStringInfo = generateRandomString(50,50);
			String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			return new ElementComponent<>(generatedStringName,generatedStringInfo);
		case 3:
			// Generate ArrayList
			ArrayList<?> generatedArrayList = new ArrayList<>();
			generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			return new ElementComponent<>(generatedString,generatedArrayList);
		}
		return null;
	}

	public <T> ArrayList<T> getRandomizedList(ArrayList<T> originalList){
		ArrayList<T> copiedList = new ArrayList<T>();
		for(T item: originalList) {
			copiedList.add(item);
		}
		ArrayList<T> randomizedList = new ArrayList<T>();
		while(!copiedList.isEmpty()) {
			randomizedList.add(copiedList.remove(getRandomIndex(copiedList)));
		}
		return randomizedList;
	}

	public String getElememtStringRepresentation(Element element) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Element Name: ");
		stringBuilder.append(element.getElementName()+"\n");
		stringBuilder.append("Created @ : " + element.getCreatedDate()+"\t");
		stringBuilder.append("Modified @: " + element.getLastModifiedDate()+"\n");
		stringBuilder.append("Unique Identifier: " + element.getUniqueIndentifer()+"\n");
		return stringBuilder.toString();
	}

	public String getElementComponentStringRepresentation(ElementComponent<?> elementComponent) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Component ");
		stringBuilder.append(getElememtStringRepresentation(elementComponent));
		stringBuilder.append("Info: "+elementComponent.getElementInfo()+"\n");
		return stringBuilder.toString();
	}

	public String getElementComponentListRepresentation(ElementComponentList<?> elementList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Component List ");
		stringBuilder.append(getElememtStringRepresentation(elementList));
		stringBuilder.append("List:\n");
		for(ElementComponent<?> component:elementList.getElementList()) {
			stringBuilder.append(component+"\n");
		}

		return stringBuilder.toString();

	}

	@Test
	void Constructor_Error_Check() {
		// Testing Default Constructor
		try {
			ElementComponentComparator<ElementComponent<Integer>> elementComponentComparator;
			elementComponentComparator = new ElementComponentComparator<ElementComponent<Integer>>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Integer>: Exception Found");
		}
		try {
			ElementComponentComparator<ElementComponent<Double>> elementComponentComparator;
			elementComponentComparator = new ElementComponentComparator<ElementComponent<Double>>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Double>: Exception Found");
		}
		try {
			ElementComponentComparator<ElementComponent<String>> elementComponentComparator;
			elementComponentComparator = new ElementComponentComparator<ElementComponent<String>>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<String>: Exception Found");
		}
		try {
			ElementComponentComparator<ElementComponent<Object>> elementComponentComparator;
			elementComponentComparator = new ElementComponentComparator<ElementComponent<Object>>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Object>: Exception Found");
		}
		try {
			ElementComponentComparator<ElementComponent<?>> elementComponentComparator;
			elementComponentComparator = new ElementComponentComparator<ElementComponent<?>>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<?>: Exception Found");
		}
	}

	@Test
	void Compare_NAME_Check() {

		ArrayList<ElementComponent<?>> elementComponentList = new ArrayList<ElementComponent<?>>();
		ElementComponentComparator<ElementComponent<?>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.NAME,OrderType.ASCENDING);
		ArrayList<String> generatedNames = new ArrayList<>();
		// Get Integer
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			int generatedInteger = (int)(Math.random()*10_000_000);
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedNames.add(generatedString);
			elementComponentList.add(new ElementComponent<Integer>(generatedString,generatedInteger));
		}

		// Get Double
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			double generatedDouble = Math.random()*10_000_000;
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedNames.add(generatedString);
			elementComponentList.add(new ElementComponent<Double>(generatedString,generatedDouble));
		}

		// Get String
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			String generatedStringInfo = "String Info";
			String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedNames.add(generatedStringName);
			elementComponentList.add(new ElementComponent<String>(generatedStringName,generatedStringInfo));
		}

		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<?> newElementComponent = generateRandomElementComponent();
			generatedNames.add(newElementComponent.getElementName());
			elementComponentList.add(newElementComponent);
		}
		Collections.sort(generatedNames);
		elementComponentList.sort(elementComponentComparator);
		ArrayList<String> namesFromComponentList = new ArrayList<String>();
		for(ElementComponent<?> elementComponent:elementComponentList) {
			namesFromComponentList.add(elementComponent.getElementName());
		}
		assertEquals(generatedNames,namesFromComponentList,
				"Failure - Sort By Name (Ascending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);

		Collections.reverse(generatedNames);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.NAME,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		namesFromComponentList = new ArrayList<String>();
		for(ElementComponent<?> elementComponent:elementComponentList) {
			namesFromComponentList.add(elementComponent.getElementName());
		}
		assertEquals(generatedNames,namesFromComponentList,
				"Failure - Sort By Name (Descending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);
	}

	@Test
	void Compare_CREATED_DATE_Check() {ArrayList<ElementComponent<?>> elementComponentList = new ArrayList<ElementComponent<?>>();
	ElementComponentComparator<ElementComponent<?>> elementComponentComparator = 
			new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.CREATED_DATE,OrderType.ASCENDING);
	ArrayList<ElementComponent<?>> generatedComponents = new ArrayList<>();
	try {
		// Get Integer
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			int generatedInteger = (int)(Math.random()*10_000_000);
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			ElementComponent<Integer> elementComponent = new ElementComponent<Integer>(generatedString,generatedInteger); 
			generatedComponents.add(elementComponent);
			elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			TimeUnit.MILLISECONDS.sleep((long)(1));
		}

		// Get Double
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			double generatedDouble = Math.random()*10_000_000;
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			ElementComponent<Double> elementComponent = new ElementComponent<Double>(generatedString,generatedDouble); 
			generatedComponents.add(elementComponent);
			elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			TimeUnit.MILLISECONDS.sleep((long)(1));
		}

		// Get String
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			String generatedStringInfo = "String Info";
			String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			ElementComponent<String> elementComponent = new ElementComponent<String>(generatedStringName,generatedStringInfo); 
			generatedComponents.add(elementComponent);
			elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			TimeUnit.MILLISECONDS.sleep((long)(1));
		}

		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<?> newElementComponent = generateRandomElementComponent();
			generatedComponents.add(newElementComponent);
			elementComponentList.add(getRandomIndex(elementComponentList),newElementComponent);
			TimeUnit.MILLISECONDS.sleep((long)(1));
		}
		elementComponentList.sort(elementComponentComparator);
		assertEquals(generatedComponents,elementComponentList,
				"Failure - Sort By Created Date (Ascending) does Sort not Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);

		Collections.reverse(generatedComponents);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.CREATED_DATE,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		assertEquals(generatedComponents,elementComponentList,
				"Failure - Sort By Created Date (Descending) does Sort not Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);
	}catch(Exception e) {
		fail("Failure - Excpetion Found");
	}
	}

	@Test
	void Compare_MODIFIED_DATE_Check() {

		ArrayList<ElementComponent<?>> elementComponentList = new ArrayList<ElementComponent<?>>();
		ElementComponentComparator<ElementComponent<?>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.MODIFIED_DATE,OrderType.ASCENDING);
		ArrayList<ElementComponent<?>> generatedComponents = new ArrayList<ElementComponent<?>>();
		ArrayList<ElementComponent<?>> updatedComponents = new ArrayList<ElementComponent<?>>();
		try {
			// Get Integer
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				int generatedInteger = (int)(Math.random()*10_000_000);
				String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<Integer> elementComponent = new ElementComponent<Integer>(generatedString,generatedInteger); 
				generatedComponents.add(elementComponent);
				elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			}

			// Get Double
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				double generatedDouble = Math.random()*10_000_000;
				String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<Double> elementComponent = new ElementComponent<Double>(generatedString,generatedDouble); 
				generatedComponents.add(elementComponent);
				elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			}

			// Get String
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				String generatedStringInfo = "String Info";
				String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<String> elementComponent = new ElementComponent<String>(generatedStringName,generatedStringInfo); 
				generatedComponents.add(elementComponent);
				elementComponentList.add(getRandomIndex(elementComponentList),elementComponent);
			}

			// Get Object
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				ElementComponent<?> newElementComponent = generateRandomElementComponent();
				generatedComponents.add(newElementComponent);
				elementComponentList.add(getRandomIndex(elementComponentList),newElementComponent);
			}
			while(!generatedComponents.isEmpty()) {
				TimeUnit.MILLISECONDS.sleep((long)(1));
				ElementComponent<?> retrivedComponent = generatedComponents.remove(getRandomIndex(generatedComponents));
				retrivedComponent.updateTime();
				updatedComponents.add(retrivedComponent);
			}

			elementComponentList.sort(elementComponentComparator);
			assertEquals(updatedComponents,elementComponentList,
					"Failure - Sort By Modified Date (Ascending) does Sort not Component Properly");
			//printArrayListConcurrently(updatedComponents,elementComponentList);

			Collections.reverse(updatedComponents);
			elementComponentComparator = 
					new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.MODIFIED_DATE,OrderType.DESCENDING);
			elementComponentList.sort(elementComponentComparator);
			assertEquals(updatedComponents,elementComponentList,
					"Failure - Sort By Modified Date (Descending) does Sort not Component Properly");
			//printArrayListConcurrently(updatedComponents,elementComponentList);
		}catch(Exception e) {
			fail("Failure - Excpetion Found");
		}
	}

	@Test
	void Compare_UNIQUE_IDENTIFER_Check() {

		ArrayList<ElementComponent<?>> elementComponentList = new ArrayList<ElementComponent<?>>();
		ElementComponentComparator<ElementComponent<?>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.UNIQUE_IDENTIFER,OrderType.ASCENDING);
		try {
			// Get Integer
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				int generatedInteger = (int)(Math.random()*10_000_000);
				String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<Integer> elementComponent = new ElementComponent<Integer>(generatedString,generatedInteger);
				elementComponentList.add(elementComponent);
			}

			// Get Double
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				double generatedDouble = Math.random()*10_000_000;
				String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<Double> elementComponent = new ElementComponent<Double>(generatedString,generatedDouble);
				elementComponentList.add(elementComponent);
			}

			// Get String
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				String generatedStringInfo = "String Info";
				String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
				ElementComponent<String> elementComponent = new ElementComponent<String>(generatedStringName,generatedStringInfo);
				elementComponentList.add(elementComponent);
			}

			// Get Object
			for(int i = 0; i<GENERATION_COUNT_PER;i++) {
				int randomNum = (int)(Math.random()*4);
				String generatedString;
				ElementComponent<?> elementComponent;
				switch(randomNum){
				case 0:
					// Generate Int
					int generatedInteger = (int)(Math.random()*10_000_000);
					generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
					elementComponent = new ElementComponent<>(generatedString,generatedInteger);
					elementComponentList.add(elementComponent);
					break;
				case 1:
					// Generate Double
					double generatedDouble = Math.random()*10_000_000;
					generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
					elementComponent = new ElementComponent<>(generatedString,generatedDouble);
					elementComponentList.add(elementComponent);
				case 2:
					// Generate String
					String generatedStringInfo = "String Info";
					String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
					elementComponent = new ElementComponent<>(generatedStringName,generatedStringInfo);
					elementComponentList.add(elementComponent);
				case 3:
					// Generate ArrayList
					ArrayList<?> generatedArrayList = new ArrayList<>();
					generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
					elementComponent = new ElementComponent<>(generatedString,generatedArrayList);
					elementComponentList.add(elementComponent);
				}
			}
			ArrayList<ElementComponent<?>> randomizedList = getRandomizedList(elementComponentList);
			randomizedList.sort(elementComponentComparator);
			assertEquals(elementComponentList,randomizedList,
					"Failure - Sort By Unique Identifier (Ascending) does not Sort Component Properly");
			//printArrayListConcurrently(generatedNames,namesFromComponentList);

			Collections.reverse(elementComponentList);
			elementComponentComparator = 
					new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.UNIQUE_IDENTIFER,OrderType.DESCENDING);
			randomizedList = getRandomizedList(elementComponentList);
			randomizedList.sort(elementComponentComparator);
			assertEquals(elementComponentList,randomizedList,
					"Failure - Sort By Unique Identifier (Descending) does not Sort Component Properly");
			//printArrayListConcurrently(generatedNames,namesFromComponentList);
		}catch(Exception e) {
			fail("Failure - Excpetion Found");
		}
	}

	@Test
	void Compare_INFO_COMPARISION_Integer_Check() {
		ArrayList<ElementComponent<Integer>> elementComponentList = new ArrayList<ElementComponent<Integer>>();
		ElementComponentComparator<ElementComponent<Integer>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.ASCENDING);
		ArrayList<Integer> generatedIntegers = new ArrayList<>();
		// Get Integer
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			int generatedInteger = (int)(Math.random()*10_000_000);
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedIntegers.add(generatedInteger);
			elementComponentList.add(new ElementComponent<Integer>(generatedString,generatedInteger));
		}

		Collections.sort(generatedIntegers);
		elementComponentList.sort(elementComponentComparator);
		ArrayList<Integer> integerFromComponentList = new ArrayList<Integer>();
		for(ElementComponent<Integer> elementComponent:elementComponentList) {
			integerFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedIntegers,integerFromComponentList,
				"Failure - Sort By Integer (Ascending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);

		Collections.reverse(generatedIntegers);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<Integer>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		integerFromComponentList = new ArrayList<Integer>();
		for(ElementComponent<Integer> elementComponent:elementComponentList) {
			integerFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedIntegers,integerFromComponentList,
				"Failure - Sort By Integer (Descending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);
	}

	@Test
	void Compare_INFO_COMPARISION_Double_Check() {
		ArrayList<ElementComponent<Double>> elementComponentList = new ArrayList<ElementComponent<Double>>();
		ElementComponentComparator<ElementComponent<Double>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<Double>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.ASCENDING);
		ArrayList<Double> generatedDoubles = new ArrayList<>();
		// Get Double
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			double generatedDouble = Math.random()*10_000_000;
			String generatedString = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedDoubles.add(generatedDouble);
			elementComponentList.add(new ElementComponent<Double>(generatedString,generatedDouble));
		}

		Collections.sort(generatedDoubles);
		elementComponentList.sort(elementComponentComparator);
		ArrayList<Double> DoubleFromComponentList = new ArrayList<Double>();
		for(ElementComponent<Double> elementComponent:elementComponentList) {
			DoubleFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedDoubles,DoubleFromComponentList,
				"Failure - Sort By Double (Ascending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);

		Collections.reverse(generatedDoubles);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<Double>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		DoubleFromComponentList = new ArrayList<Double>();
		for(ElementComponent<Double> elementComponent:elementComponentList) {
			DoubleFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedDoubles,DoubleFromComponentList,
				"Failure - Sort By Double (Descending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);
	}

	@Test
	void Compare_INFO_COMPARISION_String_Check() {

		ArrayList<ElementComponent<String>> elementComponentList = new ArrayList<ElementComponent<String>>();
		ElementComponentComparator<ElementComponent<String>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<String>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.ASCENDING);
		ArrayList<String> generatedInfo = new ArrayList<>();

		// Get String
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			String generatedStringInfo = generateRandomString(0, 20);
			String generatedStringName = generateRandomString(NAME_MIN_LEN, NAME_MAX_LEN);
			generatedInfo.add(generatedStringInfo);
			elementComponentList.add(new ElementComponent<String>(generatedStringName,generatedStringInfo));
		}

		Collections.sort(generatedInfo);
		elementComponentList.sort(elementComponentComparator);
		ArrayList<String> stringInfoFromComponentList = new ArrayList<String>();
		for(ElementComponent<String> elementComponent:elementComponentList) {
			stringInfoFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedInfo,stringInfoFromComponentList,
				"Failure - Sort By String Info (Ascending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);

		Collections.reverse(generatedInfo);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<String>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		stringInfoFromComponentList = new ArrayList<String>();
		for(ElementComponent<String> elementComponent:elementComponentList) {
			stringInfoFromComponentList.add(elementComponent.getElementInfo());
		}
		assertEquals(generatedInfo,stringInfoFromComponentList,
				"Failure - Sort By String Info (Descending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);
	}

	@Test
	void Compare_INFO_COMPARISION_Object_Check() {
		ArrayList<ElementComponent<Object>> elementComponentList = new ArrayList<ElementComponent<Object>>();
		ElementComponentComparator<ElementComponent<?>> elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.ASCENDING);
		ArrayList<Integer> generatedIntegerList = new ArrayList<>();
		ArrayList<Double> generatedDoubleList = new ArrayList<>();
		ArrayList<String> generatedStringList = new ArrayList<>();
		ArrayList<ArrayList<Object>> generatedArrayListList = new ArrayList<>();
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			Object elementInfo = newElementComponent.getElementInfo();
			if(elementInfo instanceof Integer) {
				generatedIntegerList.add((Integer)elementInfo);
				elementComponentList.add(newElementComponent);
			}else if(elementInfo instanceof Double) {
				generatedDoubleList.add((Double)elementInfo);
				elementComponentList.add(newElementComponent);
			}else if(elementInfo instanceof String) {
				generatedStringList.add((String)elementInfo);
				elementComponentList.add(newElementComponent);
			}else if(elementInfo instanceof ArrayList) {
				generatedArrayListList.add((ArrayList)elementInfo);
				elementComponentList.add(newElementComponent);
			}
		}
		Collections.sort(generatedIntegerList);
		Collections.sort(generatedDoubleList);
		Collections.sort(generatedStringList);
		// Can't sort ArrayList
		//Collections.sort(generatedArrayListList);
		elementComponentList.sort(elementComponentComparator);
		ArrayList<Integer> componentIntegerList = new ArrayList<>();
		ArrayList<Double> componentDoubleList = new ArrayList<>();
		ArrayList<String> componentStringList = new ArrayList<>();
		ArrayList<ArrayList<Object>> componentArrayListList = new ArrayList<>();


		StringBuilder strBuilder = new StringBuilder();
		int counter=0;
		for(ElementComponent<Object> component: elementComponentList) {
			if(component.getElementInfo() instanceof Integer) {
				componentIntegerList.add((Integer)component.getElementInfo());
			}else if(component.getElementInfo() instanceof Double) {
				componentDoubleList.add((Double)component.getElementInfo());
			}else if(component.getElementInfo() instanceof String) {
				componentStringList.add((String)component.getElementInfo());
			}else if(component.getElementInfo() instanceof ArrayList) {
				componentArrayListList.add((ArrayList<Object>)component.getElementInfo());
			}
			strBuilder.append(component.getElementInfo());
			if(counter++%10==9) {
				strBuilder.append("\n");
			}else {
				strBuilder.append("\t");
			}
		}
		//System.out.println(strBuilder.toString());
		assertEquals(generatedIntegerList,componentIntegerList,
				"Failure - Sort By Wildcard<Integer> (Ascending) does not Sort Component Properly");
		assertEquals(generatedDoubleList,componentDoubleList,
				"Failure - Sort By Wildcard<Double> (Ascending) does not Sort Component Properly");
		assertEquals(generatedStringList,componentStringList,
				"Failure - Sort By Wildcard<String> (Ascending) does not Sort Component Properly");
		assertEquals("ArrayList Objects: "+generatedArrayListList.size(), "ArrayList Objects: "+componentArrayListList.size(),
				"Failure - Sort By Wildcard<ArrayList<Object>> (Ascending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);


		Collections.reverse(generatedIntegerList);
		Collections.reverse(generatedDoubleList);
		Collections.reverse(generatedStringList);
		Collections.reverse(generatedArrayListList);
		elementComponentComparator = 
				new ElementComponentComparator<ElementComponent<?>>(ElementComponentCompareType.INFO_COMPARISION,OrderType.DESCENDING);
		elementComponentList.sort(elementComponentComparator);
		componentIntegerList = new ArrayList<>();
		componentDoubleList = new ArrayList<>();
		componentStringList = new ArrayList<>();
		componentArrayListList = new ArrayList<>();

		for(ElementComponent<Object> component: elementComponentList) {
			if(component.getElementInfo() instanceof Integer) {
				componentIntegerList.add((Integer)component.getElementInfo());
			}else if(component.getElementInfo() instanceof Double) {
				componentDoubleList.add((Double)component.getElementInfo());
			}else if(component.getElementInfo() instanceof String) {
				componentStringList.add((String)component.getElementInfo());
			}else if(component.getElementInfo() instanceof ArrayList) {
				componentArrayListList.add((ArrayList<Object>)component.getElementInfo());
			}
		}
		assertEquals(generatedIntegerList,componentIntegerList,
				"Failure - Sort By Wildcard<Integer> (Descending) does not Sort Component Properly");
		assertEquals(generatedDoubleList,componentDoubleList,
				"Failure - Sort By Wildcard<Double> (Descending) does not Sort Component Properly");
		assertEquals(generatedStringList,componentStringList,
				"Failure - Sort By Wildcard<String> (Descending) does not Sort Component Properly");
		assertEquals("ArrayList Objects: "+generatedArrayListList.size(), "ArrayList Objects: "+componentArrayListList.size(),
				"Failure - Sort By Wildcard<ArrayList<Object>> (Descending) does not Sort Component Properly");
		//printArrayListConcurrently(generatedNames,namesFromComponentList);


	}

	@Test 
	// This is assuming that ElementList is Working
	void Pointer_Check_NAME_And_CREATED_DATE_Check(){
		ElementComponentCompareType firstCompare = ElementComponentCompareType.NAME, 
				secondCompare = ElementComponentCompareType.CREATED_DATE;
		
		ElementComponentComparator<ElementComponent<Object>> byNameComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> toBeSwapped = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> byCreatedDateComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(secondCompare,OrderType.ASCENDING);
		ElementComponentList<Object> nameBasedList = new ElementComponentList<Object>("Random List",byNameComparator);
		ElementComponentList<Object> swappedList = new ElementComponentList<Object>("Random List",toBeSwapped);
		ElementComponentList<Object> createdDateBasedList = new ElementComponentList<Object>("Random List",byCreatedDateComparator);

		swappedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		createdDateBasedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			nameBasedList.addElement(newElementComponent);
			swappedList.addElement(newElementComponent);
			createdDateBasedList.addElement(newElementComponent);
			try {
				TimeUnit.MILLISECONDS.sleep((long)(1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		assertEquals(getElementComponentListRepresentation(nameBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - namedBasedList and swappedList before swap (by "+firstCompare+") are not the same");
		toBeSwapped.setCompareType(secondCompare);
		createdDateBasedList.updateTime();
		toBeSwapped.updateTime();

		assertEquals(getElementComponentListRepresentation(createdDateBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - createdDateBasedList and swappedList after swap (by "+secondCompare+") are not the same");

		swappedList.setActiveComponentComparator(null, false);
		assertEquals(0,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when removed");
		swappedList.setActiveComponentComparator(toBeSwapped, false);
		assertEquals(1,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when added");

		// Test Reverse
		toBeSwapped.setCompareType(firstCompare);
		nameBasedList.updateTime();
		toBeSwapped.updateTime();
		ArrayList<String> originalNames = new ArrayList<>(), updatedNames = new ArrayList<>();
		for(ElementComponent<Object> elementComponent: nameBasedList.getElementList()) {
			originalNames.add(elementComponent.getElementName());
		}
		for(ElementComponent<Object> elementComponent: swappedList.getElementList()) {
			updatedNames.add(elementComponent.getElementName());
		}
		assertEquals(originalNames,updatedNames,
				"Failure - createdNameBasedList and swappedList after swap back (by "+firstCompare+") are not the same");
	}

	@Test 
	// This is assuming that ElementList is Working
	void Pointer_Check_NAME_And_MODIFIED_DATE_Check(){
		ElementComponentCompareType firstCompare = ElementComponentCompareType.NAME, 
				secondCompare = ElementComponentCompareType.MODIFIED_DATE;
		
		ElementComponentComparator<ElementComponent<Object>> byNameComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> toBeSwapped = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> byModifiedDateComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(secondCompare,OrderType.ASCENDING);
		ElementComponentList<Object> nameBasedList = new ElementComponentList<Object>("Random List",byNameComparator);
		ElementComponentList<Object> swappedList = new ElementComponentList<Object>("Random List",toBeSwapped);
		ElementComponentList<Object> modifiedBasedList = new ElementComponentList<Object>("Random List",byModifiedDateComparator);

		swappedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		modifiedBasedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			nameBasedList.addElement(newElementComponent);
			swappedList.addElement(newElementComponent);
			modifiedBasedList.addElement(newElementComponent);
		}
		
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			nameBasedList.getElementList().get(i).updateTime();
			try {
				TimeUnit.MILLISECONDS.sleep((long)(1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		modifiedBasedList.updateList();
		//System.out.println(getElementComponentListRepresentation(modifiedBasedList));
		
		assertEquals(getElementComponentListRepresentation(nameBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - namedBasedList and swappedList before swap (by "+firstCompare+") are not the same");
		toBeSwapped.setCompareType(secondCompare);
		modifiedBasedList.updateTime();
		toBeSwapped.updateTime();

		assertEquals(getElementComponentListRepresentation(modifiedBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - ModifiedDateBasedList and swappedList after swap (by "+secondCompare+") are not the same");
		swappedList.setActiveComponentComparator(null, false);
		assertEquals(0,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when removed");
		swappedList.setActiveComponentComparator(toBeSwapped, false);
		assertEquals(1,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when added");

		// Test Reverse
		toBeSwapped.setCompareType(firstCompare);
		//printArrayListConcurrently(modifiedBasedList.getElementList(),swappedList.getElementList());
		nameBasedList.updateTime();
		toBeSwapped.updateTime();
		ArrayList<String> originalNames = new ArrayList<>(), updatedNames = new ArrayList<>();
		for(ElementComponent<Object> elementComponent: nameBasedList.getElementList()) {
			originalNames.add(elementComponent.getElementName());
		}
		for(ElementComponent<Object> elementComponent: swappedList.getElementList()) {
			updatedNames.add(elementComponent.getElementName());
		}
		//System.out.println(originalNames);
		//System.out.println(updatedNames);
		assertEquals(originalNames,updatedNames,
				"Failure - createdNameBasedList and swappedList after swap back (by "+firstCompare+") are not the same");
	}
	
	
	@Test 
	// This is assuming that ElementList is Working
	void Pointer_Check_NAME_And_UNIQUE_IDENTIFIER_Check(){
		ElementComponentCompareType firstCompare = ElementComponentCompareType.NAME, 
				secondCompare = ElementComponentCompareType.UNIQUE_IDENTIFER;
		
		ElementComponentComparator<ElementComponent<Object>> byNameComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> toBeSwapped = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> byIDComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(secondCompare,OrderType.ASCENDING);
		ElementComponentList<Object> nameBasedList = new ElementComponentList<Object>("Random List",byNameComparator);
		ElementComponentList<Object> swappedList = new ElementComponentList<Object>("Random List",toBeSwapped);
		ElementComponentList<Object> idBasedList = new ElementComponentList<Object>("Random List",byIDComparator);

		swappedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		idBasedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			newElementComponent.setElementName(generateRandomString(1,20));
			nameBasedList.addElement(newElementComponent);
			swappedList.addElement(newElementComponent);
			idBasedList.addElement(newElementComponent);
		}
				
		assertEquals(getElementComponentListRepresentation(nameBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - namedBasedList and swappedList before swap (by "+firstCompare+") are not the same");
		toBeSwapped.setCompareType(secondCompare);
		idBasedList.updateTime();
		toBeSwapped.updateTime();

		assertEquals(getElementComponentListRepresentation(idBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - createdDateBasedList and swappedList after swap (by "+secondCompare+") are not the same");
		swappedList.setActiveComponentComparator(null, false);
		assertEquals(0,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when removed");
		swappedList.setActiveComponentComparator(toBeSwapped, false);
		assertEquals(1,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when added");

		// Test Reverse
		toBeSwapped.setCompareType(firstCompare);
		//printArrayListConcurrently(idBasedList.getElementList(),swappedList.getElementList());
		nameBasedList.updateTime();
		toBeSwapped.updateTime();
		ArrayList<String> originalNames = new ArrayList<>(), updatedNames = new ArrayList<>();
		for(ElementComponent<Object> elementComponent: nameBasedList.getElementList()) {
			originalNames.add(elementComponent.getElementName());
		}
		for(ElementComponent<Object> elementComponent: swappedList.getElementList()) {
			updatedNames.add(elementComponent.getElementName());
		}
		//System.out.println(originalNames);
		//System.out.println(updatedNames);
		assertEquals(originalNames,updatedNames,
				"Failure - createdNameBasedList and swappedList after swap back (by "+firstCompare+") are not the same");
	}
	
	@Test 
	// This is assuming that ElementList is Working
	void Pointer_Check_NAME_And_INFO_Check(){
		ElementComponentCompareType firstCompare = ElementComponentCompareType.NAME, 
				secondCompare = ElementComponentCompareType.INFO_COMPARISION;
		
		ElementComponentComparator<ElementComponent<Object>> byNameComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> toBeSwapped = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> byInfoComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(secondCompare,OrderType.ASCENDING);
		ElementComponentList<Object> nameBasedList = new ElementComponentList<Object>("Random List",byNameComparator);
		ElementComponentList<Object> swappedList = new ElementComponentList<Object>("Random List",toBeSwapped);
		ElementComponentList<Object> infoBasedList = new ElementComponentList<Object>("Random List",byInfoComparator);

		swappedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		infoBasedList.setUniqueIndentifer(nameBasedList.getUniqueIndentifer());
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			newElementComponent.setElementName(generateRandomString(1,20));
			nameBasedList.addElement(newElementComponent);
			swappedList.addElement(newElementComponent);
			infoBasedList.addElement(newElementComponent);
		}
				
		assertEquals(getElementComponentListRepresentation(nameBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - namedBasedList and swappedList before swap (by "+firstCompare+") are not the same");
		toBeSwapped.setCompareType(secondCompare);
		infoBasedList.updateTime();
		toBeSwapped.updateTime();

		ArrayList<Object> swappedInfo = new ArrayList<>(), infoBasedInfo = new ArrayList<>();
		for(int i = 0; i<swappedList.getElementList().size();i++) {
			swappedInfo.add(swappedList.getElementList().get(i).getElementInfo());
			infoBasedInfo.add(infoBasedList.getElementList().get(i).getElementInfo());
		}
		assertEquals(swappedInfo.toString(),infoBasedInfo.toString(),
				"Failure - createdDateBasedList and swappedList after swap (by "+secondCompare+") are not the same");
		swappedList.setActiveComponentComparator(null, false);
		assertEquals(0,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when removed");
		swappedList.setActiveComponentComparator(toBeSwapped, false);
		assertEquals(1,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when added");

		// Test Reverse
		toBeSwapped.setCompareType(firstCompare);
		//printArrayListConcurrently(idBasedList.getElementList(),swappedList.getElementList());
		nameBasedList.updateTime();
		toBeSwapped.updateTime();
		ArrayList<String> originalNames = new ArrayList<>(), updatedNames = new ArrayList<>();
		for(ElementComponent<Object> elementComponent: nameBasedList.getElementList()) {
			originalNames.add(elementComponent.getElementName());
		}
		for(ElementComponent<Object> elementComponent: swappedList.getElementList()) {
			updatedNames.add(elementComponent.getElementName());
		}
		//System.out.println(originalNames);
		//System.out.println(updatedNames);
		assertEquals(originalNames,updatedNames,
				"Failure - createdNameBasedList and swappedList after swap back (by "+firstCompare+") are not the same");
	}
	
	/*@Test 
	// This is assuming that ElementList is Working
	void Pointer_Check_CREATED_DATE_And_MODIFIED_DATE_Check(){
		ElementComponentCompareType firstCompare = ElementComponentCompareType.CREATED_DATE, 
				secondCompare = ElementComponentCompareType.MODIFIED_DATE;
		
		ElementComponentComparator<ElementComponent<Object>> byCreatedDateComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> toBeSwapped = 
				new ElementComponentComparator<ElementComponent<Object>>(firstCompare,OrderType.ASCENDING);
		ElementComponentComparator<ElementComponent<Object>> byModifiedDateComparator = 
				new ElementComponentComparator<ElementComponent<Object>>(secondCompare,OrderType.ASCENDING);
		ElementComponentList<Object> createdBasedList = new ElementComponentList<Object>("Random List",byCreatedDateComparator);
		ElementComponentList<Object> swappedList = new ElementComponentList<Object>("Random List",toBeSwapped);
		ElementComponentList<Object> modifiedBasedList = new ElementComponentList<Object>("Random List",byModifiedDateComparator);

		swappedList.setUniqueIndentifer(createdBasedList.getUniqueIndentifer());
		modifiedBasedList.setUniqueIndentifer(createdBasedList.getUniqueIndentifer());
		// Get Object
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			ElementComponent<Object> newElementComponent = generateRandomElementComponent();
			createdBasedList.addElement(newElementComponent);
			swappedList.addElement(newElementComponent);
			modifiedBasedList.addElement(newElementComponent);
		}
		
		for(int i = 0; i<GENERATION_COUNT_PER;i++) {
			createdBasedList.getElementList().get(i).updateTime();
			try {
				TimeUnit.MILLISECONDS.sleep((long)(1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		modifiedBasedList.updateList();
		//System.out.println(getElementComponentListRepresentation(modifiedBasedList));
		
		assertEquals(getElementComponentListRepresentation(nameBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - namedBasedList and swappedList before swap (by "+firstCompare+") are not the same");
		toBeSwapped.setCompareType(secondCompare);
		modifiedBasedList.updateTime();
		toBeSwapped.updateTime();

		assertEquals(getElementComponentListRepresentation(modifiedBasedList),getElementComponentListRepresentation(swappedList),
				"Failure - ModifiedDateBasedList and swappedList after swap (by "+secondCompare+") are not the same");
		swappedList.setActiveComponentComparator(null, false);
		assertEquals(0,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when removed");
		swappedList.setActiveComponentComparator(toBeSwapped, false);
		assertEquals(1,toBeSwapped.getUsedList().size(),
				"Failure - UsedList not Updated when added");

		// Test Reverse
		toBeSwapped.setCompareType(firstCompare);
		//printArrayListConcurrently(modifiedBasedList.getElementList(),swappedList.getElementList());
		nameBasedList.updateTime();
		toBeSwapped.updateTime();
		ArrayList<String> originalNames = new ArrayList<>(), updatedNames = new ArrayList<>();
		for(ElementComponent<Object> elementComponent: nameBasedList.getElementList()) {
			originalNames.add(elementComponent.getElementName());
		}
		for(ElementComponent<Object> elementComponent: swappedList.getElementList()) {
			updatedNames.add(elementComponent.getElementName());
		}
		//System.out.println(originalNames);
		//System.out.println(updatedNames);
		assertEquals(originalNames,updatedNames,
				"Failure - createdNameBasedList and swappedList after swap back (by "+firstCompare+") are not the same");
	}*/
	
	//@Test
	//void 

	/* What to Test:
	 * Test Pointers
	 * 	Test Old PointerList
	 * 	Test New PointerList
	 * 	Check Sorted NewList
	 * 
	 * Edit
	 * The Remove & Check if there is only 1 elememt
	 * Check Add?
	 * 
	 * Check Integer, Double, String & ArrayList<?> (Unknwon)
	 * Swap from 
	 * 
	 * Ascending & Descending should now be a garenteed, so it should just be comparision types now
	 *  Name	
	 *  Created Date
	 *  Modified Date
	 *  Unique Idea
	 *  Info Comparision
	 *   5! 15 Test Cases
	 *  5+4+3+2+1
	 *  32
	 * 
	 * (DONE)	Name	Created
	 * (DONE)	Name	Modified
	 * (DONE)	Name	Unqiue ID
	 * (DONE)	Name	Info
	 *  Created	Modified
	 *  Created	Unique ID
	 *  Created	Info
	 *  Modified	UniqueID
	 *  Modified	Info
	 *  Unique ID	Info
	 */

}
