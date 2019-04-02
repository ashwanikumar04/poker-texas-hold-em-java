package in.ashwanik.texasholdem;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

class FourOfAKindHandler extends PokerHandHandler {

    FourOfAKindHandler() {
        this.next(null);
    }

    @Override
    PokerHand.Result handle(PokerHand first, PokerHand other) {
        PokerHand.Result result = fourOfAKind(first, other);
        if (result == null && pokerHandHandler != null) {
            return this.pokerHandHandler.handle(first, other);
        }
        return result;
    }


    private PokerHand.Result fourOfAKind(PokerHand first, PokerHand other) {
        Tuple<Boolean, Integer> current = getFourOfAKindDetails(first);
        Tuple<Boolean, Integer> otherHand = getFourOfAKindDetails(other);
        return getResult(current, otherHand);
    }

    private Tuple<Boolean, Integer> getFourOfAKindDetails(PokerHand pokerHand) {
        boolean isFourOfAKind = false;
        int max = 0;
        Map<Integer, List<PokerHand.Card>> groups = pokerHand.getCards().stream().collect(groupingBy(PokerHand.Card::getValue));

        for (Map.Entry<Integer, List<PokerHand.Card>> entry : groups.entrySet()) {
            if (entry.getValue().size() == 4) {
                isFourOfAKind = true;
                max = entry.getKey();
            }
        }

        return new Tuple<>(isFourOfAKind, max);
    }

}
