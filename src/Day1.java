import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Day1 day1 = new Day1();
        //day1.partOne();
        day1.partTwo();

    }

    private void partOne() throws FileNotFoundException {
        File file = new File("src/Inputs/Day1-1.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> finalNumbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> digits = new ArrayList<>();
            digits.add(line.replaceAll("[^0-9]", ""));
            for (String digit : digits) {
                String first = String.valueOf(digit.charAt(0));
                String last = String.valueOf(digit.charAt(digit.length() - 1));
                finalNumbers.add(Integer.parseInt(first + last));
            }
        }
        System.out.println(finalNumbers.stream().mapToInt(Integer::intValue).sum());
    }

    private void partTwo() throws FileNotFoundException {
        File file = new File("src/Inputs/Day1-1.txt");
        Scanner scanner = new Scanner(file);
        LinkedHashMap<String, String> numMap = new LinkedHashMap<>();
        numMap.put("one", "o1e");
        numMap.put("two", "t2o");
        numMap.put("three", "t3e");
        numMap.put("four", "f4r");
        numMap.put("five", "f5e");
        numMap.put("six", "s6x");
        numMap.put("seven", "s7n");
        numMap.put("eight", "e8t");
        numMap.put("nine", "n9e");
        List<Integer> finalNumbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> digits = new ArrayList<>();
            String parsedLine = line;
            for (Map.Entry<String, String> entry : numMap.entrySet()) {
                if (line.contains(entry.getKey())) {
                    parsedLine = parsedLine.replaceAll(entry.getKey(), entry.getValue());
                }
            }
            digits.add(parsedLine.replaceAll("[^0-9]", ""));
            for (String digit : digits) {
                String first = String.valueOf(digit.charAt(0));
                String last = String.valueOf(digit.charAt(digit.length() - 1));
                finalNumbers.add(Integer.parseInt(first + last));
            }
        }
        System.out.println(finalNumbers.stream().mapToInt(Integer::intValue).sum());
    }
}