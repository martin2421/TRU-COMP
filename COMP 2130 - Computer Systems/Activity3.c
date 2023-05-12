#include <stdio.h>
#include <string.h>

// Function prototype
void inputPersonalInfo();
void printEnrollment();
void printSC();
void printBA();
void printCMNS();
void printCS();
void printSE();
void printLANG();
char inputCourseInfo();
char askAnswer();

// Personal info variables
char firstName[20], lastName[20], email[40], semester[10];
unsigned long long int phone;

// Course info variables
int program, course;
int count = 0;        // keeps track of how many courses to add
char selection[5][9]; // stores the courses selected by the user
float total = 0;      // total tuition

// Other variables
char answer = 'y';
char addCourse;

int main()
{
     // user inputs personal info
     inputPersonalInfo();
     do
     {
          // user inputs course selection
          answer = inputCourseInfo();
     } while (answer == 'y');

     // prints output
     printEnrollment();

     return 0;
}

// prompts user to enter personal information
void inputPersonalInfo()
{

     printf("\nEnter your first name: ");
     scanf("%s", &firstName);

     printf("\nEnter your last name: ");
     scanf("%s", &lastName);

     printf("\nEnter your phone number: +1 ");
     scanf("%llu", &phone);

     printf("\nEnter your email address: ");
     scanf("%s", &email);

     printf("\n(Fall / Winter / Summer)");
     printf("\nWhich course would you like to register for? ");
     scanf("%s", &semester);

     printf("\n");
}

// prompts user to enter course selection
char inputCourseInfo()
{
     printf("\n======================================================\n");
     printf("                   LIST OF PROGRAMS                   \n");
     printf("======================================================\n");
     printf("   1. Science (Biol/Phys/Chem)  4. Computer Science   \n");
     printf("   2. Business Admin.           5. Software Eng.      \n");
     printf("   3. Communications            6. Languages          \n");
     printf("======================================================\n");

     printf("\n(Enter the number)\n");
     printf("Which program would you like to check for courses?: ");
     scanf("%d", &program);
     printf("\n");

     switch (program)
     {
     case 1: // Science
          printSC();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "BIOL 1110");
               count++;
               total += 2000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "BIOL 1592");
               count++;
               total += 2000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "CHEM 1500");
               count++;
               total += 2000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "PHYS 1100");
               count++;
               total += 2000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     case 2: // Business Admin
          printBA();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "ECON 1900");
               count++;
               total += 3000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "ECON 1950");
               count++;
               total += 3000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "MNGT 1710");
               count++;
               total += 3000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "ACCT 2210");
               count++;
               total += 3000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     case 3: // Communications
          printCMNS();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "CMNS 1100");
               count++;
               total += 4000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "CMNS 1150");
               count++;
               total += 4000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "CMNS 1290");
               count++;
               total += 4000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "CMNS 1500");
               count++;
               total += 4000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     case 4: // Computer Science
          printCS();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "COMP 1130");
               count++;
               total += 5000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "COMP 1230");
               count++;
               total += 5000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "COMP 2130");
               count++;
               total += 5000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "COMP 2210");
               count++;
               total += 5000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     case 5: // Software Engineer
          printSE();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "SENG 1110");
               count++;
               total += 6000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "SENG 1210");
               count++;
               total += 6000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "SENG 3110");
               count++;
               total += 6000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "SENG 3210");
               count++;
               total += 6000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     case 6: // Languages
          printLANG();
          printf("\nWhich course would you like? ");
          scanf("%d", &course);
          switch (course)
          {
          case 1:
               strcpy(selection[count], "ENGL 0300");
               count++;
               total += 1000;
               return askAnswer();
               break;
          case 2:
               strcpy(selection[count], "SPAN 1110");
               count++;
               total += 1000;
               return askAnswer();
               break;
          case 3:
               strcpy(selection[count], "FRAN 1110");
               count++;
               total += 1000;
               return askAnswer();
               break;
          case 4:
               strcpy(selection[count], "JAPA 1110");
               count++;
               total += 1000;
               return askAnswer();
               break;

          default:
               printf("Wrong input! Try again...\n");
               return 'n';
               break;
          }
          break;

     default:
          printf("Wrong input! Try again...\n");
          break;
     }
}

