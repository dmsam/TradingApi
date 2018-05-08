package com.jp.tradeapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.jp.tradeapi.basetest.BaseTest;
import com.jp.tradeapi.dao.SalesDao;
import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.writer.ReportWriter;

public class SalesReportServiceImplTest extends BaseTest{
	
	@InjectMocks
	private SaleReportService salesReportService = new SaleReportServiceImpl();
	
	@Mock
	private SalesDao salesDao;
	
	@Mock
	private ReportWriter reportWriter;
	
	@Test
	public void testGenerateSaleReport() {
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
		salesReportService.generateSaleReport(10);
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Beans,1,2.00");
        sj.add("Orange,2,3.00");
        String reportText = sj.toString();
		verify(reportWriter, times(1)).writeProcessingReport(reportText,"Report_10_message");
	}
}
