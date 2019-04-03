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

        Map<Integer, List<PokerHand.Card>> firstCardMap = Helpers.getValueMap(first.getCards());

        List<PokerHand.Card> firstCards = new ArrayList<>();

        int firstPairValue = 0;

        for (Map.Entry<Integer, List<PokerHand.Card>> entry : firstCardMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                firstCards.addAll(entry.getValue());
            } else {
                firstPairValue = entry.getKey();
            }
        }
        int secondPairValue = 0;

        Map<Integer, List<PokerHand.Card>> secondCardMap = Helpers.getValueMap(second.getCards());

        List<PokerHand.Card> secondCards = new ArrayList<>();

        for (Map.Entry<Integer, List<PokerHand.Card>> entry : secondCardMap.entrySet()) {
            if (entry.getValue().size() == 1) {
                secondCards.addAll(entry.getValue());
            } else {
                secondPairValue = entry.getKey();
            }
        }

        if (firstPairValue > secondPairValue) {
            return PokerHand.Result.WIN;
        } else if (firstPairValue < secondPairValue) {
            return PokerHand.Result.LOSS;
        } else {
            return Helpers.getHighCardResults(firstCards, secondCards);
        }
    }
}
