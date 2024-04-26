#include <iostream>
#include <math.h>
#include "BasicCalculator.cpp"
#include "ScientificCalculator.cpp"
#include "Calculator.cpp"

using namespace std;

// function prototypes for calculator object creation
void createBasicCalculator();
void createScientificCalculator();

int main()
{
    // variable to store the choice of the user
    double choice;

    while (choice != 3) // check if option is not quit
    {
        // display menu
        cout << "SELECT MODE:" << endl;
        cout << "\t1. Basic" << endl;
        cout << "\t2. Scientific" << endl;
        cout << "\t3. Quit" << endl;
        cout << "Enter an option: " << endl;
        cin >> choice;

        // handle non numeric input
        if (cin.fail())
        {
            // resets if not a number
            cin.clear();
            cin.ignore(100, '\n');
        }

        // check for options
        if (choice >= 1 && choice <= 3 && floor(choice) == choice)
        {
            switch (int(choice))
            {
            case 1: // basic calculator
                createBasicCalculator();
                break;
            case 2: // basic calculator
                createScientificCalculator();
                break;
            case 3: // quit
                break;
            default:
                cout << "Incorrect option!";
                break;
            }
        }
        else
        {
            cout << "\nIncorrect value. Try again!\n";
            cin.clear();
            continue;
        }
    }
}

// function to create a basic calculator object
void createBasicCalculator()
{
    BasicCalculator c; // create a basic calculator object

    // variable to store the choice of the user
    double choice;

    while (choice != 7) // check if option is not quit
    {
        // display menu
        cout << "\nBasic Calculator Menu:" << endl;
        cout << "\t1. +" << endl;
        cout << "\t2. -" << endl;
        cout << "\t3. *" << endl;
        cout << "\t4. /" << endl;
        cout << "\t5. sqrt(x)" << endl;
        cout << "\t6. x^2" << endl;
        cout << "\t7. Quit to main menu" << endl;
        cout << "Enter an option: ";
        cin >> choice;

        // handle non numeric input
        if (cin.fail())
        {
            cin.clear();
            cin.ignore(100, '\n');
        }

        // check for options
        if (choice >= 1 && choice <= 7 && floor(choice) == choice)
        {
            switch (int(choice))
            {
            case 1: // add
                c.add();
                break;
            case 2: // subtract
                c.subtract();
                break;
            case 3: // multiply
                c.multiply();
                break;
            case 4: // divide
                try // division may cause an error if num2 is 0
                {
                    c.divide();
                }
                catch (DivideByZeroException &e)
                {
                    cout << e.what() << endl;
                }
                break;
            case 5: // square root
                c.squareRoot();
                break;
            case 6: // square
                c.square();
                break;
            case 7:
                return;
                break;
            default:
                cout << "Incorrect option!";
                break;
            }
        }
        else
        {
            cout << "Incorrect value. Try again!\n";
        }
    }
}

// function to create a scientific calculator object
void createScientificCalculator()
{
    ScientificCalculator s; // create a scientific calculator object

    // variable to store the choice of the user
    double choice;

    while (choice != 6) // check if quit
    {
        // display menu
        cout << "\nScientific Calculator Menu:" << endl;
        cout << "\t1. sin" << endl;
        cout << "\t2. cos" << endl;
        cout << "\t3. tan" << endl;
        cout << "\t4. log" << endl;
        cout << "\t5. log10" << endl;
        cout << "\t6. Quit to main menu" << endl;
        cout << "Enter an option: ";
        cin >> choice;

        // handle non numeric input
        if (cin.fail())
        {
            cin.clear();
            cin.ignore(100, '\n');
        }

        // handle numeric value in the defined range
        if (choice >= 1 && choice <= 6 && floor(choice) == choice)
        {
            switch (int(choice))
            {
            case 1: // sin
                s.getSin();
                break;
            case 2: // cos
                s.getCos();
                break;
            case 3: // tan
                s.getTan();
                break;
            case 4: // log
                s.getLog();
                break;
            case 5: // log10
                s.getLog10();
                break;
            case 6:
                return;
                break;
            default:
                cout << "Incorrect option!";
                break;
            }
        }
        else
        {
            cout << "Incorrect value. Try again!\n";
        }
    }
}
