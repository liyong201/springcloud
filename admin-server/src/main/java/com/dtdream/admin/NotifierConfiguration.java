package com.dtdream.admin;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author lisheng
 * @version V1.0
 * @Title: 为监控的服务添加邮件通知
 * @Package com.dtdream.admin
 * @date 2018年6月19日 上午17:18:13
 */

@Configuration
@EnableScheduling
public class NotifierConfiguration {

    @Resource
    private Notifier notifier;

    @Resource
    private InstanceRepository instanceRepository;

    //服务上线或者下线都通知
    private String[] reminderStatuses = {"DOWN"};

    @Bean
    @Primary
    public RemindingNotifier remindingNotifier() {
        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier,instanceRepository);
        //设定时间，5分钟提醒一次
//        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(5));
        //设定监控服务状态，状态改变为给定值的时候提醒
        remindingNotifier.setReminderStatuses( reminderStatuses );
        return remindingNotifier;
    }

    @Scheduled(fixedRate = 60_000L)
    public void remind() {
        remindingNotifier();
    }
}