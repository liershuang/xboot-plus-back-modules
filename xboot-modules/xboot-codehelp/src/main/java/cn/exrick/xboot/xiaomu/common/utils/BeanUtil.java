/**
 * Copyright (C), 2019, 小木
 * FileName: BeanUtil
 * Author:   xiaomu
 * Date:     2019/10/29 17:35
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.common.utils;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtil {

    /**
     * 获取类字段名列表（包含父类字段）
     * @param c
     * @return
     */
    public static List<String> getFieldName(Class c){
        Field[] fieldList = ReflectUtil.getFields(c);
        List<String> fields = new ArrayList<>(fieldList.length);
        for(Field field : fieldList){
            fields.add(field.getName());
        }
        return fields;
    }

    public static List<String> getFieldNameWithout(Class c, String... columns){
        List<String> columnList = Arrays.asList(columns);
        Field[] fieldList = ReflectUtil.getFields(c);
        List<String> fields = new ArrayList<>(fieldList.length);
        for(Field field : fieldList){
            if(columnList.contains(field.getName())) continue;
            fields.add(field.getName());
        }
        return fields;
    }

    public static String camelToUnderScore(String column){
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
        return converter.convert(column);
    }

    public static List<String> camelToUnderScore(List<String> columnList){
        List<String> underScoreList = new ArrayList<>(columnList.size());
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
        for(String column : columnList){
            underScoreList.add(converter.convert(column));
        }
        return underScoreList;
    }
}
