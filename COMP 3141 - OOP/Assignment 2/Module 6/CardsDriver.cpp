#include <iostream>
#include "DeckOfCards.cpp"
using namespace std;

int main()
{
    // Create an instance of the DeckOfCards class
    DeckOfCards deck;

    // Print the normal deck of cards
    cout << "\nNormal Deck of Cards:" << endl;
    for (int i = 0; i < deck.numberOfCards; ++i) {
        cout << "Card " << i + 1 << ": ";
        cout << deck.dealCard() << endl;
    }

    // Shuffle the deck
    deck.shuffleCards();

    cout << endl << "Shuffled Deck of Cards:" << endl;
    deck.resetCurrentCard(); // Reset the current card to 0

    // Print the shuffled deck of cards
    for (int i = 0; i < deck.numberOfCards; ++i) {
        cout << "Card " << i + 1 << ": ";
        cout << deck.dealCard() << endl;
    }

    return 0;
}
