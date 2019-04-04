package in.ashwanik.texasholdem;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PokerHand {
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

    Map<Integer, List<Card>> getCardsMap() {
        return cardsMap;
    }

    List<Card> getCards() {
        return cards;
    }

    Result compareWith(PokerHand other) {

        Ranks current = rank();
        Ranks others = other.rank();

        if (current.getRank() > others.getRank()) {
            return Result.WIN;
        } else if (current.getRank() < others.getRank()) {
            return Result.LOSS;
        } else {
            return current.resolveConflict(this, other);
        }
    }

    Ranks rank() {
        if (isStraightFlush()) {
            return Ranks.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return Ranks.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return Ranks.FULL_HOUSE;
        } else if (isFlush()) {
            return Ranks.FLUSH;
        } else if (isStraight()) {
            return Ranks.STRAIGHT;
        } else if (isThreeOfAKind()) {
            return Ranks.THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return Ranks.TWO_PAIR;
        } else if (isOnePair()) {
            return Ranks.ONE_PAIR;
        } else {
            return Ranks.HIGH_CARD;
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

    public enum Ranks {

        HIGH_CARD {
            @Override
            public int getRank() {
                return 1;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        },
        ONE_PAIR {
            @Override
            public int getRank() {
                return 2;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                List<PokerHand.Card> firstCards = new ArrayList<>();
                List<PokerHand.Card> secondCards = new ArrayList<>();

                int firstPairValue = getHandDetails(first, firstCards);

                int secondPairValue = getHandDetails(second, secondCards);

                if (firstPairValue > secondPairValue) {
                    return PokerHand.Result.WIN;
                } else if (firstPairValue < secondPairValue) {
                    return PokerHand.Result.LOSS;
                } else {
                    return Helpers.getHighCardResults(firstCards, secondCards);
                }
            }

            private int getHandDetails(PokerHand hand, List<PokerHand.Card> cards) {
                int value = 0;

                for (Map.Entry<Integer, List<PokerHand.Card>> entry : hand.getCardsMap().entrySet()) {
                    if (entry.getValue().size() == 1) {
                        cards.addAll(entry.getValue());
                    } else {
                        value = entry.getKey();
                    }
                }
                return value;
            }
        },
        TWO_PAIR {
            @Override
            public int getRank() {
                return 3;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {

                List<Integer> firstValues = new ArrayList<>(first.getCardsMap().keySet());
                firstValues.sort(Comparator.reverseOrder());
                List<Integer> secondValues = new ArrayList<>(second.getCardsMap().keySet());
                secondValues.sort(Comparator.reverseOrder());
                return Helpers.getHighCardResultsForValues(firstValues, secondValues);
            }
        },
        THREE_OF_A_KIND {
            @Override
            public int getRank() {
                return 4;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        },
        STRAIGHT {
            @Override
            public int getRank() {
                return 5;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        }, FLUSH {
            @Override
            public int getRank() {
                return 6;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        }, FULL_HOUSE {
            @Override
            public int getRank() {
                return 7;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        }, FOUR_OF_A_KIND {
            @Override
            public int getRank() {
                return 8;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        }, STRAIGHT_FLUSH {
            @Override
            public int getRank() {
                return 9;
            }

            @Override
            public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
                return Helpers.getHighCardResults(first.getCards(), second.getCards());
            }
        };


        public abstract int getRank();

        public abstract PokerHand.Result resolveConflict(PokerHand first, PokerHand second);
    }

    static class Helpers {

        static PokerHand.Result getHighCardResults(List<PokerHand.Card> firstCardList, List<PokerHand.Card> secondCardList) {
            int index = firstCardList.size() - 1;
            while (index >= 0) {
                if (firstCardList.get(index).getValue() > secondCardList.get(index).getValue()) {
                    return PokerHand.Result.WIN;
                } else if (firstCardList.get(index).getValue() < secondCardList.get(index).getValue()) {
                    return PokerHand.Result.LOSS;
                }
                index--;
            }
            return PokerHand.Result.TIE;
        }

        static PokerHand.Result getHighCardResultsForValues(List<Integer> firstCardList, List<Integer> secondCardList) {
            int index = firstCardList.size() - 1;
            while (index >= 0) {
                if (firstCardList.get(index) > secondCardList.get(index)) {
                    return PokerHand.Result.WIN;
                } else if (firstCardList.get(index) < secondCardList.get(index)) {
                    return PokerHand.Result.LOSS;
                }
                index--;
            }
            return PokerHand.Result.TIE;
        }

        static Map<Integer, List<PokerHand.Card>> getValueMap(List<PokerHand.Card> cards) {
            return cards.stream().collect(groupingBy(PokerHand.Card::getValue));
        }

        static int getCountOfGroupOfASize(Map<Integer, List<PokerHand.Card>> map, int groupSize) {
            return (int) map.entrySet().stream().filter(x -> x.getValue().size() == groupSize).count();
        }

    }

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