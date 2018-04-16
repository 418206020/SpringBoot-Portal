package com.micro.boot.app.service.queue;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.common.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class McTopicServiceImpl implements McTopicService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private McUserDao mcUserDao;

}