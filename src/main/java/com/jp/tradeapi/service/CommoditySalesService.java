package com.jp.tradeapi.service;

import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;

/**
 * This interface declares operations to be performed on sale Dtos
 * 
 * @author doma.samson
 *
 */
public interface CommoditySalesService {
	/**
	 * This method processes the ProducDto
	 * 
	 * @param productDto
	 */
	public void processSalesNotification(ProductDto productDto);

	/**
	 * This method processes priceChangeDto
	 * 
	 * @param salePriceChangeDto
	 */
	public void processSalePriceChange(SalePriceChangeDto salePriceChangeDto);
}
