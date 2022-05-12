package com.batch.etl.model;

/**
 * 
 * @author ganchoud
 *
 */
public class TradeData {

	private String tradeName;
	private String tradeValue;

	public TradeData() {
	}

	public TradeData(String tradeName, String tradeValue) {
		super();
		this.tradeName = tradeName;
		this.tradeValue = tradeValue;
	}

	@Override
	public String toString() {
		return "TradeData [tradeName=" + tradeName + ", tradeValue=" + tradeValue + "]";
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(String tradeValue) {
		this.tradeValue = tradeValue;
	}

}
