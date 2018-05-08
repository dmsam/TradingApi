package com.jp.tradeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.jp.tradeapi.operationtype.PriceOperationType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Subclass Dto that has operation type field
 * 
 * @author doma.samson
 *
 */
@ToString
public class SalePriceChangeDto extends SaleBaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Builder
	private SalePriceChangeDto(String productType, BigDecimal productPrice, PriceOperationType priceOperationType) {
		super(productType, productPrice);
		this.priceOperationType = priceOperationType;
	}

	@Getter
	@Setter
	private PriceOperationType priceOperationType;

}
