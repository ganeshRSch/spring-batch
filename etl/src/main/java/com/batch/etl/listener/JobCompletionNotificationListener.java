package com.batch.etl.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.batch.etl.model.TradeData;

//@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    /*if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      
      jdbcTemplate.query("SELECT trade_name, trade_value FROM tradedata",
        (rs, row) -> new TradeData(
          rs.getString(1),
          rs.getString(2))
      ).forEach(tradeData -> log.info("Found <" + tradeData + "> in the database."));
    }*/
  }
}
