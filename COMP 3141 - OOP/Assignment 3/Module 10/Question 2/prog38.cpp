#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib> // exit function prototype
#include "ClientData.cpp"
using namespace std;

void outputLine(ostream &, const ClientData &); // prototype
void readSequentially();
void readRandomly();

int main()
{
     // open file for writing
     ofstream outCredit("accounts.dat", ios::out | ios::binary);

     // exit program if ofstream cannot open file
     if (!outCredit)
     {
          cerr << "File could not be opened." << endl;
          exit(EXIT_FAILURE);
     }

     int account;
     char lastname[15];
     char firstname[15];
     double balance;
     ClientData clients[5];

     cout << "Enter the information for 5 accounts" << endl;
     cout << "Enter account, lastname, firstname and balance.\n";

     for (int i = 0; i < 5; i++)
     {
          cin >> account >> lastname >> firstname >> balance;
          clients[i].setAccountNumber(account);
          clients[i].setLastName(lastname);
          clients[i].setFirstName(firstname);
          clients[i].setBalance(balance);
     }

     // write 5 records to file
     for (int i = 0; i < 5; i++)
     {
          outCredit.write(reinterpret_cast<const char *>(&clients[i]), sizeof(ClientData));
     }

     outCredit.close();

     // read records from file

     int choice;

     cout << "Select from the options given below \n";
     cout << "1. Read Sequentially \n";
     cout << "2. Read Randomly \n";
     cout << "3. Quit \n";

     // takes in numeric choice from the user
     cin >> choice;

     // checks if the choice is between 1 and 5
     if (choice >= 1 && choice <= 3)
     {
          cout << "\nYou have selected choice: " << choice << "\n";

          switch (choice)
          {
          case 1:
               readSequentially();
               break;
          case 2:
               readRandomly();
               break;
          case 3:
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

// function to read sequentially
void readSequentially()
{
     ClientData client; // create record

     ifstream inCredit("accounts.dat", ios::in | ios::binary);

     // reads the file in sequence
     inCredit.read(reinterpret_cast<char *>(&client), sizeof(ClientData));

     cout << left << setw(10) << "Account" << setw(16)
          << "Last Name" << setw(11) << "First Name" << left
          << setw(10) << right << "Balance" << endl;

     // display each record in file
     while (inCredit && !inCredit.eof())
     {
          if (client.getAccountNumber() != 0)
               outputLine(cout, client);

          inCredit.read(reinterpret_cast<char *>(&client), sizeof(ClientData));
     }
}

// function to read randomly
void readRandomly()
{
     ifstream inCredit("accounts.dat", ios::in | ios::binary);

     // exit program if ifstream cannot open file
     if (!inCredit)
     {
          cerr << "File could not be opened." << endl;
          exit(EXIT_FAILURE);
     }

     ClientData client; // create record

     int choice;
     int r;

     // options for the user to select
     cout << "Select from the options given below\n";
     cout << "1. Read First Record \n";
     cout << "2. Read Last Record \n";
     cout << "3. Read Specific Record \n";
     cout << "4. Quit \n";

     // takes in numeric choice from the user
     cin >> choice;

     if (choice >= 1 && choice <= 5)
     {
          cout << "\nYou have selected choice: " << choice << "\n";

          switch (choice)
          {
          case 1:
               r = 1;
               break;
          case 2:
               r = 5;
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
          cout << "Wrong option, try again \n";
     }

     cout << "Reading random record from the file: " << r << "\n";

     inCredit.seekg(sizeof(ClientData) * (r - 1), ios::cur);

     // read first record from file
     inCredit.read(reinterpret_cast<char *>(&client),
                   sizeof(ClientData));
     cout << left << setw(10) << "Account" << setw(16)
          << "Last Name" << setw(11) << "First Name" << left
          << setw(10) << right << "Balance" << endl;

     outputLine(cout, client);
}