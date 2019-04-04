package in.ashwanik.texasholdem;


import in.ashwanik.texasholdem.ranks.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PokerHand {

    private static Rank STRAIGHT_FLUSH = new StraightFlushCardRank();
    private static Rank FOUR_OF_A_KIND = new FourOfAKindRank();
    private static Rank FULL_HOUSE = new FullHouseRank();
    private static Rank FLUSH = new FlushCardRank();
    private static Rank STRAIGHT = new StraightCardRank();
    private static Rank THREE_OF_A_KIND = new ThreeOfAKindRank();
    private static Rank TWO_PAIR = new TwoPairRank();
    private static Rank ONE_PAIR = new OnePairRank();
    private static Rank HIGH_CARD = new HighCardRank();


    private List<Card> cards;
    private Map<Integer, List<Card>> cardsMap;

    PokerHand(String handString) {
        cards = new ArrayList<>();
        String[] cardsString = handString.split(" ");
        for (String card : cardsString) {
            cards.add(new Card(card));
        }
        cards.sort(Comparator.comparingInt(o -> o.value));
        cardsMap = Helpers.getValueMap(cards);
    }

    public Map<Integer, List<Card>> getCardsMap() {
        return cardsMap;
    }

    public List<Card> getCards() {
        return cards;
    }

    Result compareWith(PokerHand other) {

        Rank current = rank();
        Rank others = other.rank();

        if (current.getRank() > others.getRank()) {
            return Result.WIN;
        } else if (current.getRank() < others.getRank()) {
            return Result.LOSS;
        } else {
            return current.resolveConflict(this, other);
        }
    }

    Rank rank() {
        if (isStraightFlush()) {
            return STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return FULL_HOUSE;
        } else if (isFlush()) {
            return FLUSH;
        } else if (isStraight()) {
            return STRAIGHT;
        } else if (isThreeOfAKind()) {
            return THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return TWO_PAIR;
        } else if (isOnePair()) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    private boolean isOnePair() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 2) == 1;
    }

    private boolean isTwoPair() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 2) == 2;
    }

    private boolean isThreeOfAKind() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 3) == 1;
    }

    private boolean isFullHouse() {
        return isThreeOfAKind() && isOnePair();
    }

    private boolean isFourOfAKind() {
        return Helpers.getCountOfGroupOfASize(this.cardsMap, 4) == 1;
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }


    private boolean isStraight() {
        boolean isIncreasing = true;
        for (int index = 0; index < this.getCards().size() - 1; index++) {
            if (Math.abs(this.getCards().get(index).getValue() - this.getCards().get(index + 1).getValue()) != 1) {
                isIncreasing = false;
            }
        }
        return isIncreasing;
    }

    private boolean isFlush() {
        return this.getCards().stream().collect(groupingBy(PokerHand.Card::getSuit)).size() == 1;
    }


    public enum Result {TIE, WIN, LOSS}

    public class Card {
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