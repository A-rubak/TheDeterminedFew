package com.example.thedeterminedfew.SpellCards;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.Transform;

public abstract class Card implements Cloneable{
    String mTag;
    String mName;
    int mInfluence;
    String mDescription;
    CardType mType;
    int mCardImage;
    CardElement mElement;
    Transform mTransform;

    Card(String tag,String name,int influence, String desc, CardType type, int img, CardElement element){
        mTag = tag;
        mName = name;
        mInfluence = influence;
        mDescription = desc;
        mType = type;
        mCardImage = img;
        mElement = element;
    }
    public abstract Card merge(Card mergingCard);
    public String getName() {return mName;}
    public int getInfluence() {return mInfluence;}
    public String getDescription(){return mDescription;}
    public CardType getType() {return mType;}
    public int getCardImage() {return mCardImage;}
    public CardElement getCardElement() {return mElement;}
    public void setTransform(Transform t){mTransform = t; }
    public Transform getTransform(){return mTransform; }
    public Ability transformIntoAbility(){
        Ability a = new Ability(mTag,mName,mInfluence,mDescription,mCardImage);
        a.setTransform(mTransform);
        return a;
    }

    public Card clone() throws CloneNotSupportedException {
        return (Card)super.clone();
    }






}
