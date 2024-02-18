import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringCollectorApp {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<String> strings = Arrays.asList("org.reflection.stringcollector.StudentBE",
                "org.reflection.stringcollector.StudentFE",
                "org.reflection.stringcollector.StudentQA");

        List<AbstractStudent> studentList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, 3);
            String className = strings.get(index);
            AbstractStudent student = (AbstractStudent) Class.forName(className).getDeclaredConstructor().newInstance();
            studentList.add(student);
        }

        StringBuilder resultStringBuilder = new StringBuilder();

        for (AbstractStudent student : studentList) {
            Annotation classAnnotation = student.getClass().getAnnotation(CustomAnnotation.class);
            if (classAnnotation != null) {
                String classPrefix = ((CustomAnnotation) classAnnotation).value();
                for (Field field : student.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(CustomAnnotation.class)) {
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        Object fieldValue = field.get(student);
                        resultStringBuilder.append(classPrefix).append(fieldName).append(": ").append(fieldValue).append("\n");
                    }
                }
            }
        }

        Result resultObject = new Result();
        Method annotatedMethod = getAnnotatedMethod(resultObject.getClass(), CustomAnnotation.class);
        if (annotatedMethod != null) {
            annotatedMethod.invoke(resultObject, resultStringBuilder.toString());
        }
    }

    private static Method getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }
}
