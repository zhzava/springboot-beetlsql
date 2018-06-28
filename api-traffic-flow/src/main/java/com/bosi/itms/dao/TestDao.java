package com.bosi.itms.dao;

import com.bosi.itms.entity.Test;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by zhz on 2018/6/25.
 */
@Repository
public interface TestDao extends BaseMapper<Test>{
    void queryPage(PageQuery query);
}
