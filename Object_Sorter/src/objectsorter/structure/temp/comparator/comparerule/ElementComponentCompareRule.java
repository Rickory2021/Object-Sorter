package objectsorter.structure.temp.comparator.comparerule;

public class ElementComponentCompareRule implements CompareRule{
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
	
	private ElementComponentCompareType compareType;
	private OrderType orderType;
	
	public ElementComponentCompareRule() {
		this.compareType=ElementComponentCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
	}
	
	public ElementComponentCompareRule(ElementComponentCompareType compareType,OrderType orderType) {
		this.compareType=compareType;
		this.orderType=orderType;
	}

	@Override
	public CompareType getCompareType() {
		return compareType;
	}

	@Override
	public void setCompareType(CompareType compareType) {
		this.compareType=(ElementComponentCompareType) compareType;
		
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
