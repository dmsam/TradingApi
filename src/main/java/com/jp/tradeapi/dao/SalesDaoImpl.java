package com.jp.tradeapi.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jp.tradeapi.dto.ProductDto;

/**
 * Class that implements SalesDao interface which is responsible to save and
 * access sales information
 * 
 * @author doma.samson
 *
 */
@Component
public class SalesDaoImpl implements SalesDao {

	private SalesDaoImpl() {
	}

	private Map<Integer, ProductDto> saleDetailsMap = null;
	private List<String> saleAdjustmentReport = null;
	{
		saleDetailsMap = new HashMap<Integer, ProductDto>();
		saleAdjustmentReport = new LinkedList<>();
	}

	@Override
	public List<String> getAllAdjustmentReport() {
		return saleAdjustmentReport;
	}

	@Override
	public void putAdjustmentReport(String reportText) {
		saleAdjustmentReport.add(reportText);
	}

	@Override
	public Map<Integer, ProductDto> getAllSaleDetails() {
		return saleDetailsMap;
	}

	@Override
	public Map<Integer, ProductDto> putSaleDetails(ProductDto product) {
		Optional<Integer> latestKey = saleDetailsMap.keySet().stream().max(Comparator.naturalOrder());
		if (latestKey.isPresent()) {
			saleDetailsMap.put(latestKey.get() + 1, product);
		} else {
			saleDetailsMap.put(1, product);
		}
		return saleDetailsMap;
	}

}
