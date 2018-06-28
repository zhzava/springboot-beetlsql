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
 * 普通列表缓存key生成策略
 */
@Configuration
public class CacheKeyQuery implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {

		StringBuilder key = new StringBuilder();
		if(params[0]!=null){
			Map<String,Object> map = new HashMap<>();
			if(params[0].getClass().toString().indexOf("Map") > -1 || params[0].getClass().toString().indexOf("HashMap") > -1)
				map = (Map<String, Object>) params[0];
			else
				map = com.bosi.itms.utils.BeanUtils.bean2Map(params[0]);
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				if(entry.getValue() != null)
					key.append(entry.getValue().toString()+"-");
			}
			
		}else{
			key.append("0"+"-");
		}
		//key.append(pageQuery.getPageSize()+"-");
		//key.append(pageQuery.getPageNumber());
		
		return key.toString();
	}

}
