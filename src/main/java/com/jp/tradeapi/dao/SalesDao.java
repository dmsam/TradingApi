package com.jp.tradeapi.dao;

import java.util.List;
import java.util.Map;

import com.jp.tradeapi.dto.ProductDto;

/**
 * This interface declares standard operations to be performed on sales
 * information
 * 
 * @author doma.samson
 *
 */
public interface SalesDao {
	/**
	 * This method add adjustment report text
	 * 
	 * @param reportText
	 */
	public void putAdjustmentReport(String reportText);

	/**
	 * This method returns all adjustment reports
	 * 
	 * @return
	 */
	public List<String> getAllAdjustmentReport();

	/**
	 * this method returns all sales details
	 * 
	 * @return
	 */
	public Map<Integer, ProductDto> getAllSaleDetails();

	/**
	 * this method saves product sale details
	 * 
	 * @param product
	 * @return
	 */
	public Map<Integer, ProductDto> putSaleDetails(ProductDto product);

}
