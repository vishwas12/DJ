package com.dj.application.cron;

import java.io.IOException;

import com.dj.application.logging.IMessage;
import com.dj.application.logging.MessageLog;


public class BaseCron {
	private static final MessageLog logger = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "BaseCron.";
	
	public void execute() throws IOException, Exception{
		String cronName = this.getClass().getName();
		logger.println(IMessage.INFO, new StringBuilder(CLASS_NAME).append("execute() cronName :: ").append(cronName));
		this.executeCron();
	}
	
	public void executeCron() throws IOException, Exception  {
		
	}
}
