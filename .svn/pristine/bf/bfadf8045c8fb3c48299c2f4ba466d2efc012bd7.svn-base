package com.dj.application.logging;

import org.apache.log4j.Logger;

public class MessageLog {

	private Logger logger;
	private static MessageLog messageLog = null;

	private MessageLog(Logger logger) {
		this.logger = logger;
	}

	/**
	 * prints different types of alert messages 
	 * @param level- set 1 to 6 in IMessage
	 * @param message- Object type of string
	 */
	public void println(int level, Object message) {
		switch (level) {
		case IMessage.DEBUG:
			logger.debug(message);
			break;
		case IMessage.ERROR:
			logger.error(message);
			break;
		case IMessage.FATAL:
			logger.fatal(message);
			break;
		case IMessage.INFO:
			logger.info(message);
			break;
		case IMessage.TRACE:
			logger.trace(message);
			break;
		case IMessage.WARN:
			logger.warn(message);
			break;
		default:
			break;
		}
	}

	/**
	 *  prints different types of alert messages with throwable exception
	 * @param level - set 1 to 6 in IMessage
	 * @param message- Object type of string
	 * @param t - object of Throwable
	 */
	public void println(int level, Object message, Throwable t) {
		switch (level) {
		case IMessage.DEBUG:
			logger.debug(message, t);
			break;
		case IMessage.ERROR:
			logger.error(message, t);
			break;
		case IMessage.FATAL:
			logger.fatal(message, t);
			break;
		case IMessage.INFO:
			logger.info(message, t);
			break;
		case IMessage.TRACE:
			logger.trace(message, t);
			break;
		case IMessage.WARN:
			logger.warn(message, t);
			break;
		default:
			break;
		}
	}

	/**
	 * method returns messageLog
	 * @return messageLog
	 */
	public static MessageLog getLoggerInstance() {
		if(null==messageLog)
			messageLog = new MessageLog(Logger.getRootLogger());
		return messageLog;
	}

	
}
