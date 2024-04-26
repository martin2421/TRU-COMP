#include <iostream>
#include <string>
using namespace std;

class Card
{

private:
    int face;
    int suit;

    const string faces[13] = {"Ace", "Duece", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    const string suits[4] = {"Clubs", "Diamonds", "Hearts", "Spades"};

public:
    // Constructor, takes in face and suit of card
    Card(int face, int suit)
    {
        this->face = face;
        this->suit = suit;
    }

    // Returns face of card
    void toString()
    {
        cout << "Face: " << faces[face] << " Suit: " << suits[suit] << endl;
    }

    // Overloaded insertion operator
    friend std::ostream &operator<<(std::ostream &os, const Card &card)
    {
        os << card.faces[card.face] << " of " << card.suits[card.suit];
        return os;
    }

    // Overloaded assignment operator
    Card &operator=(const Card &other)
    {
        if (this != &other) // self-assignment check
        {
            this->face = other.face;
            this->suit = other.suit;
        }
        return *this;
    }
};
