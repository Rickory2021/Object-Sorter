package objectsorter.structure.temp.comparator.comparerule;

public interface CompareRule {
	public interface CompareType{
		public String getStringRepresentation();
		
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
	
	CompareType getCompareType();
	void setCompareType(CompareType compareType);
	OrderType getOrderType();
	void setOrderType(OrderType orderType);
}
