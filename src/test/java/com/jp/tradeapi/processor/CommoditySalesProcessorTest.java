package com.jp.tradeapi.processor;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jp.tradeapi.basetest.BaseTest;
import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SaleBaseDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;
import com.jp.tradeapi.operationtype.PriceOperationType;
import com.jp.tradeapi.parser.TradeFileParser;
import com.jp.tradeapi.reader.TradeFileReader;
import com.jp.tradeapi.service.CommoditySalesService;
import com.jp.tradeapi.service.SaleReportService;

public class CommoditySalesProcessorTest extends BaseTest{
	@Before
	public void prepare() {
		MockitoAnnotations.initMocks(this);
	}
	@InjectMocks
	private CommoditySalesProcessor CommoditySalesProcessor =  new CommoditySalesProcessor();
	
	@Mock
	private CommoditySalesService commoditySalesService;
	
	@Mock
	private TradeFileReader tradeFileReader;
	
	@Mock
	private SaleReportService saleReportService;
	
	@Mock
	private TradeFileParser tradeFileParser;
	
	@Test
	public void testProcessSaleInstructionInvokesParseSaleInstructionofFileParser() {
		List<String> tradeInstructions = new ArrayList<>();
		tradeInstructions.add("Orange,1,1.00");
		tradeInstructions.add("Orange,1,2.00");
		//tradeInstructions.add("Orange,1,2.00,ADD");
		when(tradeFileReader.readFile(anyString())).thenReturn(tradeInstructions);
		CommoditySalesProcessor.processSaleInstruction();
		verify(tradeFileParser, times(1)).parseSaleInstruction(tradeInstructions);
	}

	@Test
	public void testProcessSaleInstructionInvokesprocessSalesNotificationIfProductDtoIsInput() {
		List<String> tradeInstructions = new ArrayList<>();
		tradeInstructions.add("Orange,1,1.00");
		tradeInstructions.add("Orange,1,2.00");
		
		List<SaleBaseDto> expectedSaleBaseDtos = new ArrayList<>();
		ProductDto firstProductDto = ProductDto.builder().productPrice(new BigDecimal(1.00)).productQuantity(1).build();
		ProductDto secondProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		expectedSaleBaseDtos.add(firstProductDto);
		expectedSaleBaseDtos.add(secondProductDto);
		
		when(tradeFileReader.readFile(anyString())).thenReturn(tradeInstructions);
		when(tradeFileParser.parseSaleInstruction(tradeInstructions)).thenReturn(expectedSaleBaseDtos);
		CommoditySalesProcessor.processSaleInstruction();
		verify(commoditySalesService, times(1)).processSalesNotification(firstProductDto);
		verify(commoditySalesService, times(1)).processSalesNotification(secondProductDto);
	}

	@Test
	public void testProcessSaleInstructionInvokesprocessSalesNotificationIfSalePriceChangeDtoIsInput() {
		List<String> tradeInstructions = new ArrayList<>();
		tradeInstructions.add("Orange,1,1.00");
		tradeInstructions.add("Orange,1,2.00");
		tradeInstructions.add("Orange,1,2.00,ADD");
		
		List<SaleBaseDto> expectedSaleBaseDtos = new ArrayList<>();
		ProductDto firstProductDto = ProductDto.builder().productPrice(new BigDecimal(1.00)).productQuantity(1).build();
		ProductDto secondProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		SalePriceChangeDto thirdProductDto = SalePriceChangeDto.builder().priceOperationType(PriceOperationType.ADD)
				.productPrice(new BigDecimal(2.00)).productType("Orange").build();
		expectedSaleBaseDtos.add(firstProductDto);
		expectedSaleBaseDtos.add(secondProductDto);
		expectedSaleBaseDtos.add(thirdProductDto);
		
		when(tradeFileReader.readFile(anyString())).thenReturn(tradeInstructions);
		when(tradeFileParser.parseSaleInstruction(tradeInstructions)).thenReturn(expectedSaleBaseDtos);
		CommoditySalesProcessor.processSaleInstruction();
		verify(commoditySalesService, times(1)).processSalePriceChange(thirdProductDto);
	}

	@Test
	public void testProcessSaleInstructionInvokesgenerateSaleReportOnceProcessingCompletes() {
		List<String> tradeInstructions = new ArrayList<>();
		tradeInstructions.add("Orange,1,1.00");
		tradeInstructions.add("Orange,1,2.00");
		tradeInstructions.add("Orange,1,2.00,ADD");
		tradeInstructions.add("Beans,1,2.00");
		tradeInstructions.add("Beans,1,1,MULTIPLY");
		
		List<SaleBaseDto> expectedSaleBaseDtos = new ArrayList<>();
		ProductDto firstProductDto = ProductDto.builder().productPrice(new BigDecimal(1.00)).productQuantity(1).build();
		ProductDto secondProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		SalePriceChangeDto thirdProductDto = SalePriceChangeDto.builder().priceOperationType(PriceOperationType.ADD)
				.productPrice(new BigDecimal(2.00)).productType("Orange").build();
		ProductDto fourthProductDto = ProductDto.builder().productType("Beans").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		SalePriceChangeDto fifthProductDto = SalePriceChangeDto.builder().priceOperationType(PriceOperationType.MULTIPLY)
				.productPrice(new BigDecimal(1.00)).productType("Beans").build();
		expectedSaleBaseDtos.add(firstProductDto);
		expectedSaleBaseDtos.add(secondProductDto);
		expectedSaleBaseDtos.add(thirdProductDto);
		expectedSaleBaseDtos.add(fourthProductDto);
		expectedSaleBaseDtos.add(fifthProductDto);
		
		when(tradeFileReader.readFile(anyString())).thenReturn(tradeInstructions);
		when(tradeFileParser.parseSaleInstruction(tradeInstructions)).thenReturn(expectedSaleBaseDtos);
		CommoditySalesProcessor.processSaleInstruction();
		verify(saleReportService, times(1)).generateSaleReport(5);
	}
}
