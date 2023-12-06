import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    /*
    winning nums | your nums
    card = first match = 1p otherwise match = points*2
    add points of each card together

    part 2
    you get copies of scratchers instead based on matches
    the copies you get are the ones that follow the card you are on
    ex.
    card 1 4 matches so you get copy of 2,3,4,5
    how many scratchcards do you get
     */
//    HashMap<Integer, Integer> cardMap = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        Day4 day4 = new Day4();
        //day4.partOne();
        day4.partTwo();
    }

    private void partOne() throws FileNotFoundException {
        File file = new File("src/Inputs/Day4.txt");
        Scanner scanner = new Scanner(file);
        int total = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> winningNumbers = List.of(line.split(":")[1].split("\\|")[0].trim().replaceAll(" {2}", " ").split(" "));
            List<String> scratchedNumbers = List.of(line.split("\\|")[1].trim().replaceAll(" {2}", " ").split(" "));
            List<Integer> matches = new ArrayList<>();
            int points = 0;
            for (String num : scratchedNumbers) { if (winningNumbers.contains(num)) { matches.add(Integer.parseInt(num)); } }
            for (int i = 0; i <= matches.size() - 1; i++) { if (i == 0) { points++; } else { points *= 2; } }
            total += points;
        }
        System.out.println(total);
    }
    List<ScratchCard> cardsList = new ArrayList<>();
    private void sortInput () throws FileNotFoundException {
        File file = new File("src/Inputs/Day4.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            int cardId = Integer.parseInt(line.split(":")[0].split("ScratchCard")[1].trim());
            List<String> winningNumbers = List.of(line.split(":")[1].split("\\|")[0].trim().replaceAll(" {2}", " ").split(" "));
            List<String> scratchedNumbers = List.of(line.split("\\|")[1].trim().replaceAll(" {2}", " ").split(" "));
            List<Integer> matches = new ArrayList<>();
            for (String num : scratchedNumbers) {
                if (winningNumbers.contains(num)) {
                    matches.add(Integer.parseInt(num));
                }
            }
            cardsList.add(new ScratchCard(cardId, winningNumbers, scratchedNumbers, matches.size()));
        }
    }
    private void partTwo() throws FileNotFoundException {
        sortInput();
        List<ScratchCard> total = new ArrayList<>();
        for (ScratchCard card : cardsList){ dealWithThisShit(card, cardsList,total); }
        System.out.println(total.size());
    }
    static void dealWithThisShit(ScratchCard card, List<ScratchCard> cardList, List<ScratchCard> total){
        total.add(card);
        for (int i = card.getId(); i < card.getMatches()+card.getId(); i++) { dealWithThisShit(cardList.get(i),cardList,total); }
    }

}// end class

