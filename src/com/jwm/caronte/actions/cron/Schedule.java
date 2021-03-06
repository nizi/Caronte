package com.jwm.caronte.actions.cron;
import org.apache.commons.lang.StringUtils;
import org.lainsoft.forge.flow.nav.CommandException;
import org.lainsoft.forge.flow.nav.GenericAction;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.Job.*;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;



public class Schedule extends GenericAction {

String expr = ""; 	
	@Override
	public String execute() throws CommandException {
		expr = "0 ";
		String [] time= param("startfrom").split(":");
		String cronType = param("crontype");
		if(cronType.equals("d")){			
			expr = expr + time[1] + " " + time [0] + " * * "+param("days")+ " *";
		}
		else{
			if(param("freqType").equals("m")){
				expr = expr +time[1]+"/"+ param("freq") + " " + time [0] + "* * *";
			}else{
				expr = expr +time[1] + " " + time [0] +"/"+ param("freq") + "* * *";
			}
		}
		schedule(expr);
		return null;
	}
	
	public void schedule(String expr) { 
	try{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		JobDetail job = newJob(CaronteJob.class)
	      .withIdentity(expr, "group1")
	      .build();							        
        
		Trigger trigger = newTrigger()
		.withIdentity(expr, "group1")
		.withSchedule(cronSchedule(expr))
		.forJob(expr, "group1")
		.build();
		
		scheduler.scheduleJob(job, trigger);
			
		        
	} catch (Exception e) {
		// TODO Auto-generated catch bloc		
		e.printStackTrace();
	}
	}
}