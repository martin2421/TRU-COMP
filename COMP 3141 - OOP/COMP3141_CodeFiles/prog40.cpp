#include <exception>
#include <iostream>

using namespace std;

class OverSpeed : public exception
{
    int speed;

public:
    const char *what()
    {
        return "check up your speed! you may be charged for the speeding";
    }
    void getSpeed() { cout << "Your car speed is" << speed << endl; }
    void setSpeed(int sp) { this->speed = sp; }
};

// create another class to make ZeroSpeed. when speed exceeds 250, this exception is called and after that it should again start increasing the speed

class Car
{
    int speed;

public:
    Car()
    {
        speed = 0;
        cout << "speed is zero" << endl;
    }

    void accelerate()
    {
        for (;;)
        {
            speed += 10;
            cout << "Speed is " << speed << endl;
            if (speed >= 250)
            {
                OverSpeed s;
                s.setSpeed(speed);
                throw s;
            }
        }
    }
};

int main()
{

    Car c;
    try
    {
        c.accelerate();
    }
    catch (OverSpeed s)
    {

        cout << s.what() << endl;
        s.getSpeed();
    }
    return 0;
}
