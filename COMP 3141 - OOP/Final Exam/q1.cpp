#include <iostream>
#include <string>
using namespace std;

class Account {
    protected:
        int age;
        string name;
        float balance;
    public:
        Account() : name(""), age(0) {}
        Account(string n, int x) {
            name = n;
            age = x;
        }
        
        int getAge() {
            return age;
        }
        string getName() {
            return name;
        }
        bool setAge(int x) {
            if (x <= 0) {
                return false;
            } else {
                age = x;
                return true;
            }
        }
        void setName(string n) {
            name = n;
        }
        
        float showBalance() {
            return balance;
        }
        void doDeposit(float b) {
            balance += b;
        }
        bool doWithdraw(float b) {
            balance -= b;
            return true;
        }
        
};

class individual : public Account {
       public:
       individual(string n, int x, float b) {
           name = n;
           age = x;
           balance = b;
       }
        void setAll(string n, int x, float b) {
            name = n;
            age = x;
            balance = b;
        }
};

class joint : public Account {
    public:
       joint(string n, int x, float b) {
           name = n;
           age = x;
           balance = b;
       }
        void setAll(string n, int x, float b) {
            name = n;
            age = x;
            balance = b;
        }
};

class student : public Account {
    public:
       student(string n, int x, float b) {
           name = n;
           age = x;
           balance = b;
       }
        void setAll(string n, int x, float b) {
            name = n;
            age = x;
            balance = b;
        }
};
 
int main() {
    
    
  
  return 0;
}