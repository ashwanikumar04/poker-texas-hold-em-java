package in.ashwanik.texasholdem.ranks;

import in.ashwanik.texasholdem.Helpers;
import in.ashwanik.texasholdem.PokerHand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TwoPairRank implements Rank{

    @Override
    public int getRank() {
        return 3;
    }

    @Override
    public PokerHand.Result resolveConflict(PokerHand first, PokerHand second) {

        List<Integer> firstValues = new ArrayList<>(Helpers.getValueMap(first.getCards()).keySet());
        firstValues.sort(Comparator.reverseOrder());
        List<Integer> secondValues = new ArrayList<>(Helpers.getValueMap(second.getCards()).keySet());
        secondValues.sort(Comparator.reverseOrder());
        return Helpers.getHighCardResultsForValues(firstValues, secondValues);
    }
}
