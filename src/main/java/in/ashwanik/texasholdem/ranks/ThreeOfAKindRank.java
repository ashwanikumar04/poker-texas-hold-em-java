package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.PokerHand;

import java.util.List;
import java.util.Map;

public class ThreeOfAKindRank implements Rank {

    @Override
    public int getRank() {
        return 4;
    }

    @Override
    public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {

        int firstPairValue = getHandDetails(first);
        int secondPairValue = getHandDetails(second);
        if (firstPairValue > secondPairValue) {
            return PokerHand.Result.WIN;
        } else if (firstPairValue < secondPairValue) {
            return PokerHand.Result.LOSS;
        } else {
            return PokerHand.Result.TIE;
        }
    }

    private int getHandDetails(PokerHand hand) {
        int value = 0;
        for (Map.Entry<Integer, List<PokerHand.Card>> entry : hand.getCardsMap().entrySet()) {
            if (entry.getValue().size() == 3) {
                value = entry.getKey();
            }
        }
        return value;
    }
}
