package com.jp.tradeapi.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SaleBaseDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;
import com.jp.tradeapi.parser.TradeFileParser;
import com.jp.tradeapi.reader.TradeFileReader;
import com.jp.tradeapi.service.CommoditySalesService;
import com.jp.tradeapi.service.SaleReportService;

/**
 * This is main processor class which coordinates processing of input file
 * 
 * @author doma.samson
 *
 */
@Component
public class CommoditySalesProcessor implements TradeProcessingContract {
	@Autowired
	private CommoditySalesService commoditySalesService;
	@Autowired
	private TradeFileReader tradeFileReader;
	@Autowired
	private SaleReportService saleReportService;
	@Autowired
	private TradeFileParser tradeFileParser;

	@Override
	public void processSaleInstruction() {
		List<String> saleInstructions = tradeFileReader
				.readFile("src/main/resources/tradefile/input/SaleInstruction.csv");
		List<SaleBaseDto> saleInstuctionDtos = tradeFileParser.parseSaleInstruction(saleInstructions);
		int count = 1;
		for (SaleBaseDto saleDetails : saleInstuctionDtos) {
			if (count <= 50) {
				if (saleDetails instanceof ProductDto) {
					commoditySalesService.processSalesNotification((ProductDto) saleDetails);
				} else {
					commoditySalesService.processSalePriceChange((SalePriceChangeDto) saleDetails);
				}
				saleReportService.generateSaleReport(count);
				count++;
			} else {
				System.out.println("Pausing.50 messages processed and all further messages ignored");
			}
		}
	}

}
