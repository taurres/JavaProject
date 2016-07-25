package com.wenjiaxi.oa.core.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * @author WEN JIAXI
 * @date 2016年7月24日 下午6:38:49
 * @version 1.0
 */

@Component("logAdvice")
public class LogAdvice {
	
	private Logger logger = Logger.getLogger(LogAdvice.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	//操作日志记录
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		long beginMillis = System.currentTimeMillis();
		logger.info(sdf.format(new Date(beginMillis))+" 开始调用 "+pjp.getSignature().getName()+" 方法");
		Object obj = pjp.proceed(pjp.getArgs());
		long endMillis = System.currentTimeMillis();
		logger.info(sdf.format(new Date(beginMillis))+" 结束调用 "+pjp.getSignature().getName()+" 方法");
		logger.info("该方法耗时 "+(endMillis - beginMillis)+" ms");
		return obj;
	}
	
	//错误日志记录
	public void error(JoinPoint jp, Throwable e){
		logger.error("调用"+jp.getSignature().getName()+"方法");
		logger.error(e.getMessage(),e);
	}
	
}
