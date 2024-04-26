// Fig. 14.9: ClientData.h
// Class ClientData definition used in Fig. 14.11-Fig. 14.14.
#ifndef CLIENTDATA_H_INCLUDED
#define CLIENTDATA_H_INCLUDED

using namespace std;
#include <string>

class ClientData
{
public:
     // default ClientData constructor
     ClientData(int = 0, const string & = "", const string & = "", double = 0.0);

     // accessor functions for accountNumber
     void setAccountNumber(int);
     int getAccountNumber() const;

     // accessor functions for lastName
     void setLastName(const std::string &);
     std::string getLastName() const;

     // accessor functions for firstName
     void setFirstName(const std::string &);
     std::string getFirstName() const;

     // accessor functions for balance
     void setBalance(double);
     double getBalance() const;

private:
     int accountNumber;
     char lastName[15];
     char firstName[10];
     double balance;
}; // end class ClientData

#endif // CLIENTDATA_H_INCLUDED
