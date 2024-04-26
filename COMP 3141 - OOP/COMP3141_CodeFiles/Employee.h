#ifndef EMPLOYEE_H
#define EMPLOYEE_H
using namespace std;
#include <string>
#include "Time.h"

class Employee
{
public:
    Employee(const string &, const string &, const Time &, const Time &);
    void print() const;
    ~Employee();

private:
    std::string firstName;
    std::string lastName;
    const Time inTime;
    const Time outTime;
};

#endif // EMPLOYEE_H
