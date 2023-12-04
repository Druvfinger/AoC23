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

    /*
     find all places where * is adjacent to exactly 2 digits
     multiply those 2 numbers
     then add all numbers together
      */
    //System.out.println(previousLine);
    //                System.out.println(currentLine);
    //                System.out.println(nextLine);
    private void partTwo() throws FileNotFoundException {
        File file = new File("src/Inputs/Day3-1.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add("..." + scanner.nextLine() + "...");
        }
        int total = 0;
        for (int i = 0; i <= lines.size()-1; i++) {
            String currentLine = lines.get(i);
            String previousLine = i == 0 ? lines.get(i) : lines.get(i - 1);
            String nextLine = i == lines.size() - 1 ? lines.get(i) : lines.get(i + 1);
            List<Integer> gearIndexes = getGearIndexes(currentLine);
            System.out.println("Line");
            System.out.println(i+1);
            if (currentLine.contains("*")) {
                for (Integer index : gearIndexes){ // I THINK THIS PART IS OK
                    if (!currentLine.contains("*")){continue;}
                    int result = checkForNumbers(List.of(previousLine, currentLine, nextLine), index);
                    if (result != -1) {
                        total += result;
                    }
                    currentLine = currentLine.replaceFirst("\\*", ".");
                }
            }
        }
        System.out.println(total);
    }

    private int checkForNumbers(List<String> lines, int index) {
        int count = 0;

        for (String line:lines){
            boolean hasDigitBefore = Character.isDigit(line.charAt(index - 1));
            boolean hasDigitAfter = Character.isDigit(line.charAt(index + 1));
            boolean hasDigitOnIndex = Character.isDigit(line.charAt(index));
            if (hasDigitAfter && hasDigitBefore && hasDigitOnIndex){
                count++;
            } else if (hasDigitAfter && hasDigitBefore) {
                count += 2;
            } else if (hasDigitAfter){
                count++;
            } else if (hasDigitBefore) {
                count++;
            }else if (hasDigitOnIndex) {
                count++;
            }
        }
        if (count == 2){
            System.out.println();
           return getGearRatio(lines, index);
        }
        return -1;
    }

    private int getGearRatio(List<String> lines, int index) {
        List<Integer> result = new ArrayList<>();

        String before = lines.get(0);
        String current = lines.get(1);
        String next = lines.get(2);

        String beforeSub = before.substring(index-3,index+4);
        String currentSub = current.substring(index-3,index+4);
        String nextSub = next.substring(index-3,index+4);

        System.out.println(beforeSub);
        System.out.println(currentSub);
        System.out.println(nextSub);


        return -1;
//        for (String line : lines){
//            boolean hasDigitBefore = Character.isDigit(line.charAt(index - 1));
//            boolean hasDigitAfter = Character.isDigit(line.charAt(index + 1));
//            boolean hasDigitOnIndex = Character.isDigit(line.charAt(index));
//
//            if (hasDigitBefore && hasDigitOnIndex && hasDigitAfter){
//                String sub = line.substring(index-1,index+2);
//                System.out.println("b o a "+sub);
//                result.add(Integer.parseInt(sub));
//                System.out.println(result);
//            }
//            if (!hasDigitBefore && hasDigitOnIndex && !hasDigitAfter){
//                result.add(Integer.parseInt(String.valueOf(line.charAt(index))));
//                System.out.println("!b o !a "+line.charAt(index));
//                System.out.println(result);
//            }
//            if (hasDigitOnIndex && hasDigitBefore && !hasDigitAfter){
//                String sub = line.substring(0,index+1).replaceAll("[^0-9]",".");
//                System.out.println("o b !a "+sub.substring(sub.lastIndexOf(".")+1));
//                result.add(Integer.parseInt(sub.substring(sub.lastIndexOf(".")+1)));
//                System.out.println(result);
//            }
//            if (hasDigitOnIndex && hasDigitAfter && !hasDigitBefore){
//                String sub = line.substring(index).replaceAll("[^0-9]",".");
//                System.out.println("o a !b "+sub.substring(0,sub.indexOf(".")));
//                result.add(Integer.parseInt(sub.substring(0,sub.indexOf("."))));
//                System.out.println(result);
//            }
//            if (hasDigitAfter && hasDigitBefore && !hasDigitOnIndex){
//                String sub = line.substring(index-3,index+4);
//
//                for (int i = 0; i < 3; i++) {
//                    if (sub.charAt(0) == '.'){
//                        sub = sub.substring(1);
//                    }
//                    if (sub.charAt(sub.length()-1) == '.'){
//                        sub = sub.substring(0,sub.length()-1);
//                    }
//                }
//                System.out.println("a b !o "+sub);
//                String[] x = sub.replaceAll("\\*",".").split("\\.");
////                System.out.println("x0 " +x[0] + " x1 "+ x[1]);
//                result.add(Integer.parseInt(x[0]));
//                result.add(Integer.parseInt(x[1]));
//                System.out.println("RESULT: "+result);
//            }
//            if (hasDigitBefore && !hasDigitOnIndex){
//                String sub = line.substring(0,index).replaceAll("[^0-9]",".");
//                System.out.println("b !o "+sub.substring(sub.lastIndexOf(".")+1));
//                result.add(Integer.parseInt(sub.substring(sub.lastIndexOf(".")+1)));
//                System.out.println(result);
//            }
//            if (hasDigitAfter && !hasDigitOnIndex){
//                String sub = line.substring(index+1).replaceAll("[^0-9]",".");
//                System.out.println("a !o "+sub.substring(0,sub.indexOf(".")));
//                result.add(Integer.parseInt(sub.substring(0,sub.indexOf("."))));
//                System.out.println(result);
//            }
//        }
//          System.out.println(result);
////        if (result.size() == 4 && result.get(0)+result.get(1) == result.get(2)+result.get(3)){
////            result = List.of(result.get(0),result.get(1));
////        }
////        System.out.println(result);
//        if (result.size() == 2){
//            return result.get(0) * result.get(1);
//        }
//        return -1;
    }
    private List<Integer> getGearIndexes(String line) {
        List<Integer> indexes = new ArrayList<>();
        for (int j = 0; j < line.toCharArray().length-1; j++) {
            if (line.charAt(j) == '*'){
                indexes.add(j);
            }
        }
        return indexes;
    }
}
