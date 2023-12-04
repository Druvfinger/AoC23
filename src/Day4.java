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
            for (String num : scratchedNumbers) {
                if (winningNumbers.contains(num)) {
                    matches.add(Integer.parseInt(num));
                }
            }
            for (int i = 0; i <= matches.size() - 1; i++) {
                if (i == 0) {
                    points++;
                } else {
                    points *= 2;
                }
            }
            total += points;
        }
        System.out.println(total);
    }

    private void partTwo() throws FileNotFoundException {
        File file = new File("src/Inputs/Test.txt");
        Scanner scanner = new Scanner(file);
        HashMap<Integer, Integer> cardMap = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int cardId = Integer.parseInt(line.split(":")[0].split(" ")[1]);
            List<String> winningNumbers = List.of(line.split(":")[1].split("\\|")[0].trim().replaceAll(" {2}", " ").split(" "));
            List<String> scratchedNumbers = List.of(line.split("\\|")[1].trim().replaceAll(" {2}", " ").split(" "));
            List<Integer> matches = new ArrayList<>();
            for (String num : scratchedNumbers) {
                if (winningNumbers.contains(num)) {
                    matches.add(Integer.parseInt(num));
                }
            }
            cardMap.put(cardId, matches.size());
        }
        System.out.println(cardMap);
        Map<Integer,Integer> finalMap = new HashMap<>(); // cardId / numOfTimesItshows
        for (Map.Entry<Integer,Integer> entry : cardMap.entrySet()){
            int id = entry.getKey();
            int matches = entry.getValue();
            for (int i = 0; i <= matches; i++) {
                    finalMap.put(id, finalMap.containsKey(id) ? finalMap.get(id)+1 :1);
            }
        }
        System.out.println(finalMap);
    }






}
