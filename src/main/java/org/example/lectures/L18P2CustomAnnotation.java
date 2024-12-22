package org.example.lectures;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/*
* Here, int value() is called an annotation member (not a method, although it seems like one)
* We can pass any integer value like an argument when using this annotation (Line 22)
* Here, the return types are restricted to following types
* ____________________________________________________________________
* | Primitive | Class | Strings| Enums | Annotations | Array of these |
* --------------------------------------------------------------------
* */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER) // Can only be applied to parameters
@interface MinAge {
    int value(); // Minimum age
}

class UserService {
    void registerUser(String name, @MinAge(18) int age) {
        System.out.println("User " + name + " registered successfully with age " + age);
    }
}

public class L18P2CustomAnnotation {
    public static void main(String[] args) {
        UserService service = new UserService();

        try {
            invokeWithValidation(service, "registerUser", "Alice", 20); // Valid
            invokeWithValidation(service, "registerUser", "Bob", 16);  // Invalid
        } catch (Exception e) {
            System.err.println("Validation failed: " + e.getMessage());
        }
    }

    // Method to validate and invoke
    public static void invokeWithValidation(Object obj, String methodName, Object... args) throws Exception {
        // Find the method by name and argument count
        Method method = null;
        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.getName().equals(methodName) && m.getParameterCount() == args.length) {
                method = m;
                break;
            }
        }

        if (method == null) {
            throw new NoSuchMethodException("Method " + methodName + " not found.");
        }

        // Validate parameters
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(MinAge.class)) {
                MinAge minAge = parameters[i].getAnnotation(MinAge.class);
                if (args[i] instanceof Integer) {
                    int age = (Integer) args[i];
                    if (age < minAge.value()) {
                        throw new IllegalArgumentException("Parameter " + parameters[i].getName() +
                                " in method " + method.getName() + " must be at least " + minAge.value() +
                                " years old. Found: " + age);
                    }
                } else {
                    throw new IllegalArgumentException("Parameter " + parameters[i].getName() +
                            " in method " + method.getName() + " must be of type int.");
                }
            }
        }

        // Invoke the method if validation passes
        method.invoke(obj, args);
    }
}
