import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//7,"fiveOfAKind",
//6,"fourOfAKind",
//5,"fullHouse",
//4,"threeOfAKind",
//3,"twoPair",
//2,"pair",
//1,"highCard"
class CardHand {
    String hand;
    int bet;
    int rank;
    int type;
    public CardHand(String hand, int bet, int type) { this.hand = hand; this.bet = bet; this.type = type; }
    public void setRank(int rank) { this.rank = rank; }
    public void setType(int type) { this.type = type; }
    public String getHand() { return hand; }
    public int getBet() { return bet; }
    public int getType() { return type; }
    public int getRank() { return rank; }
    public String getWildHand() { return this.hand.replace("J", getCharWithHighestOccurrence()); }
    public String getCharWithHighestOccurrence() {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : this.hand.toCharArray()) {if (map.containsKey(c)) { map.put(c, map.get(c) + 1); } else { map.put(c, 1); } }
        char returnChar = 'X';
        int highVal = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 'J' && entry.getValue() == 5) { return "A"; }
            if (entry.getKey() != 'J'){
                if (entry.getValue() == highVal){ if (entry.getKey() > returnChar){ returnChar = entry.getKey(); } }
                if (entry.getValue() > highVal) { highVal = entry.getValue(); returnChar = entry.getKey(); }
            }
        }
        return String.valueOf(returnChar);
    }
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
        //day7.partOne();
        day7.partTwo();
    }
    private void partOne() {
        cardHands.sort(new HandComparator());
        System.out.println(calculateTotal());
    }
    private void partTwo() {
        cardHands.forEach(cardHand -> cardHand.setType(getHandType(cardHand.getWildHand())));
        cardHands.sort(new HandComparator());
        System.out.println(calculateTotal());
    }
    public int calculateTotal(){
        int rank = cardHands.size();
        int total = 0;
        for (CardHand cardHand : cardHands) { cardHand.setRank(rank); rank--; }
        for (CardHand hand : cardHands) { total += hand.getBet() * hand.getRank(); }
        return total;
    }
    static class HandComparator implements Comparator<CardHand> {
        @Override
        public int compare(CardHand hand1, CardHand hand2) {
//            String cardValues = "23456789TJQKA"; // part1
            String cardValues = "J23456789TQKA"; // part2
            int typeCompare = Integer.compare(hand2.getType(), hand1.getType());
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
        File file = new File("src/Inputs/Day7.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String hand = line.split(" ")[0].trim();
            int bet = Integer.parseInt(line.split(" ")[1].trim());
            cardHands.add(new CardHand(hand, bet, getHandType(hand)));
        }
    }
    static public int getHandType(String hand) {
        Map<Character, Integer> map = new HashMap<>();
        boolean hasThree = false;
        boolean hasTwo = false;
        for (char c : hand.toCharArray()) { if (!map.containsKey(c)) { map.put(c, 1); } else { map.put(c, map.get(c) + 1); } }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 5) { return 7; }
            if (entry.getValue() == 4) { return 6; }
            if (entry.getValue() == 3) { hasThree = true; }
            if (entry.getValue() == 2) { if (hasTwo) { return 3; } hasTwo = true; }
        }
        if (hasThree && hasTwo) { return 5; }
        if (hasThree) { return 4; }
        if (hasTwo) { return 2; }
        return 1;
    }

}





