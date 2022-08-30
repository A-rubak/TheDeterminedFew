package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.R;

import java.util.ArrayList;
import java.util.Random;

public class StartingDeck {

    private ArrayList<Card> cardList = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private Random rand = new Random();
    public StartingDeck(){
        Card card1 = new CardSpark();
        Card card2 = new CardDew();
        Card card3 = new CardDust();
        Card card4 = new CardBreeze();

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        for(int i = 0;i<5;i++){
            int randomIndex = rand.nextInt(cards.size());
            try {
                cardList.add(cards.get(randomIndex).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }
    }



    public ArrayList<Card> getCardList(){
        return cardList;
    }
}
