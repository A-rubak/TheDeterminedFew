package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardSpark extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Spark";
    private static final int influence = 1;
    private static final String description = "A tiny spark of energy";
    private static final CardType type = CardType.ELEMENT;
    private static final int cardImage = R.drawable.card_icon_spark;
    private static final CardElement element = CardElement.FIRE;

    public CardSpark(){
        super(tag,name,influence,description,type,cardImage,element);
    }


    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return new CardMoltenpebbles();
            case "Spark":return new CardFireball();
            case "Dew":return new CardSteam();
            case "Breeze":return new CardFlamethrower();
        }
        return null;
    }
}
