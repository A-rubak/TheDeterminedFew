package com.example.thedeterminedfew.SpellCards;

public class CardMagic extends Card{
    private static final String tag = "Support";
    private static final String name = "Mana";
    private static final int influence = 2;
    private static final String description = "Raw magic energy";
    private static final CardType type = CardType.ENERGY;
    private static final int cardImage = 0;
    private static final CardElement element = null;

    public CardMagic(){
        super(tag,name,influence,description,type,cardImage,element);
    }

    @Override
    public Card merge(Card mergingCard) {
        switch (mergingCard.getName()){
            case "Dust":return null;
            case "Spark":return new CardFireball();
            case "Dew":return null;
            case "Breeze":return null;
        }
        return null;
    }
}
