#include <iostream>
#include <vector>
#include <cstdlib> // rand and srand
#include <ctime>   // time
#include "Card.cpp"
using namespace std;

class DeckOfCards
{

private:
    vector<Card> deck; // vector of card objects
    int currentCard = 0;

public:
    const int numberOfCards = 52;
    const int numberOfSuits = 4;
    const int numberOfFaces = 13;

    // Constructor, creates deck of cards
    DeckOfCards()
    {
        for (int suit = 0; suit < numberOfSuits; suit++)
            for (int face = 0; face < numberOfFaces; face++)
                deck.push_back(Card(face, suit));
    }

    // Resets the current card to 0
    void resetCurrentCard() {
        currentCard = 0;
    }

    // Shuffles the deck of cards
    void shuffleCards()
    {
        // Seed the random number generator with current time
        srand(static_cast<unsigned int>(time(nullptr)));

        for (int i = 0; i < numberOfCards; ++i)
        {
            // Generate a random number between 0 and 51
            int j = rand() % numberOfCards;
            
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    // returns current card in the deck, goes from 0-52
    Card dealCard()
    {
        if (moreCards())
            return deck[currentCard++];
        else // if no more cards, return empty card
            return Card(-1, -1);
    }

    // returns true if there are more cards in the deck
    bool moreCards()
    {
        return (currentCard < numberOfCards);
    }
};
