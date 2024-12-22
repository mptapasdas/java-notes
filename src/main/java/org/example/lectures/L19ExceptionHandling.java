package org.example.lectures;


/*
*                                          | Throwable |
*                                               |
*           --------------------------------------------------------------------------------------------------------------------------------------
*          |                                                                                                                                     |
*      | Exception |                                                                                                                        | Error |
*           |                                                                                                                                   |
*        ---------------------------------------------------------------------------------------                                 -----------------------------
*       |                                                                                      |                                |                            |
*  | Unchecked / Runtime |                                                  | Checked / Compile time |             | OutOfMemoryException |   | StackOverflowException |
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
   }

}
