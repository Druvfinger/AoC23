import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day6 {

    /*
    you get race time and recordLength
    for each ms you hold button you increaseSpeed by 1 mm/ms
    In how many ways can you win ?
    for each race multiply numOfWaysTowin together
     */


    public static void main(String[] args) {
        Day6 day6 = new Day6();
       // day6.partOne();
        day6.partTwo();
    }

    private void partOne() {
        Map<Integer, Integer> map = Map.of(244, 41, 1047, 66, 1228, 72, 1040, 66);
        int numWaysToWin = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int wins = 0;
            int time = entry.getValue();
            int distance = entry.getKey();
            for (int i = 1; i < time; i++) { if (i * (time - i) > distance) { wins++; } }
            numWaysToWin *= wins;
            wins = 0;
        }
        System.out.println(numWaysToWin);
    }

    private void partTwo() {
        long time = 41667266L;
        long distance = 244104712281040L;
        int wins = 0;
        for (int i = 1; i < time; i++) { if (i * (time - i) > distance) { wins++; } }
        System.out.println(wins);
    }


}
