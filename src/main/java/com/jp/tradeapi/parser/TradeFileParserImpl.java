package com.jp.tradeapi.parser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jp.tradeapi.dto.SaleBaseDto;

/**
 * Class that implements TradeFileParser interface and defines the method for
 * parsing sale instructions
 * 
 * @author doma.samson
 *
 */
@Component
public class TradeFileParserImpl implements TradeFileParser {
	private static final Logger log = LoggerFactory.getLogger(TradeFileParserImpl.class);

	@Override
	public List<SaleBaseDto> parseSaleInstruction(List<String> tradeInstructions) {
		if (tradeInstructions != null && !tradeInstructions.isEmpty()) {
			return tradeInstructions.stream().map(TradeInstructionLineParser::parseTradeLine)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

}