void printEnrollment()
{
     printf("\n\n======================================================\n");
     printf("                  STUDENT ENROLLMENT                  \n");
     printf("======================================================\n");

     printf("Student Name: %s %s\n", firstName, lastName);
     printf("Contact Details: +1 %llu \temail: %s\n", phone, email);
     printf("\nCourses Enrolled for the %s semester:\n", semester);

     for (int i = 0; i < sizeof(selection) / sizeof(selection[0]); i++)
     {
          printf("\t%.9s\n", selection[i]);
     }

     printf("Total Tuition: $ %.2f\n", total);

     printf("\nFor fall and winter semesters, before you can register, you must pay the tuition deposit. If you have already paid a tuition deposit prior to the FALL 2023 term, you do not need to pay this deposit again for WINTER. No deposit is required for summer session registration.\n");

     if (strcmp(semester, "Fall") == 0)
     {
          printf("\n* Registration for FALL 2023 opens in JUNE 2023. Deadline is SEPTEMBER 2023.\n");
     }
     else if (strcmp(semester, "Winter") == 0)
     {
          printf("\n* Registration for WINTER 2023 opens in OCTOBER 2023. Deadline is JANUARY 2024.\n");
     }
     else if (strcmp(semester, "Summer") == 0)
     {
          printf("\n* Registration for SUMMER 2023 opens in MARCH 2023. Deadline is MAY 2023.\n");
     }
}

// Helper methods
char askAnswer()
{
     do
     {
          printf("\nDo you wish to add another course? (y/n): ");
          scanf("%c", &addCourse);
     } while (addCourse != 'y' && addCourse != 'n');

     return addCourse;
}

void printSC()
{
     printf("1. BIOL 1110 - Principle of Biology 1 (4 months) - $2,000\n");
     printf("\tStudents examine the molecular basis of cellular processes including energy transfer and the storage and use of genetic information. Prerequisite: Life Sciences 11 or Anatomy & Physiology 12 with a minimum grade of C+ , Chemistry 11 or CHEM 0500.\n");

     printf("2. BIOL 1592 - Human Biology 1 (8 months) - $2,000\n");
     printf("\tStudents examine the anatomy and physiology of human organ systems over the course of two semesters, while focusing on the relationship between structure and function. Prerequisite: Anatomy & Physiology 12 with a minimum grade of C+ or BIOL 0600 and Chemistry 11 or CHEM 0500.\n");

     printf("3. CHEM 1500 - Chemical Bonding & Organic Chem. (4 months) - $2,000\n");
     printf("\tStudents will organize and synthesize existing knowledge of chemical structure, and engage in reflective review of their understanding. Prerequisite: Chemistry 11 or 12; CHEM 0500 or 0600; and Pre-Calculus 12 or MATH 0600/0610.\n");

     printf("4. PHYS 1100 - Fundamental of Physics 1 (4 months) - $2,000\n");
     printf("\tStudents develop a basic understanding of several fields of physics through conceptualization, problem-solving and laboratory exercises. Prerequisite: Pre-calculus 12 or equivalent with a minimum C+ and Physics 11 or equivalent with a minimum C+.\n");
}

void printBA()
{
     printf("1. ECON 1900 - Principles of Microeconomics (4 months) - $3,000\n");
     printf("\tStudents examine the interactions between individuals and firms in various types of markets. Prerequisite: Foundations of Mathematics 11 or Pre-calculus Math 11 with a minimum B OR MATH 0510 or MATH 0530 or equivalent.\n");

     printf("2. ECON 1950 - Principles of Macroeconomics (4 months) - $3,000\n");
     printf("\tStudents examine economic behaviour at the aggregate level, and the measurement and determination of national income. Prerequisite: Foundations of Mathematics 11 or Pre-calculus Math 11 with a minimum B or MATH 0510 or MATH 0530 or equivalent.\n");

     printf("3. MNGT 1710 - Intro to Business (4 months) - $3,000\n");
     printf("\tStudents will simulate, adapt, and respond to a variety of business challenges, expanding their knowledge of business. Prerequisite: English Studies 12/English First Peoples 12 with a minimum of 73%% or equivalent; or ENGL 0600 with minimum C+; or completion of ESAL 0570 and ESAL 0580 with a C+.\n");

     printf("4. ACCT 2210 - Financial Accounting (4 months) - $3,000\n");
     printf("\tStudents develop the skills necessary to prepare and analyze the financial statements of a public corporation. Prerequisite: English Studies 12/ English First Peoples 12 with a minimum of 73%% or equivalent.\n");
}

