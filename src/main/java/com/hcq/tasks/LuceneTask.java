package com.hcq.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcq.utils.LuceneUtil;

@Component
public class LuceneTask {
	@Scheduled(cron="0 30 11 ? * *")
	public void luceneindex() throws Exception{
		LuceneUtil helloLucene =new LuceneUtil();
		helloLucene.index();
	}
}
