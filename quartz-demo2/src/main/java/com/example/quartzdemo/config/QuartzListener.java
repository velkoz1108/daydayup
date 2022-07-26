package com.example.quartzdemo.config;


import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuartzListener implements ApplicationListener<ApplicationReadyEvent> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Scheduler scheduler;


	@Value("${system.config.sn:1001}")
	private String sysConfigSn;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			List<Map<String,Object>> qrtzJobs = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<>();

			map.put("cronExpression","0/10 * * * * ?");
			map.put("jobClassName","com.example.quartzdemo.Job.MyJop");
			map.put("jobGroup","sync2");
			map.put("jobName","'自动生成应付结算单'");

			qrtzJobs.add(map);

			List<TriggerKey> trigkeylist = new ArrayList<>();
			if (qrtzJobs != null) {
				for (Map qrtzjob : qrtzJobs) {
					String classname = String.valueOf(qrtzjob.get("jobClassName"));
					if(StringUtils.isBlank(classname)){
						continue;
					}
					String jobname = String.valueOf(qrtzjob.get("jobName"));
					String jbogroup = String.valueOf(qrtzjob.get("jobGroup"));
					String cron = String.valueOf(qrtzjob.get("cronExpression"));
					if (!CronExpression.isValidExpression(cron)) {
						logger.error("任务:" + jbogroup + "." + jobname + "的CronExpression:" + cron + " 设置不正确");
						continue;
					}
					JobKey jobkey = JobKey.jobKey(jobname, jbogroup);
					TriggerKey trigKey = TriggerKey.triggerKey(jobname, jbogroup);
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
					int misfireV = qrtzjob.get("misfireinstruction")!=null?(Integer) qrtzjob.get("misfireinstruction"):0;
					if (misfireV == -1) {
						scheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
					} else if (misfireV == 1) {
						scheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
					} else {
						scheduleBuilder.withMisfireHandlingInstructionDoNothing();
					}
					if (scheduler.checkExists(jobkey)) {
						if (!scheduler.getJobDetail(jobkey).getJobClass().getName().equals(classname)) {
							scheduler.deleteJob(jobkey);
						}
					}
					if (!scheduler.checkExists(jobkey)) {
						JobDetail job = JobBuilder.newJob((Class<? extends Job>) Class.forName(classname))
								.withIdentity(jobkey).build();
						CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(trigKey)
								.withSchedule(scheduleBuilder).build();
						scheduler.scheduleJob(job, trigger);
					} else {
						CronTrigger oldTrig = (CronTrigger) scheduler.getTrigger(trigKey);
						if (!oldTrig.getCronExpression().equals(cron)
								|| oldTrig.getMisfireInstruction() != misfireV) {
							CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(trigKey)
									.withSchedule(scheduleBuilder).build();
							scheduler.rescheduleJob(trigKey, trigger);
						}
						if (scheduler.getTriggerState(trigKey) == Trigger.TriggerState.PAUSED
								|| scheduler.getTriggerState(trigKey) == Trigger.TriggerState.ERROR) {
							scheduler.resumeTrigger(trigKey);
						}
					}
					trigkeylist.add(trigKey);
				}
			}
			Set<TriggerKey> allTriggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
			for (TriggerKey trigk : allTriggerKeys) {
				if (trigkeylist == null || !trigkeylist.contains(trigk)){
					scheduler.pauseTrigger(trigk);
				}
			}
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

}
