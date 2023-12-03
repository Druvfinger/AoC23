import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Day3 day3 = new Day3();
        day3.partOne();
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
                    if (!checkerString.isEmpty()) {
                        x += Integer.parseInt(num);
                    }
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

}
