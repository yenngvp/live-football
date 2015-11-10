/**
 * @author: yen.nt
 * @created on Nov 9, 2015
 */
package com.footballun.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yen.nt
 *
 */
@Aspect
public class LivescoreAspect {

	final Logger logger = LoggerFactory.getLogger("LivescoreAspect");
	
//	@Before("execution(* *.*(..))")
//	public void beforeControllerMethod(JoinPoint joinPoint) throws Throwable {
//		logger.error("Invoke Something: " );
//	}

	@After("execution(* com.footballun.repository.springdatajpa.MatchupLiveRepositoryJpa.save(..))")
	public void afterPersistMatchupLiveEvent(JoinPoint joinPoint) {
		logger.error("MatchupLiveRepositoryJpa.save: ");
	}
	
	@After("execution(* com.footballun.repository.MatchupLiveRepository.save(..))")
	public void afterPersistMatchupLive(JoinPoint joinPoint) {
		logger.error("MatchupLiveRepositoryJpa.save: ");
	}
}
