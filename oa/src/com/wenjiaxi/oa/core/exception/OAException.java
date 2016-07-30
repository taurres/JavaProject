package com.wenjiaxi.oa.core.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月19日 上午10:23:48
 * @version 1.0
 */

public class OAException extends RuntimeException {

	
	public OAException() {
		super();
	}

	public OAException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OAException(String message, Throwable cause) {
		super(message, cause);
	}

	public OAException(String message) {
		super(message);
	}

	public OAException(Throwable cause) {
		super(cause);
	}

	

}
