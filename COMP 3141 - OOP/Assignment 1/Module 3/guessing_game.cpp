#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main()
{
     // seed for the random number
     srand(time(nullptr));

     // generate a random number between 1 and 100
     int number = rand() % 100 + 1;

     int guess;
     bool found = false;

     cout << "Welcome to the Random Number Guessing Game.\n";
     cout << "Guess the number between 1 and 100.\n";

     do
     {
          // input number
          cout << "Enter guess: ";
          cin >> guess;

          // check guess
          if (guess == number)
          {
               cout << "##### YOU WON #####\n";
               found = true;
          }
          else if (guess < number)
          {
               if (number - guess <= 10)
                    cout << "You are hot on the upper side (+++)\n";
               else
                    cout << "You are too cold on the upper side (+++++)\n";
          }
          else
          {
               if (guess - number <= 10)
                    cout << "You are hot on the lower side (---)\n";
               else
                    cout << "You are too cold on the lower side (-----)\n";
          }
     } while (!found);

     return 0;
}
