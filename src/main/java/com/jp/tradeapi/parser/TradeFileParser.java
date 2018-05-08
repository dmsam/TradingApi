package com.jp.tradeapi.parser;

import java.util.List;

import com.jp.tradeapi.dto.SaleBaseDto;

/**
 * This interface declares function for parsing trade instructions
 * 
 * @author doma.samson
 *
 */
public interface TradeFileParser {
	/**
	 * This method parses the sale instructions received
	 * 
	 * @param tradeInstructions
	 * @return
	 */
	public List<SaleBaseDto> parseSaleInstruction(List<String> tradeInstructions);
}
