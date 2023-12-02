/*
Part 1
In bag:
12 red
13 green
14 blue
determine if game is possible
if it is add ID to a total

Part 2
hitta högsta värdet per färg per spel
gångra ihop dem
addera alla spels värden
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        Day2 day2 = new Day2();
        day2.partTwo();
    }

    private void partOne() throws FileNotFoundException {

        File file = new File("src/Inputs/Day2-1.txt");
        Scanner scanner = new Scanner(file);
        Map<String, Integer> bagContents = Map.of("red", 12, "green", 13, "blue", 14);
        int total = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(":");
            int id = Integer.parseInt(splitLine[0].split(" ")[1].trim());
            String results = splitLine[1].substring(1).replaceAll(";", ",");
            List<String> splitResults = List.of(results.split(","));
            boolean isPossible = true;
            for (String s : splitResults) {
                String[] x = s.trim().split(" ");
                if (bagContents.get(x[1]) < Integer.parseInt(x[0])) {
                    isPossible = false;
                }
            }
            if (isPossible) total += id;
        }
        System.out.println(total);
    }

    private void partTwo() throws FileNotFoundException {
        File file = new File("src/Inputs/Day2-1.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> finalNumbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(":");
            String results = splitLine[1].substring(1).replaceAll(";", ",");
            List<String> splitResults = List.of(results.split(","));
            int redVal = -1;
            int blueVal = -1;
            int greenVal = -1;
            for (String s : splitResults) {
                String[] x = s.trim().split(" ");
                int val = Integer.parseInt(x[0]);
                switch (x[1]) {
                    case "red" -> {if (val > redVal){redVal = val;}}
                    case "blue" -> {if (val > blueVal){blueVal = val;}}
                    case "green" -> {if (val > greenVal){greenVal = val;}}
                }
            }
            finalNumbers.add(redVal*blueVal*greenVal);
        }
        System.out.println(finalNumbers.stream().mapToInt(Integer::intValue).sum());
    }
}
