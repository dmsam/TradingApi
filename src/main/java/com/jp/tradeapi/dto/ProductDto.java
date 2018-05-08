package com.jp.tradeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Subclass Dto that holds product quantity and price change
 * 
 * @author doma.samson
 *
 */
@ToString
public class ProductDto extends SaleBaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Builder
	private ProductDto(String productType, BigDecimal productPrice, int productQuantity) {
		super(productType, productPrice);
		this.productQuantity = productQuantity;
	}
	@Getter
	@Setter
	private int productQuantity;
}