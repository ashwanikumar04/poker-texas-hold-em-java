package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.Helpers;
import in.ashwanik.texasholdem.PokerHand;

public class HighCardRank implements Rank {

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {
        return Helpers.getHighCardResults(first.getCards(), second.getCards());
    }
}