void printCMNS()
{
     printf("1. CMNS 1100 - Principles of Communication (4 months) - $4,000\n");
     printf("\tStudents in this introductory course explore core theories and principles of non-discursive multimodal media composition. No prerequisite required.\n");

     printf("2. CMNS 1150 - Advertising as Communication (4 months) - $4,000\n");
     printf("\tStudents examine advertising as a form of professional and cultural communication through the lens of communication studies. No prerequisite required.\n");

     printf("3. CMNS 1290 - Intro to Professional Writing (4 months) - $4,000\n");
     printf("\tStudents study the theories and practice of professional organizational communication. No prerequisite required.\n");

     printf("4. CMNS 1500 - Digital Photography (4 months) - $4,000\n");
     printf("\tStudents learn technical and aesthetic theories of photographic composition. No prerequisite required.\n");
}

void printCS()
{
     printf("1. COMP 1130 - Computer Programming 1 (4 months) - $5,000\n");
     printf("\tStudents are introduced to the use of structured problem solving methods, algorithms, structured programming, and object-oriented programming concepts. No prerequisite required.\n");

     printf("2. COMP 1230 - Computer Programming 2 (4 months) - $5,000\n");
     printf("\tStudents are introduced to object oriented programming and continue to develop a disciplined approach to the design, coding and testing of programs. Prerequisite: C or better in COMP 1130 or 1131.\n");

     printf("3. COMP 2130 - Intro to Computer Systems (4 months) - $5,000\n");
     printf("\tStudents are introduced to the concepts of computer architecture, the 'C' and assembly programming languages as well as the use of Linux operating system. Prerequisite: C or better in COMP 1230 or COMP 1231 or COMP 2120.\n");

     printf("4. COMP 2210 - Programming Methods (4 months) - $5,000\n");
     printf("\tStudents are introduced to the programming environments of visual and scripting language along with tools and techniques of software development process. Prerequisite: C or better in COMP 1230 or COMP 1231.\n");
}

void printSE()
{
     printf("1. SENG 1110 - Programming for Engineers 1 (4 months) - $6,000\n");
     printf("\tStudents are introduced to the concepts of computer programming with specific emphasis on engineering problems and applications. Prerequisite: Admission to the Electrical Engineering, Computer Engineering, Software Engineering or Engineering Transfer Programs OR Engineering Program Advisor's permission.\n");

     printf("2. SENG 1210 - Programming for Engineers 2 (4 months) - $6,000\n");
     printf("\tStudents are introduced to the concepts of object-oriented programming in designing, implementing and testing engineering problems. Prerequisite: SENG 1110 with a minimum grade of C.\n");

     printf("3. SENG 3110 - Algorithms and Data Structure (4 months) - $6,000\n");
     printf("\tStudents are introduced to the concepts of evaluating complexity analysis of the algorithms. Prerequisite: CENG 2030 with a minimum grade of C AND STAT 2230 with a minimum grade of C.\n");

     printf("4. SENG 3210 - Applied Software Engineering (4 months) - $6,000\n");
     printf("\tStudents learn various software process models and understand the commonalities and variabilities among them and understand methodologies to assess the software process. Prerequisite: SENG 3110 with a minimum grade of C.\n");
}

void printLANG()
{
     printf("1. ENGL 0300 - Fundamentals of English (4 months) - $1,000\n");
     printf("\tStudents will practice reading and writing skills, and develop basic grammer. No prerequisite required.\n");

     printf("2. SPAN 1110 - Introductory Spanish 1 (4 months) - $1,000\n");
     printf("\tStudents develop cultural knowledge and communication skills in speaking, listening, reading, and writing in modern standard Spanish. No prerequisite required.\n");

     printf("3. FRAN 1110 - Introductory French 1 (4 months) - $1,000\n");
     printf("\tStudents develop cultural knowledge and communicative skills in speaking, listening, reading and writing in modern standard French. No prerequisite required.\n");

     printf("4. JAPA 1110 - Introductory Japanese 1 (4 months) - $1,000\n");
     printf("\tStudents develop cultural knowledge and communicative skills in speaking, listening, reading, and writing in modern standard Japanese. No prerequisite required.\n");
}
