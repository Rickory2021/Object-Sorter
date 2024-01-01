package objectsorter.executable.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementComponent;

class ElementComponentTest {

	@Test
	void Constructor_Error_Check() {
		// Testing Default Constructor
		try {
			ElementComponent<Integer> elementComponentObject;
			elementComponentObject = new ElementComponent<Integer>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Integer>: Exception Found");
		}
		try {
			ElementComponent<Double> elementComponentObject;
			elementComponentObject = new ElementComponent<>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Double>: Exception Found");
		}
		try {
			ElementComponent<String> elementComponentObject;
			elementComponentObject = new ElementComponent<>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<String>: Exception Found");
		}
		try {
			ElementComponent<Object> elementComponentObject;
			elementComponentObject = new ElementComponent<>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<Object>: Exception Found");
		}
		try {
			ElementComponent<?> elementComponentObject;
			elementComponentObject = new ElementComponent<>();
		}catch(Exception e) {
			fail("Failure - Default Constructor<?>: Exception Found");
		}
		
		// Testing Constructor(String)
		try {
			ElementComponent<Integer> elementComponentObject;
			elementComponentObject = new ElementComponent<Integer>("Name");
			assertEquals("Name",elementComponentObject.getElementName(),
					"Failure - Constructor<Integer>(String): Name not Assigned");
			assertEquals(null,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Integer>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Integer>(String): Exception Found");
		}
		try {
			ElementComponent<Double> elementComponentObject;
			elementComponentObject = new ElementComponent<Double>("Name");
			assertEquals("Name",elementComponentObject.getElementName(),
					"Failure - Constructor<Double>(String): Name not Assigned");
			assertEquals(null,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Double>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Double>(String): Exception Found");
		}
		try {
			ElementComponent<String> elementComponentObject;
			elementComponentObject = new ElementComponent<>("Name");
			assertEquals("Name",elementComponentObject.getElementName(),
					"Failure - Constructor<String>(String): Name not Assigned");
			assertEquals(null,elementComponentObject.getElementInfo(),
					"Failure - Constructor<String>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<String>(String): Exception Found");
		}
		try {
			ElementComponent<Object> elementComponentObject;
			elementComponentObject = new ElementComponent<Object>("Name");
			assertEquals("Name",elementComponentObject.getElementName(),
					"Failure - Constructor<Object>(String): Name not Assigned");
			assertEquals(null,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Object>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Object>(String): Exception Found");
		}
		try {
			ElementComponent<?> elementComponentObject;
			elementComponentObject = new ElementComponent<>("Name");
			assertEquals("Name",elementComponentObject.getElementName(),
					"Failure - Constructor<?>(String): Name not Assigned");
			assertEquals(null,elementComponentObject.getElementInfo(),
					"Failure - Constructor<?>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<?>(String): Exception Found");
		}

		// Testing Constructor(E extends T elementInfo)
		try {
			ElementComponent<Integer> elementComponentObject;
			elementComponentObject = new ElementComponent<Integer>(100);
			assertEquals(100,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Integer>(String): Component not Assigned");
			assertNotEquals(100,elementComponentObject.getElementName(),
					"Failure - Constructor<Integer>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Integer>(String): Exception Found");
		}
		try {
			ElementComponent<Double> elementComponentObject;
			elementComponentObject = new ElementComponent<Double>(1.5);
			assertEquals(1.5,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Double>(String): Component not Assigned");
			assertNotEquals(1.5,elementComponentObject.getElementName(),
					"Failure - Constructor<Double>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Double>(String): Exception Found");
		}
		try {
			ElementComponent<String> elementComponentObject;
			//System.out.println("CONSTRUCTING STRING COMPONENT");
			elementComponentObject = new ElementComponent<String>("String Info");
			//System.out.println("Name:"+elementComponentObject.getElementName());
			//System.out.println("Info:"+elementComponentObject.getElementInfo());
			assertEquals("String Info",elementComponentObject.getElementInfo(),
					"Failure - Constructor<String>(String): Component not Assigned");
			
			assertNotEquals("String Info",elementComponentObject.getElementName(),
					"Failure - Constructor<String>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<String>(String): Exception Found");
		}
		try {
			ElementComponent<Object> elementComponentObject;
			elementComponentObject = new ElementComponent<Object>(5);
			assertEquals((Object)5,elementComponentObject.getElementInfo(),
					"Failure - Constructor<Object>(String): Component not Assigned");
			assertNotEquals((Object)5,elementComponentObject.getElementName(),
					"Failure - Constructor<Object>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<Object>(String): Exception Found");
		}
		try {
			ElementComponent<?> elementComponentObject;
			elementComponentObject = new ElementComponent<>(6);
			assertEquals(6,elementComponentObject.getElementInfo(),
					"Failure - Constructor<?>(String): Name not Assigned");
			assertNotEquals(6,elementComponentObject.getElementName(),
					"Failure - Constructor<?>(String): Value unintentionally assigned");
		}catch(Exception e) {
			fail("Failure - Constructor<?>(String): Exception Found");
		}
	}

}
