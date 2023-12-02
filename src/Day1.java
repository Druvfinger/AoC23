import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Day1 day1 = new Day1();
        day1.partOne();

    }

    private void partOne() throws FileNotFoundException {
        File file = new File("src/Inputs/Day1-1.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> finalNumbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            List<String> digits = new ArrayList<>();
            String line = scanner.nextLine();
            digits.add(line.replaceAll("[^0-9]", ""));
            for (String digit : digits) {
                String first = String.valueOf(digit.charAt(0));
                String last = String.valueOf(digit.charAt(digit.length() - 1));
                finalNumbers.add(Integer.parseInt(first + last));
            }
        }
        System.out.println(finalNumbers.stream().mapToInt(Integer::intValue).sum());
    }

}