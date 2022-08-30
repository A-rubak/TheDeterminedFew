package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardDust extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Dust";
    private static final int influence = 1;
    private static final String description = "The minimal power of the earth";
    private static final CardType type = CardType.ELEMENT;
    private static final int cardImage = R.drawable.card_icon_dust;
    private static final CardElement element = CardElement.EARTH;


    public CardDust() {
        super(tag,name,influence, description, type, cardImage,element);
    }

    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return new CardEarthspike();
            case "Spark":return new CardMoltenpebbles();
            case "Dew":return new CardQuicksand();
            case "Breeze":return new CardSandblast();
        }
        return null;
    }
}
