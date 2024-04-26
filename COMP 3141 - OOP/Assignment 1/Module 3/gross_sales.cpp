#include <iostream>
using namespace std;

int main()
{
     double sales;    // gross sales for the week
     double totalEarnings; // total earnings for the salesperson

     // input gross sales
     cout << "Enter the gross sales for the week (-1 to end): ";
     cin >> sales;

     while (sales != -1)
     {
          // calculate earnings
          totalEarnings = 200 + (0.09 * sales);

          // display earnings for the salesperson
          cout << "The earnings for the salesperson are: $" << totalEarnings << endl;

          // input for next salesperson
          cout << "\nEnter the gross sales for the week (-1 to end): ";
          cin >> sales;
     }

     return 0;
}
