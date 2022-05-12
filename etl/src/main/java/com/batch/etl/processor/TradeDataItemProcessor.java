package com.batch.etl.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.batch.etl.model.TradeData;

public class TradeDataItemProcessor implements ItemProcessor<TradeData, TradeData> {

	private static final Logger log = LoggerFactory.getLogger(TradeDataItemProcessor.class);

	public TradeData process(final TradeData tradeData) throws Exception {
		/*final String tradeName = tradeData.getTradeName().toUpperCase();
		final String tradeValue= tradeData.getTradeValue().toUpperCase();*/

		final TradeData transformedData = new TradeData(tradeData.getTradeName(), tradeData.getTradeValue());

		log.info("Converting (" + tradeData + ") into (" + transformedData + ")");

		return transformedData;
	}


}