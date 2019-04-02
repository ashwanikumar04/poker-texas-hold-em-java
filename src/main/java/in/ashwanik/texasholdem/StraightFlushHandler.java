package in.ashwanik.texasholdem;

import static java.util.stream.Collectors.groupingBy;

class StraightFlushHandler extends PokerHandHandler {

    StraightFlushHandler() {
        this.next(new FourOfAKindHandler());
    }

    @Override
    PokerHand.Result handle(PokerHand first, PokerHand other) {
        PokerHand.Result result = straightFlush(first, other);
        if (result == null && pokerHandHandler != null) {
            return pokerHandHandler.handle(first, other);
        }
        return result;
    }


    private PokerHand.Result straightFlush(PokerHand first, PokerHand other) {
        Tuple<Boolean, Integer> current = getStraightFlushDetails(first);
        Tuple<Boolean, Integer> otherHand = getStraightFlushDetails(other);
        return getResult(current, otherHand);
    }

    private Tuple<Boolean, Integer> getStraightFlushDetails(PokerHand pokerHand) {
        boolean isStraightFlush;
        int max;
        boolean isSameSuit = pokerHand.getCards().stream().collect(groupingBy(PokerHand.Card::getSuit)).size() == 1;
        boolean isIncreasing = true;
        for (int index = 0; index < pokerHand.getCards().size() - 1; index++) {
            if (Math.abs(pokerHand.getCards().get(index).getValue() - pokerHand.getCards().get(index + 1).getValue()) != 1) {
                isIncreasing = false;
            }
        }
        max = Math.max(pokerHand.getCards().get(0).getValue(), pokerHand.getCards().get(pokerHand.getCards().size() - 1).getValue());
        isStraightFlush = isSameSuit && isIncreasing;
        return new Tuple<>(isStraightFlush, max);
    }

}
