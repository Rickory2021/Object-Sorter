package objectsorter.structure.temp.comparator.comparerule;

public class ElementCompareRule implements CompareRule{
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
	
	private ElementCompareType compareType;
	private OrderType orderType;
	
	public ElementCompareRule() {
		this.compareType=ElementCompareType.NAME;
		this.orderType=OrderType.ASCENDING;
	}
	
	public ElementCompareRule(ElementCompareType compareType,OrderType orderType) {
		this.compareType=compareType;
		this.orderType=orderType;
	}

	@Override
	public CompareType getCompareType() {
		return compareType;
	}

	@Override
	public void setCompareType(CompareType compareType) {
		this.compareType=(ElementCompareType) compareType;
		
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
