package com.knight.thread.config;

import java.util.Date;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step0() {
		return stepBuilderFactory.get("wait").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Wait Started :"+new Date());
				Thread.sleep(600000);
				System.out.println("Wait End :"+new Date());
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("After Step");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}
