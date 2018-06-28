package com.bosi.itms.service.impl;

import com.bosi.itms.dao.TestDao;
import com.bosi.itms.entity.Test;
import com.bosi.itms.service.TestService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhz on 2018/6/25.
 */
@Service
public class TestServiceImpl implements TestService{

    @Resource
    private TestDao testDao;

    @Autowired
    SQLManager sqlManager;

    @Override
    public List<Test> list(){
        return sqlManager.query(Test.class).select();
    };

    @Override
    @Cacheable(cacheNames = "testQuery",keyGenerator = "cacheKeyQuery")
    public List<Test> query(Test t){
        return sqlManager.template(t);
    }

    @Override
    @Cacheable(cacheNames = "testQueryPage",keyGenerator = "cacheKeyPageQuery")
    public PageQuery queryPage(PageQuery query){
        testDao.queryPage(query);
        return query;
    }

}
