
/*
Part 1
. is not a symbol
if number is adjacent to a symbol add number to total

how: maybe pick out all special chars and then look for their index in line
then check this, previous and nextLine at those index as well as index -1 and index +1 ?


*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    List<String> specialChars = List.of("-", "%", "+", "=", "*", "/", "$", "&", "#", "@");

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
            System.out.println(i+1);
            String previousLine = i == 0 ? lines.get(i) : lines.get(i - 1);
            String nextLine = i == lines.size() - 1 ? lines.get(i) : lines.get(i + 1);
            List<String> nums = getNumbersInLine(currentLine);
            int x = 0;
            if (!nums.isEmpty()) {
                List<String> usedNums = new ArrayList<>();
                for (String num : nums) {
                    int startIndex = usedNums.contains(num) ? currentLine.lastIndexOf(num) :currentLine.indexOf(num);
                    int endIndex = usedNums.contains(num) ? currentLine.lastIndexOf(num) + num.length() : currentLine.indexOf(num) + num.length();
                    String s1 = currentLine.substring(startIndex - 1, endIndex + 1);
                    String s2 = previousLine.substring(startIndex - 1, endIndex + 1);
                    String s3 = nextLine.substring(startIndex - 1, endIndex + 1);
                    String checkerString = s1+s2+s3;
                    for (String s: specialChars){
                        if (checkerString.contains(s)){
                            //System.out.println("Num has adjacent symbol " + num);
                            x += Integer.parseInt(num);
                        }
                    }
                    usedNums.add(num);
                }
                System.out.println("Line Total: " +x);
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
