package com.footmark.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.ClassUtil;
import com.footmark.common.util.StringUtil;
import com.footmark.common.util.ValidateUtil;
import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 公共校验工具
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class BaseInfoValidater {

    private static final String ENUM_SCOPE_METHOD = "toEnum";
    private static final String SERIALVERSIONUID = "serialVersionUID";

    public static void checkParam(RequestDto request) throws Exception, BusinessException {
        try {
            Field[] fields = request.getClass().getDeclaredFields();
            for (Field field : fields) {
                Object obj = getFieldValue(field, request); // 1.得到属性值
                check(field, obj); // 2.根据注解检查状态
                if (obj instanceof Collection<?>) { // 3.检查是否是collection
                    if (!isBasicType(field)) { // 4.检查是否是基本数据类型
                        Collection<?> collections = (Collection<?>) obj;
                        for (Object collection : collections) { // 4.循环检查
                            Field[] collectionFields = collection.getClass().getDeclaredFields();
                            for (Field collectionfield : collectionFields) { // 5.检查循环体对象注解状态
                                Object collectionObj = getFieldValue(collectionfield, collection);
                                check(collectionfield, collectionObj);
                            }
                        }
                    }
                }
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据属性字段和属性值检查是否符合注解校验
     * 
     * @param field
     * @param obj
     * @throws Exception
     * @throws BusinessException
     */
    public static void check(Field field, Object obj) throws Exception, BusinessException {
        Validatable valid = field.getAnnotation(Validatable.class);
        if (valid != null) {
            if (!valid.nullable()) {
                // 1.校验是否为空
                checkNullable(field, obj, valid);
                // 2.转换枚举
                Class<?> enumScope = valid.enumScope();
                if (null != enumScope && !enumScope.equals(Void.class)) {
                    if (null == checkValScope(enumScope, obj)) {
                        throw new BusinessException(ExceptionEnum.ENUM_TRANSFER_ERR);
                    }
                }
            }
        }
    }

    // 得到属性值
    private static Object getFieldValue(Field field, Object request) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        if (field.getName().equalsIgnoreCase(SERIALVERSIONUID)) {
            return null;
        }
        Method method = ClassUtil.getMethodByField(field.getName(), request.getClass());
        if (null == method) {
            throw new BusinessException(ExceptionEnum.SYS_ERR, String.format("Could not find getter method[%s]",
                    field.getName()));
        }
        Object obj = method.invoke(request);
        return obj;
    }

    // 检查是否为空
    private static void checkNullable(Field field, Object obj, Validatable valid) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        if (StringUtil.isEmpty(obj)) { // 校验字符串为空
            throw new BusinessException(ExceptionEnum.PARAM_ERR, String.format("%s is null", field.getName()));
        }
        if (obj instanceof Long || obj instanceof Integer) { // 校验数字为空
            if (obj.toString().equals("0")) {
                throw new BusinessException(ExceptionEnum.PARAM_ERR, String.format("%s is null", field.getName()));
            }
        }
        if (valid.isNumber()) { // 嵌套校验是否是数字，前提条件必须不为空
            checkIsNumber(field, obj);
        }
        if (valid.isMobileNo()) { // 嵌套校验是否是手机号
            checkIsMobileNo(field, obj);
        }
    }

    // 检查是否是数字
    private static void checkIsNumber(Field field, Object obj) {
        try {
            Integer.parseInt(obj.toString());
            Long.parseLong(obj.toString());
        } catch (NumberFormatException e) {
            throw new BusinessException(ExceptionEnum.PARAM_ERR, String.format("%s is not number", field.getName()));
        }
    }

    // 检查是否是手机号
    private static void checkIsMobileNo(Field field, Object obj) {
        if (!ValidateUtil.isMobileNO(obj.toString())) {
            throw new BusinessException(ExceptionEnum.PARAM_ERR, String.format("%s is not mobileNo", field.getName()));
        }
    }

    // 得到属性是枚举的字段值
    private static Object checkValScope(Class<?> scopeEnum, Object val) throws Exception {
        if (Enum.class.isAssignableFrom(scopeEnum)) {
            Method toEnumMethod = null;
            try {
                toEnumMethod = scopeEnum.getDeclaredMethod(ENUM_SCOPE_METHOD, String.class);
                return toEnumMethod.invoke(null, String.valueOf(val));
            } catch (NoSuchMethodException e) {
                toEnumMethod = scopeEnum.getDeclaredMethod(ENUM_SCOPE_METHOD, Integer.class);
                return toEnumMethod.invoke(null, Integer.parseInt(String.valueOf(val)));
            }
        }
        throw new RuntimeException("value of scopeEnum is not a valid Enum class.");
    }

    /**
     * 过滤基本类型
     * 
     * @param field
     * @return
     */
    private static boolean isBasicType(Field field) {
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Class<?> fieldListClz = (Class<?>) pt.getActualTypeArguments()[0];
        String fieldName = fieldListClz.getName();
        if (fieldName.equals("java.lang.Double") || fieldName.equals("java.lang.Long")
                || fieldName.equals("java.lang.Integer") || fieldName.equals("java.lang.String")
                || fieldName.equals("java.lang.Float") || fieldName.equals("java.lang.Boolean")
                || fieldName.equals("java.lang.Char") || fieldName.equals("java.lang.Short")
                || fieldName.equals("java.lang.Byte")) {
            return true;
        }
        return false;
    }
}
