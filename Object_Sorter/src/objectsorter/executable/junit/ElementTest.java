package objectsorter.executable.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;

import objectsorter.structure.temp.Element;

class ElementTest {

	@Test
	void Constructor_Error_Check() {
		Element elementObject;
		try {
			elementObject = new Element();
			assertTrue(elementObject.getElementName().equals(elementObject.getUniqueIndentifer()),
					"Failure - Default Constructor: Error with ElementName or UniqueIdentifier");
		}catch(Exception e) {
			fail("Failure - Default Constructor: Exception Found");
		}
		try {
			elementObject = new Element("");
		}catch(Exception e) {
			fail("Failure - Constructor(String): Exception Found");
		}
		
		//assertTrue(true);
	}
	
	@Test
	void UpdateTime_Check() {
		Element elementObject = new Element();
		assertTrue(elementObject.getCreatedDate().compareTo(elementObject.getLastModifiedDate())<=0,
				"Failure - Default Constructor: Error with CreatedDate or LastModifiedDate");
		
		Date oldModifiedDate = elementObject.getLastModifiedDate();
		elementObject.updateTime();
		assertTrue(oldModifiedDate.compareTo(elementObject.getLastModifiedDate())<=0
				,"Failure - Error with UpdateTime Method");
	}
	
	@Test
	void ElementCount_and_ElementNumber_Check() {
		long initialElementCount = Element.getElementCount();
		Element elementObject1 = new Element();
		Element elementObject2 = new Element("ElementObject2");
		assertTrue(((Long)initialElementCount).compareTo(elementObject1.getElementNumber())<=0,
				"Failure - Default Constructor: Error with ElementCount/ElementNumber Generated");
		assertTrue(((Long)initialElementCount).compareTo(elementObject2.getElementNumber())<=0,
				"Failure - Constructor(String): Error with ElementCount/ElementNumber Generated");
		assertTrue(((Long)initialElementCount).compareTo(elementObject2.getElementNumber())<=0,
				"Failure - Constructor(String): Error with ElementCount/ElementNumber Generated");
		assertEquals((Long)elementObject2.getElementNumber(), Element.getElementCount(),
				"Failure - Default Contructor or Constructor(String): Error with ElementCount/ElementNumber Generated");
		
	}
}
