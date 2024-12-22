package org.example.lectures;

interface Dog {

    default String breathe() {
        return "Dog Breathing";
    }

}
interface Wolf {

    default String breathe() {
        return "Wolf Breathing";
    }

}

class Hyena implements  Dog, Wolf {
    public String breathe() {
        return "Hyena Breathing";
    }
}
// Why a default method was introduced in JAVA 8
// Default method was introduced in java 8 so that if we want to add a function that has same definition
// across all the implementations, then we don't need to add definition to all those. For example the
// Stream method in collection is a default method



/*
* Is there a situation when we will need to provide a definition for the default method
* Yes, refer the above example. Lets say a class is implementing two interfaces which have a default method
* with same signature and same name, then the implementing class need to provide a definition for that default
* method failing which will result in compilation error
*  */

/*
* How to extend an interface with default method
* */
public class L15DefaultStaticPrivate {

}
