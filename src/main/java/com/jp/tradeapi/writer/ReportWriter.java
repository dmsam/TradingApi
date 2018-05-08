package com.jp.tradeapi.writer;

/**
 * This interface deals with writing report
 * 
 * @author doma.samson
 *
 */
public interface ReportWriter {
	/**
	 * This methods hold logic for saving processing report
	 * 
	 * @param reportText
	 */
	public void writeProcessingReport(String reportText, String reportType);

}
