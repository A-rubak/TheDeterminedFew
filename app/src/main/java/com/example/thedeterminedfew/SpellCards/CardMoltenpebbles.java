package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardMoltenpebbles extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Molten Pebbles";
    private static final int influence = 10;
    private static final String description = "Several stones bursting with flames";
    private static final CardType type = CardType.SPELL;
    private static final int cardImage = R.drawable.card_icon_moltenpebbles;
    private static final CardElement element = CardElement.FIRE;

    public CardMoltenpebbles(){
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
