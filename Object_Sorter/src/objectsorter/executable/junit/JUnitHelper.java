package objectsorter.executable.junit;

import java.util.ArrayList;

public class JUnitHelper {
	public static String generateRandomString(int minLength, int maxLength) {
		int strLen = (int)(Math.random()*(maxLength-minLength)+minLength);
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i<strLen;i++) {
			char generatedChar = (char)(Math.random()*94+33);
			stringBuilder.append(generatedChar);
		}
		return stringBuilder.toString();
	}
	
	
	// ArrayList Helper Methods
	public String getArrayListStringConcurrently(ArrayList<?> arrayList1, ArrayList<?> arrayList2) {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0;i<arrayList1.size();i++) {
			stringBuilder.append("FROM ArrayList1: \n"
					+arrayList1.get(i)+"\n"
					+"FROM ArrayList2: \n"
					+arrayList2.get(i)+"\n");
		}
		return stringBuilder.toString();
	}
}
