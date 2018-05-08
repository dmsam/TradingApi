package com.jp.tradeapi.reader;

import java.util.List;

/**
 * This is the reader class to read trade instuction from input csv file from
 * input folder
 * 
 * @author doma.samson
 *
 */
public interface TradeFileReader {
	/**
	 * This method reads trade instructions from an input csv file
	 * 
	 * @return
	 */
	public List<String> readFile(String fileLocation);
}
