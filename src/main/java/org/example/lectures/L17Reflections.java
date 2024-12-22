package org.example.lectures;

//Disclaimer: This is used pretty rarely but may be asked in the interview

//1 -> Can be used to examine the classes, interface, methods, fields during the run time
//2 -> We can also change the behaviour of the class during runtime using reflections

/*
* This the object of this class Class is created by JVM for each class that is compiled
* */

/*When we do class.getMethods() it returns
* only the public methods of that class along with all the methods from the parent class
* Now, to get the private methods, we use class.getDeclaredMethods()
* This provides a list of public as well as private methods FOR THAT CLASS ONLY
* Same is applicable for FIELDS as well
*/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//We can invoke a method using method.invoke(object, ..params)
class Doge {

    String breed;

    public void sleep() {
        System.out.println("Sleeping");
    }
    private void bark() {
        System.out.println("Barking");
    }
}

class GermanShepherd extends Doge {
    String name;

    public void guard(String place) {
        System.out.println("Guarding " + place);
    }

    private void sniff() {

    }
}
public class L17Reflections {

    public static void main(String[] args) {
        Class<Doge> classObjForDoge = Doge.class; // Method1 Using .class

        try {
            Class<?> classObjForDoge2 = Class.forName("org.example.lectures.Doge"); //Method2 using Class.forName("ClassName")
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Doge dogObj = new Doge();

        Class<?> classObjForDoge3 = dogObj.getClass();

        System.out.println(classObjForDoge.getName());
        for(var field: classObjForDoge3.getFields()) {
            System.out.println(field.getName());
        }

// =========================================================================================================
        GermanShepherd gsd = new GermanShepherd();
        Class<GermanShepherd> gsdClassObj = GermanShepherd.class;

        var gsdMethods = gsdClassObj.getMethods();

        System.out.println("Printing methods ======");
        for(var method: gsdMethods) {
            System.out.println(method.getName() + " : " + method.getReturnType());
        }

        System.out.println("Printing declared methods: public + private for that class only");

        var declaredGsdMethods = gsdClassObj.getDeclaredMethods();
        for(var method: declaredGsdMethods) {
            System.out.println(method.getName() + " : " + method.getReturnType());
        }
// =========================================================================================================
        try {
            Method method = gsdClassObj.getMethod("guard", String.class);
            method.invoke(new GermanShepherd(), "Home");
            //we can create the object by doing class.newInstance() but it is deprecated and has been marked for removal
            Object gsdReflectionObj = gsdClassObj.newInstance();
        } catch (NoSuchMethodException e) {
            System.out.println("No such method");
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}

/* Output
org.example.lectures.Doge
Printing methods ======
guard : void
sleep : void
equals : boolean
toString : class java.lang.String
hashCode : int
getClass : class java.lang.Class
notify : void
notifyAll : void
wait : void
wait : void
wait : void
Printing declared methods: public + private for that class only
guard : void
sniff : void
 */