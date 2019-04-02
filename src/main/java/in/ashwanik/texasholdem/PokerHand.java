package in.ashwanik.texasholdem;

public class PokerHand {
    public enum Result {TIE, WIN, LOSS}

    PokerHand(String handString) {
    }

    Result compareWith(PokerHand other) {
        return Result.TIE;
    }
}