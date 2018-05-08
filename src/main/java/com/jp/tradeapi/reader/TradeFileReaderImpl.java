package com.jp.tradeapi.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jp.tradeapi.exception.TradeProcessingException;

/**
 * Class that implements TradeFileReader interface and defines readFile function
 * 
 * @author doma.samson
 *
 */
@Component
public class TradeFileReaderImpl implements TradeFileReader {
	private static final Logger log = LoggerFactory.getLogger(TradeFileReaderImpl.class);

	@Override
	public List<String> readFile(String fileLocation) {
		List<String> saleInstructions = null;
		Stream<String> stream = null;
		try {
			saleInstructions = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)
					.collect(Collectors.toList());
			return saleInstructions;
		} catch (IOException ioException) {
			log.error(ioException.getMessage(), ioException);
			throw new TradeProcessingException("Error in reading trade file", ioException);
		} finally {
			if (stream != null)
				stream.close();
		}
	}

}
