A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also online. Can you help them by writing an algorithm that can rank poker hands?
Task Specification

    Create a poker hand that has a method to compare itself to another poker hand:

    Result PokerHand.compareWith(PokerHand hand);

    A poker hand has a constructor that accepts a string containing 5 cards:

    PokerHand hand = new PokerHand("KS 2H 5C JD TD");

    The characteristics of the string of cards are:
        A space is used as card separator
        Each card consists of two characters

        The first character is the value of the card, valid characters are:

        2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)

        The second character represents the suit, valid characters are:

        S(pades), H(earts), D(iamonds), C(lubs)

    The result of your poker hand compare can be one of these 3 options:

    public enum Result {
        WIN,
        LOSS,
        TIE
    }

Poker Hands

Apply the standard Texas Hold'em rules for ranking the cards. (Ace is the highest valued card, as shown above.)

In order, from highest-ranking to lowest-ranking (e.g., a Straight Flush beats a Four-of-a-Kind), the hands are:

    Straight Flush
        All five cards have the same suit, and can be arranged in sequential order.
        If two hands are both a straight flush, then the hand with the highest card value is the winner.
        If both hands have the same values, it's a tie.

    Four-of-a-Kind
        Four of the cards are the same face value and one non-matching card. Suit does not matter.
        If two hands are four-of-a-kind, then the hand with the higher value four-of-a-kind card is the winner.

    Full House
        Three cards of one face value and two of another. Suit does not matter.
        If two hands are both full houses, then the hand with the higher set of three wins.

    Flush
        All five cards are the same suit.
        If two hands are both a flush, follow high card rules.
        If both flushes have the same values, it's a tie.

    Straight
        The five cards can be arranged in sequential order. Suit does not have to match.
        If two hands are both a straight, then the hand with the highest face value wins.
        If both hands have the same values, it's a tie.

    Three-of-a-Kind
        Three cards with the same value, and two non-matching cards.
        If two hands are both three-of-a-kind, then the hand with the highest set of three wins.

    Two Pair
        Two sets of two matching cards, and a non-matching card, such as A,A,9,9,2.
        If two hands are both two pair, compare the highest set of two from each. Then compare the lowest set of two from each. If they match, follow high-card rules.
        If both hands have the same face values, it's a tie.

    One Pair
        One set of matching cards, and three non-matching cards, such as A,A,7,6,4.
        If two hands both have one pair, compare the sets. If they match, follow high-card rules for the remaining cards.
        If both hands have the same face values, it's a tie.

    High Card
        No matching values or suits, and the cards are not all in a sequence.
        To compare two high card hands, compare the highest value on each hand. If that matches, compare the next highest value, and so on.
        If all cards have the same value, it's a tie.

