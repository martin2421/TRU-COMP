/****    
 * Name: Martin Atanacio
 * Student Number: T00684924
 * Assignment Number: 5
 * Due Date:  March 8th, 2022
 * Program Description: Practice developing custom created exception classes,
 * testing all possible exceptions that can occur when reading a file.
 * Creating and working with an input file containg valid and invalid data. Content
 * includes names, student numbers, and program selected. Program will create 2
 * output files (one for valid and one for invalid data), while making use of some 
 * common String methods (i.e length( ), charAt( ), substring( ), trim( )), File 
 * methods (i.e hasNext(), nextLine()), & parsing data using the parseInt() method. 
 ****/

import java.io.*;
import java.util.Scanner;

public class Exceptions {

     public static void main(String[] args) throws IOException {

          // File object that declares the name of the file where the content will
          // be read from
          File file = new File("StudentInfo.txt");

          // declares where will output be printed to: two newly created text
          // files: one for valid and one for invalid data found
          PrintWriter validInputs = new PrintWriter("ValidInputs.txt");
          PrintWriter invalidInputs = new PrintWriter("InvalidInputs.txt");

          // final variables that declare the maximum # of characters for the
          // name substring and for an individual line from the file
          final int NAME_LIMIT = 15, LINE_LIMIT = 20;

          // temporary variables that stores the line and substring containing name
          String line, name;

          // temporary variable that stores the character for the student's program
          char program;

          // temporary variables that store the student number and the number of
          // the line (for accurate error messages)
          int studentNumber = 0, lineNum = 0;

          // tries to read the content of the file object and catches an error in
          // case the file doesn't exist
          try {

               // reads data from file object
               Scanner input = new Scanner(file);

               // checks if there is data to be read from the file
               while (input.hasNext()) {

                    // tries to run the program while catching errors
                    try {

                         // since data was found using the hasNext() method, then
                         // it means there exists a line, thus incrementing the
                         // counter
                         lineNum++;

                         // stores a whole line from the text file
                         line = input.nextLine().trim();

                         // checks for InputTooShortException - line limit
                         if (line.length() != LINE_LIMIT) {

                              throw new InputTooShortException(line, lineNum);

                         } else {

                              // creates a substring for the name part of the input
                              name = line.substring(0, NAME_LIMIT);

                              // checks for InputTooShort exception - name limit
                              if (name.length() < NAME_LIMIT) {
                                   throw new InputTooShortException(line, lineNum);
                              }

                              try {

                                   // creates a substring of the student # from
                                   // the text file and converts it into an integer
                                   
                                   studentNumber = Integer.parseInt(line.substring(
                                             NAME_LIMIT, NAME_LIMIT + 4));

                                   // checks for InvalidStudentNumberException
                                   // 2 issues: needs to be a 4 digit code and it
                                   // needs to be between the range of 3000 - 8000
                                   if ((studentNumber < 1000 ||
                                             studentNumber > 9999) ||
                                             studentNumber < 3000 ||
                                             studentNumber > 8000) {

                                        throw new InvalidStudentNumberException(
                                                  studentNumber, lineNum);

                                   }

                              } catch (NumberFormatException e) {

                                   invalidInputs.println("NumberFormatException:" +
                                             " --> " + line.substring(NAME_LIMIT,
                                                       NAME_LIMIT + 4)
                                             + " <-- : Error on line " + lineNum +
                                             " of text file.\nNot a number!");
                                   invalidInputs.println("----------------------" +
                                             "----------");

                              }
                         }

                         // stores a temporary substring of the last character from
                         // the text file
                         String tempProgram = line.substring(line.length() - 1);

                         // store the character variable from the temp. substring
                         program = tempProgram.charAt(0);

                         // checks for InvalidProgramException
                         if (program != 'C' && program != 'E' && program != 'L' &&
                                   program != 'N') {
                              throw new InvalidProgramException(program,
                                        lineNum);
                         }

                         // Prints content into a validInputs text file
                         validInputs.println(line);
                         validInputs.print("Name = " + name.trim() + ", ");
                         switch (program) {
                              case 'C':
                                   validInputs.print("Program = Computing, ");
                                   break;
                              case 'E':
                                   validInputs.print("Program = Engineering, ");
                                   break;
                              case 'L':
                                   validInputs.print("Program = Law, ");
                                   break;
                              case 'N':
                                   validInputs.print("Program = Nursing, ");
                                   break;
                         }
                         validInputs.println("Student Number = " + studentNumber);
                         validInputs.print("----------------------------------\n");

                    } catch (InputTooShortException short_e) {

                         invalidInputs.println(short_e);
                         invalidInputs.println("--------------------------------");

                    } catch (InvalidProgramException program_e) {

                         invalidInputs.println(program_e);
                         invalidInputs.println("--------------------------------");

                    } catch (InvalidStudentNumberException studentNumber_e) {

                         invalidInputs.println(studentNumber_e);
                         invalidInputs.println("--------------------------------");

                    }

               }

               // close the file
               input.close();

          }

          // catches an error if the text file couldn't be found
          catch (FileNotFoundException e) {
               e.printStackTrace();
               System.out.println("File not found!");
          }

          // close the files
          validInputs.close();
          invalidInputs.close();

     }

}
