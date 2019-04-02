package in.ashwanik.texasholdem;


abstract class PokerHandHandler {
    PokerHandHandler pokerHandHandler;

    void next(PokerHandHandler pokerHandHandler) {
        this.pokerHandHandler = pokerHandHandler;
    }

    abstract PokerHand.Result handle(PokerHand first, PokerHand other);

    PokerHand.Result getResult(Tuple<Boolean, Integer> current, Tuple<Boolean, Integer> otherHand) {
        if (current.value1 && otherHand.value1) {
            if (current.value2.equals(otherHand.value2)) {
                return PokerHand.Result.TIE;
            }
            return current.value2 > otherHand.value2 ? PokerHand.Result.WIN : PokerHand.Result.LOSS;
        }

        if (current.value1) {
            return PokerHand.Result.WIN;
        }

        if (otherHand.value1) {
            return PokerHand.Result.LOSS;
        }
        return null;
    }
}
