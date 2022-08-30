package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardBreeze extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Breeze";
    private static final int influence = 1;
    private static final String description = "Gentle manifestation of wind element";
    private static final CardType type = CardType.ELEMENT;
    private static final int cardImage = R.drawable.card_icon_breeze;
    private static final CardElement element = CardElement.AIR;


    public CardBreeze() {
        super(tag,name,influence, description, type, cardImage,element);
    }

    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return new CardSandblast();
            case "Spark":return new CardFlamethrower();
            case "Dew":return new CardRain();
            case "Breeze":return new CardVortex();
        }
        return null;
    }
}
