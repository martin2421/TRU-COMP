#include <iostream>
#include <fstream>
#include <cstdlib>

using std::cerr;
using std::cin;
using std::cout;
using std::endl;
using std::ios;
using std::ofstream;
using std::exit;

// creates a file of text data about clients
int main()
{
   ofstream outClientFile("clients.dat", ios::out);

   if (!outClientFile)
   {
      cerr << "File could not be opened" << endl;
      exit(1);
   }

   int account = 12;
   char name[80] = "This Is A Test";
   double balance = 123.123;
   char ans = 'Y';
   while (ans == 'Y' || ans == 'y')
   {
      cout << "Enter name ";
      cin >> name;
      cout << "Enter account no: ";
      cin >> account;
      cout << "Enter Balance : ";
      cin >> balance;
      outClientFile << account << " " << name << " " << balance << endl;
      cout << "Press 'y' to continue...";
      cin >> ans;
   }
   outClientFile.close();

   return 0;
}
