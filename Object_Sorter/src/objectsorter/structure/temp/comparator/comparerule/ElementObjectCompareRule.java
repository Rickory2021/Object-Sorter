package objectsorter.structure.temp.comparator.comparerule;

import objectsorter.structure.temp.comparator.comparerule.CompareRule.CompareType;

public class ElementObjectCompareRule implements CompareRule{
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
	
	private ElementObjectCompareType compareType;
	private OrderType orderType;

	public ElementObjectCompareRule() {
		this.compareType=ElementObjectCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
	}
	
	public ElementObjectCompareRule(ElementObjectCompareType compareType,OrderType orderType) {
		this.compareType=compareType;
		this.orderType=orderType;
	}
	
	@Override
	public CompareType getCompareType() {
		return compareType;
	}

	@Override
	public void setCompareType(CompareType compareType) {
		this.compareType=(ElementObjectCompareType) compareType;
		
	}

	@Override
	public OrderType getOrderType() {
		return orderType;
	}

	@Override
	public void setOrderType(OrderType orderType) {
		this.orderType=orderType;
	}
}
