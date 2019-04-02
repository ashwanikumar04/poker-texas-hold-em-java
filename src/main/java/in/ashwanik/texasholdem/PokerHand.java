package in.ashwanik.texasholdem;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class PokerHand {
    private List<Card> cards;

    PokerHand(String handString) {
        cards = new ArrayList<>();
        String[] cardsString = handString.split(" ");
        for (String card : cardsString) {
            cards.add(new Card(card));
        }
        cards.sort(Comparator.comparingInt(o -> o.value));
    }

    List<Card> getCards() {
        return cards;
    }

    Result compareWith(PokerHand other) {
        StraightFlushHandler straightFlushHandler = new StraightFlushHandler();
        return straightFlushHandler.handle(this, other);
    }

    public enum Result {TIE, WIN, LOSS}

    class Card {
        private int value;
        private char suit;

        Card(String representation) {
            suit = representation.charAt(1);
            char first = representation.charAt(0);
            value = calculateValue(first);
        }

        int getValue() {
            return value;
        }

        char getSuit() {
            return suit;
        }

        private int calculateValue(char first) {
            int cardValue;
            switch (first) {
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    cardValue = Integer.parseInt(first + "");
                    break;
                case 'T':
                    cardValue = 10;
                    break;
                case 'J':
                    cardValue = 11;
                    break;
                case 'Q':
                    cardValue = 12;
                    break;
                case 'K':
                    cardValue = 13;
                    break;
                case 'A':
                    cardValue = 14;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid card");
            }
            return cardValue;
        }
    }


}