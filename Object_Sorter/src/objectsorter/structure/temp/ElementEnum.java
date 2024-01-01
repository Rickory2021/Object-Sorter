package objectsorter.structure.temp;

public class ElementEnum {
	public interface CompareType{
		public String getStringRepresentation();
		
	}
	
	public enum ElementCompareType implements CompareType{
		NAME("NAME"),
		CREATED_DATE("CREATED_DATE"),
		MODIFIED_DATE("MODIFIED_DATE"),
		UNIQUE_IDENTIFER("UNIQUE_IDENTIFER");

		private String stringRepresentation;
		
		ElementCompareType(String stringRepresentation) {
			// TODO Auto-generated constructor stub
			this.stringRepresentation=stringRepresentation;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
	}
	
	public enum ElementComponentCompareType implements CompareType{
		NAME("NAME"),
		CREATED_DATE("CREATED_DATE"),
		MODIFIED_DATE("MODIFIED_DATE"),
		UNIQUE_IDENTIFER("UNIQUE_IDENTIFER"),
		INFO_COMPARISION("INFO_COMPARISION");

		private String stringRepresentation;
		
		ElementComponentCompareType(String stringRepresentation) {
			// TODO Auto-generated constructor stub
			this.stringRepresentation=stringRepresentation;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
	}
	
	public enum ElementObjectCompareType implements CompareType{
		NAME("NAME"),
		CREATED_DATE("CREATED_DATE"),
		MODIFIED_DATE("MODIFIED_DATE"),
		UNIQUE_IDENTIFER("UNIQUE_IDENTIFER"),
		INTERNAL_INFO("INTERNAL_INFO");

		private String stringRepresentation;
		
		ElementObjectCompareType(String stringRepresentation) {
			// TODO Auto-generated constructor stub
			this.stringRepresentation=stringRepresentation;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
	}
	
	public enum ElementObjectInternalListSearchType implements CompareType{
		NULL("NULL"),
		DEFAULT_COMPARATOR_FIRST("DEFAULT_COMPARATOR_FIRST"),
		DEFAULT_COMPARATOR_LAST("DEFAULT_COMPARATOR_LAST");
		//CUSTOM_COMPARATOR_FIRST("CUSTOM_COMPARATOR_FIRST"),
		//CUSTOM_COMPARATOR_LAST("CUSTOM_COMPARATOR_LAST");

		private String stringRepresentation;
		
		ElementObjectInternalListSearchType(String stringRepresentation) {
			// TODO Auto-generated constructor stub
			this.stringRepresentation=stringRepresentation;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
	}
	
	public enum ElementComponentListType{
		INTEGER("INTEGER"),
		DOUBLE("DOUBLE"),
		STRING("STRING"),
		UNKNOWN("UNKNOWN");

		private String stringRepresentation;
		
		ElementComponentListType(String stringRepresentation) {
			// TODO Auto-generated constructor stub
			this.stringRepresentation=stringRepresentation;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
	}

	public enum OrderType{
		ASCENDING(1),
		DESCENDING(-1);

		private int integerRepresentation;
		
		OrderType(int integerRepresentation) {
			// TODO Auto-generated constructor stub
			this.integerRepresentation=integerRepresentation;
		}
		
		public int getIntegerRepresentation() {
			return integerRepresentation;
		}
	}
	
	public enum StructureType{
		DUPLICATE,
		UNIQUEFINAL,
		UNQIUEOVERRIDE;
	}
}
