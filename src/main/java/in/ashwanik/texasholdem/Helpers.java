package in.ashwanik.texasholdem;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Helpers {

    public static PokerHand.Result getHighCardResults(List<PokerHand.Card> firstCardList, List<PokerHand.Card> secondCardList) {
        int index = firstCardList.size() - 1;
        while (index >= 0) {
            if (firstCardList.get(index).getValue() > secondCardList.get(index).getValue()) {
                return PokerHand.Result.WIN;
            } else if (firstCardList.get(index).getValue() < secondCardList.get(index).getValue()) {
                return PokerHand.Result.LOSS;
            }
            index--;
        }
        return PokerHand.Result.TIE;
    }

    public static PokerHand.Result getHighCardResultsForValues(List<Integer> firstCardList, List<Integer> secondCardList) {
        int index = firstCardList.size() - 1;
        while (index >= 0) {
            if (firstCardList.get(index) > secondCardList.get(index)) {
                return PokerHand.Result.WIN;
            } else if (firstCardList.get(index) < secondCardList.get(index)) {
                return PokerHand.Result.LOSS;
            }
            index--;
        }
        return PokerHand.Result.TIE;
    }

    static Map<Integer, List<PokerHand.Card>> getValueMap(List<PokerHand.Card> cards) {
        return cards.stream().collect(groupingBy(PokerHand.Card::getValue));
    }

    static int getCountOfGroupOfASize(Map<Integer, List<PokerHand.Card>> map, int groupSize) {
        return (int) map.entrySet().stream().filter(x -> x.getValue().size() == groupSize).count();
    }

}
