#include <iostream> // std::cout, std::right, std::endl
#include <iomanip>  // std::setw
using namespace std;

int main()
{
  int val = 15;

  cout << right << setw(10) << val << endl;

  cout << oct << val << endl; // multiple insertions

  cout << internal << setw(10) << setfill('#') << val << endl;

  cout << uppercase << hex << val << endl;

  cout << scientific << dec << 120.00 << endl;

  // TODO - Enter the user name and Balance in the bank
  //  Display the name in the capital letters
  //  Diplay the balance right aligned padded with the * on left side

  return 0;
}
