package com.wechat.shop.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wechat.shop.service.ScheduledTaskService;

/**
 * 
 * 定时任务类
 * 
 * 定时器设定表达式：
 * 
 * 按顺序依次为
 * 
 * 1.秒（0~59） , - * /
 * 
 * 2.分钟（0~59） , - * /
 * 
 * 3.小时（0~23） , - * /
 * 
 * 4.日（1~31） , - * / L W C
 * 
 * 5.月（1~12） 或者 JAN-DEC , - * /
 * 
 * 6.星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT） , - * / L W #
 * 
 * 7.年份（可选）留空（1970－2099） , - * /
 * 
 * 有些子表达式能包含一些范围或列表
 * 
 * 例如：子表达式（日）可以为 “MON-FRI”，“MON，WED，FRI”，“MON-WED,SAT”
 * 
 * 
 * “*”字符代表所有可能的值
 * 
 * 因此，“*”在子表达式（月）里表示每个月的含义，“*”在子表达式（天（星期））表示星期的每一天
 * 
 * 
 * 
 * “/”字符用来指定数值的增量
 * 
 * 例如：在子表达式（分钟）里的“0/15”表示从第0分钟开始，每15分钟
 * 
 * 在子表达式（分钟）里的“3/20”表示从第3分钟开始，每20分钟（它和“3，23，43”）的含义一样
 * 
 * 
 * “？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值
 * 
 * 当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“？”
 * 
 * 
 * 
 * “L” 字符仅被用于天（月）和天（星期）两个子表达式，它是单词“last”的缩写
 * 
 * 但是它在两个子表达式里的含义是不同的。
 * 
 * 在天（月）子表达式中，“L”表示一个月的最后一天
 * 
 * 在天（星期）自表达式中，“L”表示一个星期的最后一天，也就是SAT
 * 
 * 如果在“L”前有具体的内容，它就具有其他的含义了
 * 
 * 例如：“6L”表示这个月的倒数第６天，“ＦＲＩＬ”表示这个月的最一个星期五
 * 
 * 注意：在使用“L”参数时，不要指定列表或范围，因为这会导致问题
 * 
 * @author zlx
 *
 */
@Component
public class ScheduledTask {
	private static Logger logger = Logger.getLogger(ScheduledTask.class);

	@Autowired
	private ScheduledTaskService scheduledTaskService;

	/**
	 * 每天每隔一小时定期器任务
	 * 
	 * 每隔一小时查询订单表中已过期订单，将其状态改为 ‘已取消’， 过期时间根据订单表中创建时间字段推后三天判断是否过期
	 * 
	 */
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void task_1() {
		try {
			logger.info("----------每天每隔一小时定期器任务 task_1 begin----------");
			scheduledTaskService.updateOverdueOrder();
			logger.info("----------每天每隔一小时定期器任务 task_1 end----------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("----------每天每隔一小时定期器任务 task_1 异常 异常信息为：" + e.toString());
		}
	}

}
