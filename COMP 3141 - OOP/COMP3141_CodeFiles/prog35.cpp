#include <iostream>
#include <sstream>
#include <fstream>
#include <cstdlib>

using std::cerr;
using std::cin;
using std::cout;
using std::endl;
using std::ios;
using namespace std;

// reads data from a file of text data about clients
int main()
{
   ifstream outClientFile("clients.dat", ios::in);

   if (!outClientFile)
   {
      cerr << "File could not be opened" << endl;
      exit(1);
   }

   string line;
   cout << "ID   Name   Balance\n";
   while (getline(outClientFile, line))
   {
      cout << line << endl;
   }
   outClientFile.close();

   return 0;
}
