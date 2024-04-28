#include <iostream>
#include <string>
using namespace std;

class Vector2 {
    public: 
    int x, y;
    
    Vector2(int a, int b) : x(a), y(b) {}
    
    Vector2 operator+(Vector2 &v) {
        return Vector2(x + v.x, y + v.y);
    }
    
    friend void display(Vector2 &v) {
        cout << v.x << ", " << v.y;
    }
};
 
int main() {
    
    Vector2 v1(1, 2);
    Vector2 v2(3, 4);
    Vector2 v3 = v1 + v2;
    
    display(v3);
  
  return 0;
}