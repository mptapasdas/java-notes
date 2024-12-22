package org.example.lectures;

import java.io.FileNotFoundException;
import java.io.IOException;



/*
*                                          | Throwable |
*                                               |
*           --------------------------------------------------------------------------------------------------------------------------------------
*          |                                                                                                                                     |
*      | Exception |                                                                                                                        | Error |
*           |                                                                                                                                   |
*        ---------------------------------------------------------------------------------------                                 -----------------------------
*       |                                                                                      |                                |                            |
*  | Unchecked / RuntimeException |                                         | Checked / Compile time |             | OutOfMemoryException |   | StackOverflowException |
*       |                                                                         |
*       | ClassCastException |                                                    | ClassNotFoundException |
*
*       | ArithmeticException |                                                   | InterruptedException |
*
*       | NullPointerException |                                                  | SQLException |
*
*       | IllegalArgumentException |                                              | IOException |
*                    |--------->| NumberFormatException |                               |
*                                                                           ------------------------------------------
*       | IndexOfBoundException |                                           |                       |                 |
*               |                                               | FileNotFoundException|   | EOFException |   | SocketException |
*        -------------------------------------
*       |                                    |
*  | ArrayIndexOfBoundException|  | StringIndexOfBoundException |
*
* */


public class L19ExceptionHandling {

   public static void main(String[] args) {
       //ClassCastException
       Object num = 10;
       String val = (String) (num);

       //ArithmeticException
       int dividedByZero = 5 / 0;

       //NumberFormatException
       int formattedNum = Integer.parseInt("tapas");

       method1();
   }

   static void method1() {
       throw new ArithmeticException(); //notice that the compiler is not forcing us to handle the exception
   }

}
//========================== :: CompileTimeException :: =================================================
/*
* throws : It simply tells that I might throw the exception but the caller needs to handle it.
* throw  : To throw any new exception from method (or constructor). You are restricted to throw only the Exception class or its child classes (Refer the hierarchy above)
* catch  : Catch block can only catch exception which can be thrown by try block.
* finally: Finally block can be used after catch or try (try|catch|finally or try|finally)
*          finally block always gets executed until unless there is some JVM related issue like out of memory or system shut down or by forcefully killing the process
*          Even if we return from the try or catch block, finally block will always get executed
*          Used for logging or close the objects (like io)
**/
//In case of compile time exceptions, compiler will FORCE TO HANDLE the exception
class ThrowsKeyword {
    public static void main(String[] args) {
        try {
            method2();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            System.out.println("Finally did something");
        }
    }

    static void method2() throws ClassNotFoundException, IOException {
        method1(5);
    }

    static void method1(int x) throws ClassNotFoundException, IOException { // here it tells the caller to handle the exception
        if(x == 5) {
            throw new ClassNotFoundException();
        } else if(x == 10) {
            throw new IOException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

//In case of huge stack trace, the exception handling is expensive

class NotDivisibleBy10Exception extends RuntimeException {
    NotDivisibleBy10Exception(String message) {
        super(message);
    }
}

class HandleCustomException {
    public static void main(String[] args) {
        try {
            expectingDivisibleBy10NumberOnly(10);
        } catch (NotDivisibleBy10Exception e) {
            System.err.println("Exception");;
        }
    }

    static void expectingDivisibleBy10NumberOnly(int num) {
        if(num % 10 == 0) {
            System.out.println("Valid Input");
        } else {
            throw new NotDivisibleBy10Exception("Invalid Parameter");
        }
    }
}


/*
* As Throwing exception is somewhat expensive, try avoiding exceptions if it can be handled without degrading the code quality
* */