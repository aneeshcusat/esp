package com.chathan.famstack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseFamStackService {
	
	public static Logger logger = null;
	
	public void logDebug(String message) {
		getLogger().debug(message);
	}
	
	public void logInfo(String message) {
		getLogger().info(message);
	}
	
	public void logError(String message) {
		getLogger().error(message);
	}

	public void logTrace(String message) {
		getLogger().trace(message);
	}
	
	public boolean isLogDebugEnabled() {
		return getLogger().isDebugEnabled();
	}
	
	public boolean isLogInfoEnabled() {
		return getLogger().isInfoEnabled();
	}
	
	public Logger getLogger() {
		if (logger == null) {
			logger = LogManager.getLogger(this.getClass());
		}
		return logger;
	}
}
