package com.wechat.shop.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wechat.shop.mapper.ScheduledTaskMapper;
import com.wechat.shop.service.ScheduledTaskService;

//此类使用注解所有方法启用事务
@Transactional
@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {
	private Logger logger = Logger.getLogger(ScheduledTaskServiceImpl.class);

	@Autowired
	private ScheduledTaskMapper scheduledTaskMapper;
	
	@Override
	public void updateOverdueOrder() throws Exception {
		scheduledTaskMapper.updateOrderType();
	}


}
