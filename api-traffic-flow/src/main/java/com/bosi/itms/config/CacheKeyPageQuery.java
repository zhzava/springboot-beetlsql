package com.bosi.itms.config;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by zhz on 2018/6/26.
 * 分页缓存key生成策略
 */

@Configuration
public class CacheKeyPageQuery implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		PageQuery pageQuery = (PageQuery)params[0];

		StringBuilder key = new StringBuilder();
		if(pageQuery.getParas()!=null){
			Map<String,Object> map = new HashMap<>();
			if(pageQuery.getParas().getClass().toString().indexOf("Map") > -1 || pageQuery.getParas().getClass().toString().indexOf("HashMap") > -1)
				map = (Map<String,Object>)(pageQuery.getParas());
			else
				map = com.bosi.itms.utils.BeanUtils.bean2Map(pageQuery.getParas());
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				if(entry.getValue() != null)
					key.append(entry.getValue().toString()+"-");
			}
			
		}else{
			key.append("0"+"-");
		}
		if(pageQuery.getOrderBy() != null)
			key.append(pageQuery.getOrderBy()+"-");
		key.append(pageQuery.getPageSize()+"-");
		key.append(pageQuery.getPageNumber());

		return key.toString();
	}

}
