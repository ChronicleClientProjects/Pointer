package vanilla.java.pointers;


import vanilla.java.pointers.fields.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.util.*;

public class ClassModel<T> {
    private final Map<String, FieldModel> modelMap = new LinkedHashMap<String, FieldModel>();
    private final Class<T> tClass;
    private final Integer objectSize;
    private final BeanInfo beanInfo;

    public ClassModel(Class<T> tClass) throws IntrospectionException {
        this.tClass = tClass;
        beanInfo = Introspector.getBeanInfo(tClass);
        int objectSize = 0;
        List<FieldModel> fields = new ArrayList<FieldModel>();
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            Method getter = pd.getReadMethod();
            Method setter = pd.getWriteMethod();
            Class type = getter != null ? getter.getReturnType() : setter.getParameterTypes()[0];
            FieldModel fm;
            final String name = pd.getName();
            if (type == boolean.class || type == Boolean.class) {
                fm = new BooleanFieldModel(name);
            } else if (type == byte.class) {
                fm = new ByteFieldModel(name);
            } else if (type == char.class) {
                fm = new CharFieldModel(name);
            } else if (type == short.class) {
                fm = new ShortFieldModel(name);
            } else if (type == int.class) {
                fm = new IntFieldModel(name);
            } else if (type == float.class) {
                fm = new FloatFieldModel(name);
            } else if (type == long.class) {
                fm = new LongFieldModel(name);
            } else if (type == double.class) {
                fm = new DoubleFieldModel(name);
            } else if (Enum.class.isAssignableFrom(type)) {
                fm = new Enum8FieldModel(name, (Enum[]) type.getEnumConstants());
            } else {
                throw new UnsupportedOperationException("Field " + type + " no supported.");
            }
            if (getter != null)
                modelMap.put(getter.getName(), fm);
            if (setter != null)
                modelMap.put(setter.getName(), fm);
            objectSize += fm.fieldSize();
            fields.add(fm);
        }
        Collections.sort(fields, new Comparator<FieldModel>() {
            @Override
            public int compare(FieldModel o1, FieldModel o2) {
                return o1.name().compareTo(o2.name());
            }
        });
        int offset = 0;
        for (FieldModel field : fields) {
            field.offset(offset);
            offset += field.fieldSize();
        }

        this.objectSize = objectSize;
    }

    public static <T> ClassModel<T> of(Class<T> tClass) {
        try {
            return new ClassModel<T>(tClass);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public T createPointer() {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass, PointerInternal.class}, new PointerInvocationHandler());
    }

    private class PointerInvocationHandler implements InvocationHandler {
        public ByteBuffer byteBuffer;
        public Integer index = -1;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(proxy, args);
            }
            final String name = method.getName();
            if (name.startsWith("$") && name.endsWith("$")) {
                if (name.equals("$byteBuffer$")) {
                    byteBuffer = (ByteBuffer) args[0];
                    return null;
                }
                if (name.equals("$index$")) {
                    if (args == null || args.length == 0) {
                        return index;
                    }
                    index = (Integer) args[0];
                    return null;
                }
                if (name.equals("$objectSize$")) {
                    return objectSize;
                }
            }
            // getter
            if (args == null || args.length == 0) {
                FieldModel fm = modelMap.get(name);
                if (fm == null) return null;
                return fm.get(byteBuffer, index * objectSize);
            }
            // setter
            FieldModel fm = modelMap.get(name);
            if (fm == null) return null;
            fm.set(byteBuffer, index * objectSize, args[0]);
            return null;
        }
    }
}
