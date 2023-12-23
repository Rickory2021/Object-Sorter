package junit.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

class ExampleExceptionTest {

	

	@Test
	public void testExceptionAndState() {
	  List<Object> list = new ArrayList<>();

	  IndexOutOfBoundsException thrown = assertThrows(
	      IndexOutOfBoundsException.class,
	      () -> list.add(1, new Object()));

	  // assertions on the thrown exception
	  assertEquals("Index: 1, Size: 0", thrown.getMessage());
	  // assertions on the state of a domain object after the exception has been thrown
	  assertTrue(list.isEmpty());
	}

}
