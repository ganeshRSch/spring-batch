package com.batch.etl.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.etl.listener.JobCompletionNotificationListener;
import com.batch.etl.model.TradeData;
import com.batch.etl.processor.TradeDataItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	

	@Bean
	public FlatFileItemReader<TradeData> reader() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	    tokenizer.setDelimiter("|");
	    tokenizer.setNames(new String[]{ "tradeName", "tradeValue" });
	    DefaultLineMapper lineMapper = new DefaultLineMapper<TradeData>();
	    lineMapper.setLineTokenizer(tokenizer);
	    
		return new FlatFileItemReaderBuilder<TradeData>().name("tradeDataItemReader")
				.resource(new ClassPathResource("sample-data.dat")).lineTokenizer(tokenizer)
				.fieldSetMapper(new BeanWrapperFieldSetMapper<TradeData>() {
					{
						setTargetType(TradeData.class);
					}
				}).build();
	}

	@Bean
	public TradeDataItemProcessor processor() {
		return new TradeDataItemProcessor();
	}

	
	@Bean
	public JdbcBatchItemWriter<TradeData> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<TradeData>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO tradedata (trade_name, trade_value) VALUES (:tradeName, :tradeValue)").dataSource(dataSource)
				.build();
	}

	/*@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}*/
	
	@Bean
	public Job importUserJob(Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<TradeData> writer) {
		return stepBuilderFactory.get("step1").<TradeData, TradeData>chunk(1000).reader(reader()).processor(processor())
				.writer(writer).build();
	}

}
