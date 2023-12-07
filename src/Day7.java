
/*
Basically Poker
Hands (sorted by Strength desc):
fiveOfAKind
fourOfAKind
fullHouse
threeOfAKind
twoPair
pair
highCard

Input:
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483

first string = hand
secondString = bet

sort all the hands by strength weakest with rank 1
multiply each hands bet with their rank and att to a total
the total is the answer
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class CardHand {
    String hand;
    int bet;
    int rank; // default = 0
    String type;
    public CardHand(String hand, int bet, String type) {
        this.hand = hand;
        this.bet = bet;
        this.type = type;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getHand() {
        return hand;
    }

    public String getType() {
        return type;
    }

    public int getBet() {
        return bet;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "CardHand{" +
                "hand='" + hand + '\'' +
                ", bet=" + bet +
                ", rank=" + rank +
                ", type='" + type + '\'' +
                '}';
    }
}


public class Day7 {

    static List<CardHand> cardHands = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Day7 day7 = new Day7();
        day7.sortInput();
        cardHands.forEach(System.out::println);
    }

    private void partOne(){

    }
    private void sortInput() throws FileNotFoundException {
        File file = new File("src/Inputs/test.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String hand = line.split(" ")[0].trim();
            int bet = Integer.parseInt(line.split(" ")[1].trim());
            String type = getType(hand);
            cardHands.add(new CardHand(hand,bet,type));
        }
    }
    private String getType(String hand){
        Map<Character,Integer> map = new HashMap<>();
        boolean hasThree = false;
        boolean hasTwo = false;

        for (char c : hand.toCharArray()) { if (!map.containsKey(c)){ map.put(c,1); } else { map.put(c,map.get(c)+1); } }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 5) { return "fiveOfAKind"; }
                if (entry.getValue() == 4) { return "fourOfAKind"; }
                if (entry.getValue() == 3) { hasThree = true; }
                if (entry.getValue() == 2) { if (hasTwo) { return "twoPair"; } hasTwo = true; }
            }
            if (hasThree && hasTwo) { return "fullHouse"; }
            if (hasThree) { return "threeOfAKind"; }
            if (hasTwo) { return "onePair"; }
            return "highCard";
        } // GetType End

    private CardHand determineWinner(CardHand hand1, CardHand hand2){

        return null;
    }


} // DAY 7 End





