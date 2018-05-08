package com.jp.tradeapi.fileparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SaleBaseDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;
import com.jp.tradeapi.operationtype.PriceOperationType;
import com.jp.tradeapi.parser.TradeFileParser;
import com.jp.tradeapi.parser.TradeFileParserImpl;

public class TradeFileParserImplTest {
private TradeFileParser tradeFileParser = new TradeFileParserImpl();
@Test
public void testParseSaleInstruction() {
	List<String> tradeInstructions = new ArrayList<>();
	tradeInstructions.add("Orange,1,1.00");
	tradeInstructions.add("Orange,1,2.00");
	tradeInstructions.add("Orange,1,2.00,ADD");
	List<SaleBaseDto> saleBaseDtos = tradeFileParser.parseSaleInstruction(tradeInstructions);
	List<SaleBaseDto> expectedSaleBaseDtos = new ArrayList<>();
	SaleBaseDto firstProductDto = ProductDto.builder().productPrice(new BigDecimal(1.00)).productQuantity(1).build();
	SaleBaseDto secondProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(2.00))
			.productQuantity(1).build();
	SaleBaseDto thirdProductDto = SalePriceChangeDto.builder().priceOperationType(PriceOperationType.ADD)
			.productPrice(new BigDecimal(2.00)).productType("Orange").build();
	expectedSaleBaseDtos.add(firstProductDto);
	expectedSaleBaseDtos.add(secondProductDto);
	expectedSaleBaseDtos.add(thirdProductDto);
	assertEquals(expectedSaleBaseDtos.get(1).getProductType(),saleBaseDtos.get(1).getProductType());
}
@Test
public void testParseSaleInstructionIfEmpty() {
	List<String> tradeInstructions = new ArrayList<>();
	
	List<SaleBaseDto> saleBaseDtos = tradeFileParser.parseSaleInstruction(tradeInstructions);
	assertTrue(saleBaseDtos.isEmpty());
}
}
