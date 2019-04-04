package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.Helpers;
import in.ashwanik.texasholdem.PokerHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OnePairRank implements Rank {

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
}
