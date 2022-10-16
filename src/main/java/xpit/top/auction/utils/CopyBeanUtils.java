package xpit.top.auction.utils;

import io.reactivex.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CopyBeanUtils {

    /**
     * @param source      原对象
     * @param targetClass 目标对象class
     * @param <T>
     * @return copy完后的对象
     */
    public static <T> T springCopyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(source, "targetClass must not be null");

        T target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * @param source      原对象
     * @param targetClass 目标对象class
     * @param <T>
     * @return copy完后的对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(source, "targetClass must not be null");

        T target = BeanUtils.instantiateClass(targetClass);
        copyProperties(source, target);
        return target;
    }

    public static void copyProperties(Object source, Object target) throws BeansException {
        copyProperties(source, target, null, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException {
        copyProperties(source, target, editable, (String[]) null);
    }

    /**
     * @param source           原对象
     * @param target           目标对象
     * @param ignoreProperties 排除某些不需要复制的属性
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
        copyProperties(source, target, null, ignoreProperties);
    }

    private static void copyProperties(Object source, Object target, @Nullable Class<?> editable, @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        HashMap<String, String> copySourceNameMap = new HashMap<>();

        for (Field field : actualEditable.getDeclaredFields()) {
            CopySourceName annotation = field.getAnnotation(CopySourceName.class);
            if (annotation != null) {
                copySourceNameMap.put(field.getName(), annotation.value());
            }
        }

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            String name = targetPd.getName();
            String sourceName = copySourceNameMap.get(name);
            if (sourceName != null) name = sourceName;
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(name))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), name);
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + name + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

}

