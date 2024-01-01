package objectsorter.structure.temp.comparator;

import objectsorter.structure.temp.Element;
import objectsorter.structure.temp.ElementEnum.CompareType;
import objectsorter.structure.temp.ElementEnum.OrderType;

public class ElementComparator {
	public static <T extends Element> int compare(T element1, T element2, CompareType compareType, OrderType orderType) throws Exception {
		if(element1==null && element2!=null)return 1;
		if(element1==null && element2==null)return 0;
		if(element1!=null && element2==null)return -1;
		if(compareType.getStringRepresentation().equals("NAME")) {
			return element1.getElementName().compareTo(element2.getElementName())*orderType.getIntegerRepresentation();
		}if(compareType.getStringRepresentation().equals("CREATED_DATE")) {
			return element1.getCreatedDate().compareTo(element2.getCreatedDate())*orderType.getIntegerRepresentation();
		}if(compareType.getStringRepresentation().equals("MODIFIED_DATE")) {
			return element1.getLastModifiedDate().compareTo(element2.getLastModifiedDate())*orderType.getIntegerRepresentation();
		}if(compareType.getStringRepresentation().equals("UNIQUE_IDENTIFER")) {
			return element1.getUniqueIndentifer().compareTo(element2.getUniqueIndentifer())*orderType.getIntegerRepresentation();
		}else {
			throw new Exception("Invalid ElementComapreType");
		}
	}
}
