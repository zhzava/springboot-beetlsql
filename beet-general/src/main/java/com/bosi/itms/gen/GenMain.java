package com.bosi.itms.gen;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.OracleStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhz on 2018/6/26.
 */
public class GenMain {

    private static Map fileModel = new HashMap<>();

    //主方法
    public static void main(String[] args) throws Exception {
        gen("TEST");
    }

    private static void gen(String tableName) throws Exception {
        fileModel = PropertiesProvider.getProperties();
        ConnectionSource source = ConnectionSourceHelper.getSimple(db.driver, db.url, db.username, db.password);
        DBStyle dbtype = new OracleStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(dbtype,loader,source,nc,new Interceptor[]{new DebugInterceptor()});

        GenConfig config = new GenConfig();
        MapperCodeGen mapperCodeGen = new MapperCodeGen(gen.daoPkg);
        config.codeGens.add(mapperCodeGen);

        sqlManager.genPojoCode(tableName, gen.entityPkg,config);//生成实体类
        sqlManager.genSQLFile(tableName);//生成sql文件
    }

    private static class db{
        private static String driver = fileModel.get("db.driver").toString();
        private static String url = fileModel.get("db.url").toString();
        private static String username = fileModel.get("db.username").toString();
        private static String password = fileModel.get("db.password").toString();
        private db(){
        }
    }

    private static class gen{
        private static String srcPath = fileModel.get("gen.srcPath").toString();
        private static String entityPkg = fileModel.get("gen.entityPkg").toString();
        private static String daoPkg = fileModel.get("gen.daoPkg").toString();
        private static String sqlPath = fileModel.get("gen.sqlPath").toString();
    }
}
