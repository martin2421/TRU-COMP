#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib> // exit function prototype
#include "ClientData.cpp"
using namespace std;

void outputLine(ostream &, const ClientData &); // prototype

int main()
{
     // open file for reading
     ifstream inCredit("credit.dat", ios::in | ios::binary);

     // exit program if ifstream cannot open file
     if (!inCredit)
     {
          cerr << "File could not be opened." << endl;
          exit(EXIT_FAILURE);
     }

     ClientData client; // create record

     int choice;
     int r;

     cout << "Select from the options given below \n";
     cout << "1. Read First Record \n";
     cout << "2. Read Last Record \n";
     cout << "3. Read Specific Record \n";
     cout << "4. Quit \n";

     // takes in numeric choice from the user
     cin >> choice;

     // checks if the choice is between 1 and 5
     if (choice >= 1 && choice <= 5)
     {
          cout << "\nYou have selected choice: " << choice << "\n";

          switch (choice)
          {
          case 1:
               r = 1;
               break;
          case 2:
               r = 3;
               break;
          case 3:
               cout << "Enter record to read ";
               cin >> r;
               break;
          case 4:
               break;
          default:
               cout << "Wrong option";
               break;
          }
     }
     else
     {
          cout << "Wrong value, try again \n";
     }

     cout << "Reading random record from the file: " << r << "\n";

     // move file-position pointer to correct record in file
     inCredit.seekg(sizeof(ClientData) * (r - 1), ios::cur);
     // read first record from file
     inCredit.read(reinterpret_cast<char *>(&client),
                   sizeof(ClientData));
     
     cout << left << setw(10) << "Account" << setw(16)
          << "Last Name" << setw(11) << "First Name" << left
          << setw(10) << right << "Balance" << endl;

     outputLine(cout, client);
}

// display single record
void outputLine(ostream &output, const ClientData &record)
{
     output << left << setw(10) << record.getAccountNumber()
            << setw(16) << record.getLastName()
            << setw(11) << record.getFirstName()
            << setw(10) << setprecision(2) << right << fixed
            << showpoint << record.getBalance() << endl;
} // end function outputLine