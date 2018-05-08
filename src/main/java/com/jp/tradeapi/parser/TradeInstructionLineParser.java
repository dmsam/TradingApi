package com.jp.tradeapi.parser;

import java.math.BigDecimal;

import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SaleBaseDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;
import com.jp.tradeapi.operationtype.PriceOperationType;

/**
 * This class contains logic for parsing each line of instruction in trade file
 * 
 * @author doma.samson
 *
 */
public class TradeInstructionLineParser {
	private TradeInstructionLineParser() {
	}

	/**
	 * This method is used to parse each instruction in trade file
	 * 
	 * @param tradeLine
	 * @return
	 */
	public static SaleBaseDto parseTradeLine(String tradeLine) {
		String[] tradeinfo = tradeLine.split(",");
		if (tradeLine.contains("ADD") || tradeLine.contains("SUBTRACT") || tradeLine.contains("MULTIPLY")) {

			return SalePriceChangeDto.builder().priceOperationType(PriceOperationType.fromName(tradeinfo[3]))
					.productPrice(new BigDecimal(tradeinfo[2])).productType(tradeinfo[0]).build();
		} else {
			return ProductDto.builder().productType(tradeinfo[0]).productPrice(new BigDecimal(tradeinfo[2]))
					.productQuantity(Integer.parseInt(tradeinfo[1])).build();
		}
	}
}
