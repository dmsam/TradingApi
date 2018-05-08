package com.jp.tradeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.tradeapi.processor.TradeProcessingContract;

/**
 * This is the main class to invoke program to process and generate sale report
 * 
 * @author doma.samson
 *
 */
@SpringBootApplication
public class TradingApiApplication implements CommandLineRunner {
	@Autowired
	private TradeProcessingContract tradeProcessingContract;

	public static void main(String[] args) {
		SpringApplication.run(TradingApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		tradeProcessingContract.processSaleInstruction();
	}
}
