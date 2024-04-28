#include <iostream>
#include <string>
#include <fstream>
using namespace std;
 
int main() {
    
    ifstream inData("ABC.txt", ios::in);
    ostream outData("XYZ.txt", ios::out);
    
    if(!inData) {
        exit(1);
    }
    string line;
    while (getline(inData, line)) {
        outData << line << endl;
    }
    
    inData.close();
    outData.close();
    
  
    return 0;
}