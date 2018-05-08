package com.jp.tradeapi.processor;

/**
 * This interface declares function to process sale message read from a file
 * 
 * @author doma.samson
 *
 */
public interface TradeProcessingContract {

	/**
	 * This function processes sale message read from a file
	 */
	public void processSaleInstruction();
}
