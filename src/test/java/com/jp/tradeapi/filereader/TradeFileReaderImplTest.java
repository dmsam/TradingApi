package com.jp.tradeapi.filereader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.jp.tradeapi.basetest.BaseTest;
import com.jp.tradeapi.exception.TradeProcessingException;
import com.jp.tradeapi.reader.TradeFileReader;
import com.jp.tradeapi.reader.TradeFileReaderImpl;

public class TradeFileReaderImplTest extends BaseTest {
private TradeFileReader tradeFileReader = new TradeFileReaderImpl();
	
	/**
	 * This chcecks values loaded from file is matching
	 */
	@Test
	public void testTradeFileRead() {
		List<String> list = tradeFileReader.readFile("src/test/resources/SaleInstruction.csv");
		assertEquals("Orange,1,1.00,",list.get(0));
	}
	/**
	 * This will check whether runtime exception is thrown when it can't fine the file
	 */
	@Test(expected=TradeProcessingException.class)
	public void testTradeFileReadWrongFile() {
		tradeFileReader.readFile("src/test/resources/street10.txt");
	}
}
