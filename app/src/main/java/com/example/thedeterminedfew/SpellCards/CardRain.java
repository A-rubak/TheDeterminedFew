package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.R;

public class CardRain extends Card{
    private static final String tag = "Enemy";
    private static final String name = "Rain";
    private static final int influence = 10;
    private static final String description = "Water element gathered in a cloud";
    private static final CardType type = CardType.SPELL;
    private static final int cardImage = R.drawable.card_icon_rain;
    private static final CardElement element = CardElement.WATER;

    public CardRain(){
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
