#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main()
{

     // variables
     double totalMiles, costPerGallon, avgMilesPerGallon, parkingFees, tolls;

     // input
     cout << "Total miles driven per day: ";
     cin >> totalMiles;

     cout << "Cost per gallon of gasoline: $";
     cin >> costPerGallon;

     cout << "Avg. miles per gallon: ";
     cin >> avgMilesPerGallon;

     cout << "Parking fees per day: $";
     cin >> parkingFees;

     cout << "Tolls per day: $";
     cin >> tolls;

     // calculation
     double gallonsUsed = totalMiles / avgMilesPerGallon;
     double dailyCost = (gallonsUsed * costPerGallon) + parkingFees + tolls;

     // output
     cout << "\nUser's cost per day of driving is: $" << dailyCost << endl;

     return 0;
}
