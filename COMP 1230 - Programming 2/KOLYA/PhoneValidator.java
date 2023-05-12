package KOLYA;

import java.io.*;
import java.util.Scanner;

public class PhoneValidator {

     public static void main(String[] args) throws IOException {

          // file object where phone numbers will be read from
          File file = new File("KOLYA/input.txt");
          Scanner input = new Scanner(file);

          // files objects where outputs will be printed
          PrintWriter validNumbersFile = new PrintWriter("valid.txt");
          PrintWriter invalidNumbersFile = new PrintWriter("invalid.txt");

          // contains the next phone number as a String from the file
          String nextNumberString;

          // checks if there is a new line in the input file
          while (input.hasNextLine()) {

               // grabs next line and trims it in case of extra white spaces
               nextNumberString = input.nextLine().trim(); 

               try {

                    // grabs location of hyphens
                    int firstHyphen = nextNumberString.indexOf('-');
                    int secondHyphen = nextNumberString.lastIndexOf('-');

                    // checks for the presence of hyphens
                    if (firstHyphen == -1 || secondHyphen == -1) {
                         throw new HyphenException();
                    } else {

                         // grabs the area code of the phone number
                         int areaCode = Integer.parseInt(
                                   nextNumberString.substring(0, 3));

                         // grabs the prefix of the phone number
                         int prefix = Integer.parseInt(
                                   nextNumberString.substring(4, 7));

                         int line = Integer.parseInt(nextNumberString.substring(8));

                         // validates area code
                         if (!checkArea(areaCode)) {
                              throw new AreaException(areaCode);
                         } else {

                              // validates prefix
                              if (!checkPrefix(prefix)) {
                                   throw new PrefixException(prefix);
                              } else {

                                   // validates line
                                   if (!checkLine(line)) {
                                        throw new LineException(Integer.toString(line));
                                   } else {
                                        // found a valid phone number
                                        validNumbersFile.println(nextNumberString);
                                   }

                              }

                         }

                    }

               } catch (HyphenException e) {

                    invalidNumbersFile.println(nextNumberString + "\t" + e.getMessage());

               } catch (NumberFormatException e) {

                    invalidNumbersFile.println(nextNumberString + "\tNumberFormatException " + e.getMessage());

               } catch (AreaException e) {

                    invalidNumbersFile.println(nextNumberString + "\t" + e.getMessage());

               } catch (PrefixException e) {

                    invalidNumbersFile.println(nextNumberString + "\t" + e.getMessage());

               } catch (LineException e) {

                    invalidNumbersFile.println(nextNumberString + "\t" + e.getMessage());

               }

          }

          // closes file objects
          input.close();
          validNumbersFile.close();
          invalidNumbersFile.close();

     }

     // method to validate area code
     public static boolean checkArea(int areaCode) {

          boolean valid;

          switch (areaCode) {

               case 236:
               case 250:
               case 672:
               case 778:
                    valid = true;
                    break;
               default:
                    valid = false;
                    break;

          }

          return valid;

     }

     // method to validate prefix code
     public static boolean checkPrefix(int prefix) {

          boolean valid;

          switch (prefix) {

               case 214:
               case 220:
               case 252:
               case 289:
               case 299:
               case 314:
               case 377:
               case 828:
               case 471:
               case 371:
                    valid = true;
                    break;
               default:
                    valid = false;
                    break;

          }

          return valid;

     }

     // method to validate line code
     public static boolean checkLine(int line) {
          return (line > 999 && line < 10000);
     }

}