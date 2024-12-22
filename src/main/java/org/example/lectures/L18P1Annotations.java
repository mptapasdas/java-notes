package org.example.lectures;

/*
* It is kind of using META DATA to the class/methods/interfaces/fields/parameters
* We can read this metadata at the run time and based on that add logic
* This metadata is read using REFLECTION
* */

import java.util.ArrayList;
import java.util.List;

//We have two types of annotations (for predefined ones)
/*
* 1 Meta Annotations : Target  | Retention  | Documented  | Inherited  |  Repeatable (Java8)
* 2 Standard Annotations : Deprecated | Override | SuppressedWarnings | FunctionalInterface | SafeVarArgs
* Apart from these, we can also have our own custom annotations
* */
public class L18P1Annotations {
    /*
    * @Deprecated : Meaning is obvious. No improvements will happen for this particular code
    * Usage of alternative newer approach is recommended
    * Can be used over:
    * Constructor, Field, Local Variable, Method, Package, Parameter, Type(class, interface, enum)
    * */

    @Deprecated
    List<Integer> filterOddNumbersV1(List<Integer> numList) {
        int size = numList.size();

        List<Integer> filteredList = new ArrayList<>();

        for (Integer integer : numList) {
            if (integer % 2 == 1) {
                filteredList.add(integer);
            }
        }
        return  filteredList;
    }

    List<Integer> filterOddNumbersV2(List<Integer> numList) {
        return numList.stream()
                .filter(integer -> integer % 2 == 1)
                .toList();
    }
    //In the above example, we should use the v2 method as the v1 method is outdated

    /*
    * @Override : Used to specify if a method is inherited from its parent class/interface
    * Throws compile error if there is a mismatch
    * Can be used over : ONLY METHODS
    * */

    /*
    * @SuppressWarnings : It is used to suppress any compile time warnings
    * Use it safely, could lead to Run time exception if, any valid warning is IGNORED
    * Can be used over: Field, Method, Parameter, Constructor, Local Variable, Type (Class or interface or enum)
    * */

    //Now we can see that there is a compile time warning for the method.
    //We can potentially suppress the warning by adding @SuppressWarnings("deprecation") annotation to suppress the deprecation warning


        @SuppressWarnings({"unchecked", "deprecation", "removal", "preview"}) //alternative @SuppressWarnings({"all"})
        void gettingWarning() {
        }



    /*
    * @FunctionalInterface
    * */

    /*
    * @SafeVarargs
    * This is used to suppress heap pollution warning
    * What is heap pollution : Heap pollution happens when we use list of parameters as varargs to a method
    * Java interprets the varargs as an array of objects like Objects[]
    * So in place of the actual required type, we can give a reference to any kind of variable
    * This leads to heap pollution
    * Along with applying the @SafeVarargs, we will also have to make the method final
    * */

    @SafeVarargs
    final void printSentences(List<String>... texts) {

    }

}

class MetaAnnotations {
    /*
     * @Target : This restricts the other annotations to where they will be used
     * @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
     * */

    /*
     * @Retention({RetentionType.SOURCE, RetentionType.CLASS, RetentionType.RUNTIME})
     * Source :  Annotations will be processed during compilation and will be discarded after compilation
     *           That means annotations will not be recorded in .class file
     *
     * Class  :  Annotations are to be recorded in the class file by the compiler
     *           but need not be retained by the VM at run time.  This is the default
     *           behavior.
     *
     * Runtime : Annotations are to be recorded in the class file by the compiler and retained by the VM at run time,
     *           so they may be read reflectively.
     * */

    /*
     * @Documented: By default, Annotations are ignored when Java Documentation is generated.
     *              With this meta-annotation even Annotations will come in Java Docs.
     * */

    /*
     * @Inherited: By default, annotations are not inherited to the child class
     *             With this meta-annotations, annotations will be inherited to the child class as well
     * */
}
