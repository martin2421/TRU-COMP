#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>
using namespace std;
 
int main() {
    
    int x = 0;
    
    cout << "Enter an int: ";
    cin >> x;
    
    string y = string(&x);
    string result = "";
    
    for (int i = sizeof(y) - 1; i >=0 ; i--) {
        result += y[i];
    }
    
    cout << endl << "Reversed: " << result;
    
  
    return 0;
}