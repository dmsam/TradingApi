package com.jp.tradeapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.tradeapi.dao.SalesDao;
import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.writer.ReportWriter;

/**
 * Class that implements SaleReportService to generate sale report based on
 * message count
 * 
 * @author doma.samson
 *
 */
@Service
public class SaleReportServiceImpl implements SaleReportService {
	@Autowired
	private ReportWriter reportWriter;
	@Autowired
	private SalesDao salesDao;

	@Override
	public String generateSaleReport(int messageCount) {
		Map<String, List<ProductDto>> saleMapGroupedBySaleType = salesDao.getAllSaleDetails().values().stream()
				.collect(Collectors.groupingBy(ProductDto::getProductType));
		Function<String, String> consolidationMapper = key -> {
			List<ProductDto> productDetails = saleMapGroupedBySaleType.get(key);
			int consolidatedQuantity = productDetails.stream().mapToInt(ProductDto::getProductQuantity).sum();
			BigDecimal consolidatedPrice = productDetails.stream()
					.map(podDto -> podDto.getProductPrice().multiply(new BigDecimal(podDto.getProductQuantity())))
					.reduce(BigDecimal.ZERO.setScale(2) , BigDecimal::add);
			return new StringBuilder(key).append(",").append(consolidatedQuantity).append(",").append(consolidatedPrice)
					.toString();
		};
		String reportText = saleMapGroupedBySaleType.keySet().stream().map(consolidationMapper)
				.collect(Collectors.joining("\n"));
		if (messageCount == 10) {
			System.out.println("10th Message report start");
			System.out.println(reportText);
			System.out.println("10th Message report end");
			reportWriter.writeProcessingReport(reportText, "Report_10_message");
		} else if (messageCount == 20) {
			System.out.println("20th Message report start");
			System.out.println(reportText);
			System.out.println("20th Message report end");
			reportWriter.writeProcessingReport(reportText, "Report_20_message");
		} else if (messageCount == 30) {
			System.out.println("30th Message report start");
			System.out.println(reportText);
			System.out.println("30th Message report end");
			reportWriter.writeProcessingReport(reportText, "Report_30_message");
		} else if (messageCount == 40) {
			System.out.println("40th Message report start");
			System.out.println(reportText);
			System.out.println("40th Message report end");
			reportWriter.writeProcessingReport(reportText, "Report_40_message");

		} else if (messageCount == 50) {

			String adjustReport = salesDao.getAllAdjustmentReport().stream().collect(Collectors.joining("\n"));
			System.out.println("Pausing.50 messages processed and all further messages ignored");
			System.out.println("50th Message report start");
			System.out.println(adjustReport);
			System.out.println("50th Message report end");
			reportWriter.writeProcessingReport(adjustReport, "Report_50_message");
		}
		return null;
	}

}
