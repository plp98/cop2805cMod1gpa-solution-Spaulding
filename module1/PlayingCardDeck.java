//PlayingCardDeck.java
//Evan Spaulding
//01/20/2023
//Program that deals a hand of playing cards.

package edu.fscj.cop2805c.module1;

import java.util.ArrayList;
import java.util.Random;

enum Suit{ Clubs, Diamonds, Hearts, Spades }

enum FaceValue{  Ace, Two, Three, Four, Five, Six,
    Seven, Eight, Nine, Ten, Jack, Queen, King, None }

class PlayingCard {
    private Integer pointValue;
    private Suit suit;
    private FaceValue faceValue;

    boolean dealt = false;

    //Constructor
    public PlayingCard(Suit suit, FaceValue faceValue, Integer pointValue) {
        this.pointValue = pointValue;
        this.suit = suit;
        this.faceValue = faceValue;
    }

    //Accessor
    public Integer getPointValue() {
        return pointValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    public boolean isDealt() {
        return dealt;
    }

    //Mutator
    public void setDealt(boolean dealt) {
        this.dealt = dealt;
    }

    @Override
    public String toString() {
            String s = faceValue + " of " + suit + " (value = "
                    + pointValue + " dealt: " + dealt + ")";
            return s;
    }
}

public class PlayingCardDeck {
    ArrayList<PlayingCard> deck = new ArrayList<>();

    //Build hand
    ArrayList<PlayingCard> hand(int n) {
        ArrayList<PlayingCard> returnList = new ArrayList<>();
        var index = -1;
        Random r = new Random(System.currentTimeMillis());
        for (var i = 0; i < n; ) {
            index = r.nextInt(deck.size());
            PlayingCard pc = deck.get(index);
            if (pc.isDealt() == false) {
                returnList.add(pc);
                pc.setDealt(true);
                i++;
            }
        }
        return returnList;
    }

    //Show hand
    public void showHand(ArrayList<PlayingCard> hand) {
        for (PlayingCard pc : hand)
            System.out.println(pc.getFaceValue() + " of " + pc.getSuit() + " (value = "
                    + pc.getPointValue() + " dealt: " + pc.isDealt() + ")");
    }

    //Show deck
    @Override
    public String toString() {
        String s = "Here is your playing card deck:\n";
        for (PlayingCard p : deck) {
            s += "\t";
            s += p;
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {

        Suit[] suits = {
                Suit.Clubs,
                Suit.Diamonds,
                Suit.Hearts,
                Suit.Spades
        };

        FaceValue[] faceValues = {
                FaceValue.Ace,
                FaceValue.Two,
                FaceValue.Three,
                FaceValue.Four,
                FaceValue.Five,
                FaceValue.Six,
                FaceValue.Seven,
                FaceValue.Eight,
                FaceValue.Nine,
                FaceValue.Ten,
                FaceValue.Jack,
                FaceValue.Queen,
                FaceValue.King,
                FaceValue.None,
        };

        Integer[] pointValues = {
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                10,
                10,
                10,
                0
        };

        //Welcome message
        System.out.println("""
                Welcome to the Playing Card Simulator!
                I will now create your deck of 52 cards \
                and deal a random hand of size 5.""");

        //Build deck
        PlayingCardDeck pc = new PlayingCardDeck();
        for (var i = 0; i < suits.length; i++)
            for (var j = 0; j < (pointValues.length - 1); j++)
                pc.deck.add(new PlayingCard(suits[i], faceValues[j], pointValues[j]));

        //Call to show deck
        System.out.println(pc);

        ArrayList<PlayingCard> playingHand = pc.hand(5);

        //Call to show hand
        pc.showHand(playingHand);
    }
}
