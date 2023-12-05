import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Day3 day3 = new Day3();
        day3.partTwo();
    }

    private void partOne() throws FileNotFoundException {
        File file = new File("src/Inputs/Day3-1.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add("..." + scanner.nextLine() + "...");
        }
        int total = 0;
        for (int i = 0; i <= lines.size() - 1; i++) {
            String currentLine = lines.get(i);
            String previousLine = i == 0 ? lines.get(i) : lines.get(i - 1);
            String nextLine = i == lines.size() - 1 ? lines.get(i) : lines.get(i + 1);
            List<String> nums = getNumbersInLine(currentLine);
            int x = 0;
            if (!nums.isEmpty()) {
                for (String num : nums) {
                    int startIndex = currentLine.indexOf(num);
                    int endIndex = currentLine.indexOf(num) + num.length();
                    String s1 = currentLine.substring(startIndex - 1, endIndex + 1).replaceAll("[0-9]", "");
                    String s2 = previousLine.substring(startIndex - 1, endIndex + 1).replaceAll("[0-9]", "");
                    String s3 = nextLine.substring(startIndex - 1, endIndex + 1).replaceAll("[0-9]", "");
                    String checkerString = (s1 + s2 + s3).replaceAll("\\.", "");
                    if (!checkerString.isEmpty()) { x += Integer.parseInt(num); }
                    currentLine = currentLine.substring(endIndex);
                    previousLine = previousLine.substring(endIndex);
                    nextLine = nextLine.substring(endIndex);
                }
                total += x;
            }
        }
        System.out.println(total);
    }

    private List<String> getNumbersInLine(String line) {
        List<String> nums = new ArrayList<>();
        String y = line.replaceAll("[-%+=*/$&#@]", ".");
        List<String> splitLine = List.of(y.split("\\."));
        for (String s : splitLine) {
            if (!s.equals(".") && !s.isEmpty()) {
                String x = s.trim().replaceAll("[^0-9]", "");
                if (!x.isEmpty()) {
                    nums.add(x);
                }
            }
        }
        return nums;
    }

    private void partTwo() throws FileNotFoundException {
        File file = new File("src/Inputs/Day3-1.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add("..." + scanner.nextLine() + "...");
        }
        int total = 0;
        for (int i = 0; i <= lines.size() - 1; i++) {
            String currentLine = lines.get(i);
            String previousLine = i == 0 ? lines.get(i) : lines.get(i - 1);
            String nextLine = i == lines.size() - 1 ? lines.get(i) : lines.get(i + 1);
            List<Integer> gearIndexes = getGearIndexes(currentLine);

            if (currentLine.contains("*")) {
                for (Integer index : gearIndexes) {
                    if (!currentLine.contains("*")) { continue; }
                    int result = checkForNumbers(List.of(previousLine, currentLine, nextLine), index);
                    checkForNumbers(List.of(previousLine, currentLine, nextLine), index);
                    if (result != -1) { total += result; }
                    currentLine = currentLine.replaceFirst("\\*", ".");
                }
            }
        }
        System.out.println(total);
    }

    private int checkForNumbers(List<String> lines, int index) {
        int count = 0;
        for (String line : lines) {
            boolean hasDigitBefore = Character.isDigit(line.charAt(index - 1));
            boolean hasDigitAfter = Character.isDigit(line.charAt(index + 1));
            boolean hasDigitOnIndex = Character.isDigit(line.charAt(index));
            if (hasDigitAfter && hasDigitBefore && hasDigitOnIndex) {
                count++;
            } else if (hasDigitAfter && hasDigitBefore) {
                count += 2;
            } else if (hasDigitAfter) {
                count++;
            } else if (hasDigitBefore) {
                count++;
            } else if (hasDigitOnIndex) {
                count++;
            }
        }
        if (count == 2) { return getGearRatio(lines, index); }
        return -1;
    }

    private int getGearRatio(List<String> lines, int index) {
        List<Integer> result = new ArrayList<>();
        for (String line : lines) {

            boolean hasDigitBefore = Character.isDigit(line.charAt(index - 1));
            boolean hasDigitAfter = Character.isDigit(line.charAt(index + 1));
            boolean hasDigitOnIndex = Character.isDigit(line.charAt(index));

            if (hasDigitBefore && hasDigitOnIndex && hasDigitAfter) {
                String sub = line.substring(index - 1, index + 2);
                result.add(Integer.parseInt(sub));
            }
            if (!hasDigitBefore && hasDigitOnIndex && !hasDigitAfter) {
                result.add(Integer.parseInt(String.valueOf(line.charAt(index))));
            }
            if (hasDigitOnIndex && hasDigitBefore && !hasDigitAfter) {
                String sub = line.substring(0, index + 1).replaceAll("[^0-9]", ".");
                result.add(Integer.parseInt(sub.substring(sub.lastIndexOf(".") + 1)));
            }
            if (hasDigitOnIndex && hasDigitAfter && !hasDigitBefore) {
                String sub = line.substring(index).replaceAll("[^0-9]", ".");
                result.add(Integer.parseInt(sub.substring(0, sub.indexOf("."))));
            }
            if (hasDigitAfter && hasDigitBefore && !hasDigitOnIndex) {
                String sub = line.substring(index - 3, index + 4);
                for (int i = 0; i < 3; i++) {
                    if (sub.charAt(0) == '.') { sub = sub.substring(1); }
                    if (sub.charAt(sub.length() - 1) == '.') { sub = sub.substring(0, sub.length() - 1); }
                }
                String[] x = sub.replaceAll("\\*", ".").split("\\.");
                result.add(Integer.parseInt(x[0]));
                result.add(Integer.parseInt(x[1]));
            }
            if (hasDigitBefore && !hasDigitOnIndex) {
                String sub = line.substring(0, index).replaceAll("[^0-9]", ".");
                result.add(Integer.parseInt(sub.substring(sub.lastIndexOf(".") + 1)));
            }
            if (hasDigitAfter && !hasDigitOnIndex) {
                String sub = line.substring(index + 1).replaceAll("[^0-9]", ".");
                result.add(Integer.parseInt(sub.substring(0, sub.indexOf("."))));
            }
        }
        if (result.size() == 4 && result.get(0) + result.get(1) == result.get(2) + result.get(3)) {
            result = List.of(result.get(0), result.get(1));
        }
        if (result.size() == 2) {
            return result.get(0) * result.get(1);
        }
        return -1;

    }
    private List<Integer> getGearIndexes(String line) {
        List<Integer> indexes = new ArrayList<>();
        for (int j = 0; j < line.toCharArray().length - 1; j++) { if (line.charAt(j) == '*') { indexes.add(j); } }
        return indexes;
    }
}



