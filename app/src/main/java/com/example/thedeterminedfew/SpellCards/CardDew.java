package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardDew extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Dew";
    private static final int influence = 1;
    private static final String description = "A condensed vapour of water element";
    private static final CardType type = CardType.ELEMENT;
    private static final int cardImage = R.drawable.card_icon_dew;
    private static final CardElement element = CardElement.WATER;


    public CardDew() {
        super(tag,name,influence, description, type, cardImage,element);
    }

    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return new CardQuicksand();
            case "Spark":return new CardSteam();
            case "Dew":return new CardSplash();
            case "Breeze":return new CardRain();
        }
        return null;
    }
}
