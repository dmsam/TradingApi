package com.jp.tradeapi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Parent class Dto that has product type and product price fields
 * 
 * @author doma.samson
 *
 */
@AllArgsConstructor
public class SaleBaseDto {
	@Getter
	@Setter
	private String productType;
	@Getter
	@Setter
	private BigDecimal productPrice;
}
