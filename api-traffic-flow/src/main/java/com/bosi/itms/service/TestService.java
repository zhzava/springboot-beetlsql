package com.bosi.itms.service;

import com.bosi.itms.entity.Test;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * Created by zhz on 2018/6/26.
 */
public interface TestService {
    List<Test> list();
    List<Test> query(Test t);
    PageQuery queryPage(PageQuery query);
}
