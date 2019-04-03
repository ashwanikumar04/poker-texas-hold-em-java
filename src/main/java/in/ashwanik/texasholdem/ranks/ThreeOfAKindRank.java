package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.Helpers;
import in.ashwanik.texasholdem.PokerHand;

import java.util.List;
import java.util.Map;

public class ThreeOfAKindRank implements Rank{

    @Override
    public int getRank() {
        return 4;
    }

    @Override
    public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {

        Map<Integer, List<PokerHand.Card>> firstCardMap = Helpers.getValueMap(first.getCards());
        int firstPairValue = 0;
        for (Map.Entry<Integer, List<PokerHand.Card>> entry : firstCardMap.entrySet()) {
            if (entry.getValue().size() == 3) {
                firstPairValue = entry.getKey();
            }
        }
        int secondPairValue = 0;
        Map<Integer, List<PokerHand.Card>> secondCardMap = Helpers.getValueMap(second.getCards());
        for (Map.Entry<Integer, List<PokerHand.Card>> entry : secondCardMap.entrySet()) {
            if (entry.getValue().size() == 3) {
                secondPairValue = entry.getKey();
            }
        }
        if (firstPairValue > secondPairValue) {
            return PokerHand.Result.WIN;
        } else if (firstPairValue < secondPairValue) {
            return PokerHand.Result.LOSS;
        } else {
            return PokerHand.Result.TIE;
        }
    }
}
