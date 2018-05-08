package com.jp.tradeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.tradeapi.dao.SalesDao;
import com.jp.tradeapi.dto.ProductDto;
import com.jp.tradeapi.dto.SalePriceChangeDto;

/**
 * Class that implements CommoditySalesService
 * 
 * @author doma.samson
 *
 */
@Service
public class CommoditySalesServiceImpl implements CommoditySalesService {
	@Autowired
	private SalesDao salesDao;

	@Override
	public void processSalesNotification(ProductDto productDto) {
		salesDao.putSaleDetails(productDto);
	}

	@Override
	public void processSalePriceChange(SalePriceChangeDto salePriceChangeDto) {
		switch (salePriceChangeDto.getPriceOperationType()) {
		case ADD:
			salesDao.getAllSaleDetails().values().stream()
					.filter(sale -> sale.getProductType().equals(salePriceChangeDto.getProductType())).forEach(sale -> {
						StringBuilder report = new StringBuilder("ADD on ").append(sale.getProductType())
								.append(" with price ").append(sale.getProductPrice()).append(" changed to ");
						sale.setProductPrice(sale.getProductPrice().add(salePriceChangeDto.getProductPrice()));
						salesDao.putAdjustmentReport(report.append(sale.getProductPrice()).toString());
					});
			break;
		case SUBTRACT:
			salesDao.getAllSaleDetails().values().stream()
					.filter(sale -> sale.getProductType().equals(salePriceChangeDto.getProductType())).forEach(sale -> {
						StringBuilder report = new StringBuilder("SUBTRACT on ").append(sale.getProductType())
								.append(" with price ").append(sale.getProductPrice()).append(" changed to ");
						sale.setProductPrice(sale.getProductPrice().subtract(salePriceChangeDto.getProductPrice()));
						salesDao.putAdjustmentReport(report.append(sale.getProductPrice()).toString());
					});
			break;
		case MULTIPLY:
			salesDao.getAllSaleDetails().values().stream()
					.filter(sale -> sale.getProductType().equals(salePriceChangeDto.getProductType())).forEach(sale -> {
						StringBuilder report = new StringBuilder("MULTIPLY on ").append(sale.getProductType())
								.append(" with price ").append(sale.getProductPrice()).append(" changed to ");
						sale.setProductPrice(sale.getProductPrice().multiply(salePriceChangeDto.getProductPrice()));
						salesDao.putAdjustmentReport(report.append(sale.getProductPrice()).toString());
					});
			break;
		}
		;
	}

}
