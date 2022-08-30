package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardSandblast extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Sand Blast";
    private static final int influence = 10;
    private static final String description = "A short burst of earth";
    private static final CardType type = CardType.SPELL;
    private static final int cardImage = R.drawable.card_icon_sandblast;
    private static final CardElement element = CardElement.EARTH;

    public CardSandblast(){
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
