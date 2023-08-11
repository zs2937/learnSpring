package com.jirengu.spring.introduction.context;

import com.jirengu.spring.introduction.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MyApplicationContext {

    private static Map<String, Object> beanMap = new HashMap<>();

    private static Map<String, Object> earlyBeanMap = new HashMap<>();

    private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        // 尝试从 beanMap 中获取 bean
        String className = clazz.getName();
        Object obj = beanMap.get(className);
        if (obj != null) {
            return (T) obj;
        }
        T bean = createBeanV2(clazz);
        beanMap.put(className, bean);
        return bean;
    }

    public static <T> T createBean(Class<T> clazz) {
        // 1. 根据接口找到实现类
        // 完整的实现方式是扫描包路径下所有的类，找到实现类
        // 这里我们简单起见，假设实现类跟接口在同一个包下，且实现类名是接口名去掉前缀'I'
        Class implementClass = getImplementClass(clazz);
        // 2. 通过反射调用无参构造函数，创建对象
        Object implementObject;
        try {
            implementObject = implementClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 3. 为创建的对象注入属性
        injectField(implementObject);
        return (T) implementObject;
    }

    private static Class getImplementClass(Class clazz) {
        // 获取接口所在的包名
        String packageName = clazz.getPackage().getName();
        // 获取接口的简单名称
        String interfaceName = clazz.getSimpleName();
        String implementClassName = interfaceName.substring(1);
        // 拼接实现类的全限定名
        String className = packageName + "." + implementClassName;
        try {
            // 加载实现类的 Class 对象
            return Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void injectField(Object obj) {
        Class clazz = obj.getClass();
        // 利用反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 创建字段类型对应的 bean，并为字段赋值
            Class fieldType = field.getType();
            Object fieldValue = createBean(fieldType);
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static <T> T createBeanV2(Class<T> clazz) {
        // 1. 根据接口找到实现类
        // 完整的实现方式是扫描包路径下所有的类，找到实现类
        // 这里我们简单起见，假设实现类跟接口在同一个包下，且实现类名是接口名去掉前缀'I'
        Class implementClass = getImplementClass(clazz);
        // 2. 通过反射调用无参构造函数，创建对象
        Object implementObject;
        try {
            implementObject = implementClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 3. 为创建的对象注入属性
        injectFieldV2(implementObject);
        return (T) implementObject;
    }

    private static void injectFieldV2(Object obj) {
        Class clazz = obj.getClass();
        // 利用反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 创建字段类型对应的 bean，并为字段赋值
            Class fieldType = field.getType();
            Object fieldValue = getBean(fieldType);
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T getBeanV2(Class<T> clazz) {
        // 尝试从 beanMap 中获取 bean
        String className = clazz.getName();
        Object obj = beanMap.get(className);
        if (obj != null) {
            return (T) obj;
        }
        T bean = createBeanV3(clazz);
        beanMap.put(className, bean);
        return bean;
    }

    private static <T> T getBeanWithEarlyBean(Class<T> clazz) {
        // 尝试从 beanMap 中获取 bean
        String className = clazz.getName();
        Object obj = beanMap.get(className);
        if (obj != null) {
            return (T) obj;
        }
        // 尝试从 earlyBeanMap 获取 bean
        obj = earlyBeanMap.get(className);
        if (obj != null) {
            return (T) obj;
        }
        T bean = createBeanV3(clazz);
        beanMap.put(className, bean);
        return bean;
    }

    private static <T> T createBeanV3(Class<T> clazz) {
        // 1. 根据接口找到实现类
        // 完整的实现方式是扫描包路径下所有的类，找到实现类
        // 这里我们简单起见，假设实现类跟接口在同一个包下，且实现类名是接口名去掉前缀'I'
        Class implementClass = getImplementClass(clazz);
        // 2. 通过反射调用无参构造函数，创建对象
        Object implementObject;
        try {
            implementObject = implementClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 3. 将创建出来的对象放入 earlyBeanMap
        String className = clazz.getName();
        earlyBeanMap.put(className, implementObject);
        // 4. 为创建的对象注入属性
        injectFieldV3(implementObject);
        return (T) implementObject;
    }

    private static void injectFieldV3(Object obj) {
        Class clazz = obj.getClass();
        // 利用反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 创建字段类型对应的 bean，并为字段赋值
            Class fieldType = field.getType();
            Object fieldValue = getBeanWithEarlyBean(fieldType);
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
