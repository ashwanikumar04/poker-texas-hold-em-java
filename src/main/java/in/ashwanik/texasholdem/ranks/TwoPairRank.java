package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.Helpers;
import in.ashwanik.texasholdem.PokerHand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TwoPairRank implements Rank {

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
}
