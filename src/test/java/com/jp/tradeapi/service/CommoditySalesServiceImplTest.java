package com.jp.tradeapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jp.tradeapi.basetest.BaseTest;
import com.jp.tradeapi.dao.SalesDao;
import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;
import com.jp.tradeapi.operationtype.PriceOperationType;

public class CommoditySalesServiceImplTest extends BaseTest{
	
	@InjectMocks
	private CommoditySalesService commoditySalesService = new CommoditySalesServiceImpl();
	
	@Mock
	private SalesDao salesDao;
	
	@Test
	public void testProcessSalesNotificationInvokesPutSalesDetailsofSalesDao() {
		ProductDto productDto = ProductDto.builder().productPrice(new BigDecimal(1.00)).productQuantity(1).build();
		commoditySalesService.processSalesNotification(productDto);
		verify(salesDao, times(1)).putSaleDetails(productDto);
	}
	@Test
	public void testProcessSalesNotificationInvokesputAdjustmentReportofSalesDao() {
		ProductDto firstProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(1.00)).productQuantity(1).build();
		ProductDto secondProductDto = ProductDto.builder().productType("Orange").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		ProductDto thirdProductDto = ProductDto.builder().productType("Beans").productPrice(new BigDecimal(2.00))
				.productQuantity(1).build();
		Map<Integer, ProductDto> saleDetailsMap = new HashMap<Integer, ProductDto>();
		saleDetailsMap.put(1, firstProductDto);
		saleDetailsMap.put(2, secondProductDto);
		saleDetailsMap.put(3, thirdProductDto);
		when(salesDao.getAllSaleDetails()).thenReturn(saleDetailsMap);
		SalePriceChangeDto salePriceChangeDto = SalePriceChangeDto.builder().priceOperationType(PriceOperationType.ADD)
				.productPrice(new BigDecimal(2.00)).productType("Orange").build();
		StringBuilder report = new StringBuilder("ADD on ").append(firstProductDto.getProductType())
				.append(" with price ").append(firstProductDto.getProductPrice()).append(" changed to ");
		commoditySalesService.processSalePriceChange(salePriceChangeDto);
		verify(salesDao, times(1)).putAdjustmentReport(report.append(3).toString());
	}
}
