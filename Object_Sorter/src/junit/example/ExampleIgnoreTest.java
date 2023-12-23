package junit.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class ExampleIgnoreTest {

	@Ignore("Test is ignored as a demonstration [Before @Test]")
	@Test
	public void testSame1() {
	    assertEquals(1, 1);
	}
	
	
	@Test
	@Ignore("Test is ignored as a demonstration [After @Test]")
	public void testSame2() {
		assertEquals(1, 1);
	}
	
}
