package com.wechat.shop.service;

import org.springframework.stereotype.Service;

/**
 * 
 * 定时任务service，此service处理所有定时任务有关业务
 * 
 * @author zlx
 *
 */

@Service
public interface ScheduledTaskService {

	public void updateOverdueOrder() throws Exception;

}
