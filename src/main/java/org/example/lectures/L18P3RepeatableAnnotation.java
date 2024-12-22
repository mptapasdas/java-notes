package org.example.lectures;


import java.lang.annotation.*;
import java.lang.reflect.Method;

//This was introduced in java 8
//Step 1 :  Create an annotation that you want to be repeated and then put @Repeatable annotation
//Step 2 :  Then create a container annotation that will hold and array of our repeatable annotation
//          The target should be same in both the annotations
@Repeatable(Roles.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Role {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Roles {
    Role[] value();
}

class UserDetailsService {

    @Role("ADMIN")
    @Role("USER")
    public void performSensitiveOperation() {
        System.out.println("Performing a sensitive operation...");
    }
}

public class L18P3RepeatableAnnotation {
    public static void main(String[] args) {
        try {
            Method method = UserDetailsService.class.getMethod("performSensitiveOperation");

            if (method.isAnnotationPresent(Roles.class) || method.isAnnotationPresent(Role.class)) {
                Role[] roles = method.getAnnotationsByType(Role.class);
                System.out.println("Roles allowed to access this method:");
                for (Role role : roles) {
                    System.out.println("- " + role.value());
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
