package com.bosi.itms.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhz on 2018/6/26.
 */
public class BeanUtils {

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> bean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //System.out.println("|------------------transBean2Map parameter------------------|");
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = null;
                    if(getter==null){
                        value = null;
                    }else{
                        value = getter.invoke(obj);
                    }
                    //System.out.println("key:"+key+",value:"+value);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}
