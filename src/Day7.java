
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
    int rank;
    int type;
    public CardHand(String hand, int bet) { this.hand = hand; this.bet = bet; }
    public void setRank(int rank) { this.rank = rank; }
    public String getHand() { return hand; }
//    public int getType() { return type; }
    public int getBet() { return bet; }
    public int getRank() { return rank; }
    public int getType(String hand) {
        Map<Character, Integer> map = new HashMap<>();
        boolean hasThree = false;
        boolean hasTwo = false;
        for (char c : hand.toCharArray()) { if (!map.containsKey(c)) { map.put(c, 1); } else { map.put(c, map.get(c) + 1); } }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 5) { return 7; }
            if (entry.getValue() == 4) { return 6; }
            if (entry.getValue() == 3) { hasThree = true; }
            if (entry.getValue() == 2) { if (hasTwo) { return 3; } hasTwo = true;}
        }
        if (hasThree && hasTwo) { return 5; }
        if (hasThree) { return 4; }
        if (hasTwo) { return 2; }
        return 1;
    }
    public int getWildType(){
        String newHand = getHighestValue() != null ? this.getHand().replaceAll("J",getHighestValue()) : this.hand;
        System.out.println(newHand);
        return getType(newHand);
    }
    public char getCharWithHighestOccurance(){
        Map<Character,Integer> map = new HashMap<>();
        for (Character c : this.hand.toCharArray()){ if (map.containsKey(c)){ map.put(c,map.get(c)+1); } else { map.put(c,1); } }
        System.out.println(map);

        return 'c';
    }
    private String getHighestValue(){
        List<Character> values = List.of('A','K','Q','J','T','9','8','7','6','5','4','3','2');
        for (Character c : values){ if (this.hand.contains(String.valueOf(c))) { return String.valueOf(c); } }
        return null;
    }
//    private int upgradeType(){
//        return switch (getType()) {
//            case 1 -> 2;  // high becomes pair
//            case 2 -> 4;  // pair becomes triple
//            case 3 -> 5;  // two pair becomes full house
//            case 4 -> 6;  // triple becomes four of a kind
//            case 5 -> 6;  // full house becomes four of a kind
//            case 6 -> 7;  // four of a kind becomes five of a kind
//            default -> type;
//        };
//    }
//    private int getJokers(){
//        int jokers = 0;
//        for (char c : this.hand.toCharArray()) { if (c == 'J') { jokers++; } }
//        return jokers;
//    }
    @Override
    public String toString() {
        return "CardHand{" +
                "hand='" + hand + '\'' +
                ", bet=" + bet +
                ", rank=" + rank +
                ", type=" + type +
                '}';
    }
}


public class Day7 {

    static List<CardHand> cardHands = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Day7 day7 = new Day7();
        day7.sortInput();
        cardHands.sort(new HandComparator());
//        cardHands.forEach(c -> System.out.println(c.hand + " " + c.getType()));
        cardHands.forEach(c -> System.out.println(c.getCharWithHighestOccurance()));

        day7.partOne();

    }

    private void partOne() {
        int rank = cardHands.size();
        for (CardHand cardHand : cardHands){ cardHand.setRank(rank); rank--; }

        int total = 0;
        for (CardHand hand : cardHands) { total += hand.getBet() * hand.getRank(); }
        System.out.println(total);
    }
    private void partTwo(){

    }
    static class HandComparator implements Comparator<CardHand> {
        @Override
        public int compare(CardHand hand1, CardHand hand2) {
//                String cardValues = "23456789TJQKA"; // part1
                String cardValues = "J23456789TQKA";
                int typeCompare = Integer.compare(hand2.getType(hand2.getHand()), hand1.getType(hand1.getHand()));
                if (typeCompare != 0) { return typeCompare; }
                String cards1 = hand1.getHand();
                String cards2 = hand2.getHand();

                for (int i = 0; i < cards1.length(); i++) {
                    int value1 = cardValues.indexOf(cards1.charAt(i));
                    int value2 = cardValues.indexOf(cards2.charAt(i));
                    if (value1 != value2) { return Integer.compare(value2, value1); }
                }
                return 0;
            }
        }
    private void sortInput() throws FileNotFoundException {
        File file = new File("src/Inputs/test.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String hand = line.split(" ")[0].trim();
            int bet = Integer.parseInt(line.split(" ")[1].trim());
//            int type = getType(hand);
            cardHands.add(new CardHand(hand, bet));
        }
    }

}





