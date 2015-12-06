/**
 * @author: yen.nt
 * @created on Nov 17, 2015
 */
package com.footballun.schedule;

import com.footballun.schedule.task.MatchupStatusUpdateTask;
import com.footballun.util.AppConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A Infinite running scheduler for the app.
 * It is kept alive to check any match is about to taking place and then kick-start to match status.
 *  
 * @author yen.nt
 *
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages="com.footballun.schedule.task")
public class MatchupScheduler implements SchedulingConfigurer   {
	
	private static Logger logger = LoggerFactory.getLogger(MatchupScheduler.class);

	@Autowired
	private MatchupStatusUpdateTask matchupStatusUpdateTask;
	
	@Autowired
	private AppConfigure appConfigure;
	
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(2);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        
        // Task 1: Updates matches status against the actual game scheduled (stored on matchup or matchup_detail table)
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override 
                    public void run() {
                        matchupStatusUpdateTask.update();
                    }
                },
                new Trigger() {
					
					@Override
					public Date nextExecutionTime(TriggerContext triggerContext) {
                        Calendar nextExecutionTime =  new GregorianCalendar();
                        Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                        nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                        nextExecutionTime.add(Calendar.HOUR, appConfigure.getMatchStatusTrackerInterval());
                        return nextExecutionTime.getTime();
                    }
                }
        );
        
        // Task 2: 
    }

	
}

