package com.jp.tradeapi.operationtype;

/**
 * This contains all supported operations which can be performed on sale price
 * 
 * @author doma.samson
 *
 */
public enum PriceOperationType {
	ADD("ADD"), SUBTRACT("SUBTRACT"), MULTIPLY("MULTIPLY");
	private final String name;

	private PriceOperationType(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}

	/**
	 * This method to get enum from string
	 * 
	 * @param name
	 * @return
	 */
	public static PriceOperationType fromName(String name) {
		if (ADD.name().equals(name)) {
			return ADD;
		} else if (SUBTRACT.name().equals(name)) {
			return SUBTRACT;
		} else if (MULTIPLY.name().equals(name)) {
			return MULTIPLY;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}

}
