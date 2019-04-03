package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.PokerHand;

public interface Rank {
    int getRank();
    PokerHand.Result resolveConflict(PokerHand first, PokerHand second);
}
