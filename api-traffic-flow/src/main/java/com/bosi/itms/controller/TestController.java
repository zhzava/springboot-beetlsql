package com.bosi.itms.controller;

import com.bosi.itms.entity.Test;
import com.bosi.itms.service.TestService;
import com.bosi.itms.vo.PageParam;
import com.bosi.itms.utils.pojo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhz on 2018/6/25.
 */
@RestController
@RequestMapping("/test")
@Api("测试接口")
public class TestController {

    @Autowired
    SQLManager sqlManager;

    @Autowired
    private TestService testService;

    @Caching(evict = {@CacheEvict(cacheNames = {"testQuery"},allEntries=true),
            @CacheEvict(cacheNames = {"testQueryPage"},allEntries=true)})
    //@CacheEvict(cacheNames = {"testQuery"}, allEntries=true)//根据名字清除指定缓存，新增对像之后相关查询缓存要清除
    @ApiOperation(value = "新增测试对象",notes="测试例子")
    @PostMapping(value="/save")
    @Transactional
    public R save(Test t){
        t.setId(System.currentTimeMillis() + "000");
        Query<Test> query = sqlManager.query(Test.class);
        query.insert(t);
        //query.insert(t);
        return R.ok("新增测试对象成功！");
    }

    @Caching(evict = {@CacheEvict(cacheNames = {"testQuery"},allEntries=true),
            @CacheEvict(cacheNames = {"testQueryPage"},allEntries=true)})//根据名字清除指定缓存，新增对像之后相关查询缓存要清除
    @ApiOperation(value = "根据主键删除测试对象",notes="测试例子")
    @PostMapping(value="/del/{id}")
    public R del(@PathVariable(name = "id",required = true) String id){
        sqlManager.deleteById(Test.class,id);//此方法只能根据主键删除对像
        return R.ok("删除测试对象成功！");
    }

    @Caching(evict = {@CacheEvict(cacheNames = {"testQuery"},allEntries=true),
            @CacheEvict(cacheNames = {"testQueryPage"},allEntries=true)})//根据名字清除指定缓存，新增对像之后相关查询缓存要清除
    @ApiOperation(value = "更新测试对象",notes="测试例子")
    @PostMapping(value="/update/{id}")
    public R update(@PathVariable(name="id",required=true) String id,Test t){
        //sqlManager.updateAll(Test.class,t);//更新所有数据
        t.setId(id+"");
        sqlManager.updateTemplateById(Test.class,t);
        return R.ok("更新测试对象成功！");
    }

    @ApiOperation(value = "查询列表数据",notes="测试例子")
    @GetMapping(value="/query")
    public R query(Test t) throws Exception {
        List<Test> list = testService.query(t);
        //throw new HttpMessageNotReadableException("测试错误！");
        return R.ok(list);
    }

    @ApiOperation(value = "查询分页列表数据",notes="测试例子")
    @GetMapping(value="/queryPage")
    public R queryPage(PageParam pageParam, Test t){
        PageQuery query = new PageQuery(pageParam.getPageNumber(),pageParam.getPageSize(),t);
        query.setOrderBy(pageParam.getOrderBy());
        query = testService.queryPage(query);
        return R.ok(query);
        //return new ResObject(HttpStatus.OK.value(), "新增成功.");
    }
}
