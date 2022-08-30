package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardQuicksand extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Quicksand";
    private static final int influence = 10;
    private static final String description = "Volatile sand";
    private static final CardType type = CardType.SPELL;
    private static final int cardImage = R.drawable.card_icon_quicksand;
    private static final CardElement element = CardElement.EARTH;

    public CardQuicksand(){
        super(tag,name,influence,description,type,cardImage,element);
    }
    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return null;
            case "Spark":return null;
            case "Dew":return null;
            case "Breeze":return null;
        }
        return null;
    }
}
