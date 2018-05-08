package com.jp.tradeapi.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jp.tradeapi.exception.TradeProcessingException;

/**
 * This class implements ReportWriter Interface
 * 
 * @author Doma Samson
 *
 */
@Component
public class ReportWriterImpl implements ReportWriter {
	private static final Logger log = LoggerFactory.getLogger(ReportWriterImpl.class);

	@Override
	public void writeProcessingReport(String reportText, String reportType) {
		Path path = Paths.get(
				new StringBuilder("src/main/resources/tradefile/output/").append(reportType).append(".csv").toString());
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(reportText);
			writer.flush();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new TradeProcessingException("Error in writing report", e);
		}
	}
}
