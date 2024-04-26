#include <iostream>
using namespace std;

int main()
{
	int counter, howmuch;
	cout << "Enter number to generate the series : ";
	cin >> howmuch;

	counter = 0;
	while (counter < howmuch)
	{
		counter++;
		cout << counter << '\n';
	}

	system("pause");
	return 0;
}
//@ Change the above program to run with for statement
//@ Change the above program to run with do.. while
