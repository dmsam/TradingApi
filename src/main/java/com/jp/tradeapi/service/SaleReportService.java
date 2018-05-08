package com.jp.tradeapi.service;

/**
 * This interface declares function for generating sale report
 * 
 * @author doma.samson
 *
 */
public interface SaleReportService {
	/**
	 * This method generates sale report based on message count
	 * 
	 * @param messageCount
	 * @return
	 */
	public String generateSaleReport(int messageCount);
}
