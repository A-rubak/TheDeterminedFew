package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardSplash extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Splash";
    private static final int influence = 10;
    private static final String description = "A ball of water";
    private static final CardType type = CardType.SPELL;
    private static final int cardImage = R.drawable.card_icon_splash;
    private static final CardElement element = CardElement.WATER;

    public CardSplash(){
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
