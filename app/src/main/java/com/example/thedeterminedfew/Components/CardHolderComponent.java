package com.example.thedeterminedfew.Components;

import com.example.thedeterminedfew.SpellCards.Card;
import com.example.thedeterminedfew.SpellCards.CardBreeze;
import com.example.thedeterminedfew.SpellCards.CardDew;
import com.example.thedeterminedfew.SpellCards.CardDust;
import com.example.thedeterminedfew.SpellCards.CardSpark;

import java.util.ArrayList;
import java.util.Random;

public class CardHolderComponent {

    private static final int MAX_CARDS = 5;

    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> drawingDeck = new ArrayList<>();
    private Random rand = new Random();

    public CardHolderComponent(ArrayList<Card> list){
        deck = list;
        Card card1 = new CardSpark();
        Card card2 = new CardDew();
        Card card3 = new CardDust();
        Card card4 = new CardBreeze();

        drawingDeck.add(card1);
        drawingDeck.add(card2);
        drawingDeck.add(card3);
        drawingDeck.add(card4);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void drawCards(){
        while(deck.size() < MAX_CARDS){
            int randomIndex = rand.nextInt(drawingDeck.size());
            try {
                deck.add(drawingDeck.get(randomIndex).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
