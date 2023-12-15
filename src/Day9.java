import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        Day9 day9 = new Day9();
        List<List<Long>> lines = day9.sortInput("src/Inputs/Day9.txt");
        day9.getValueOfLine(lines.get(0));
    }

    public List<List<Long>> sortInput(String path) throws FileNotFoundException {
        List<List<Long>> returnList = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            returnList.add(Arrays.stream(line.split(" ")).map(Long::parseLong).toList());
        }
        return returnList;
    }

    public void partOne(){

    }
    public long getValueOfLine(List<Long> sequence){
        List<List<Long>> allSequencesOfLine = new ArrayList<>();
        allSequencesOfLine.add(sequence);
        List<Long> newSequence = new ArrayList<>();
        while (!isAllZeros(newSequence)){
            List<Long> current =allSequencesOfLine.get(allSequencesOfLine.size()-1);
        }
        System.out.println(allSequencesOfLine);
        return -1L;
    }

    public boolean isAllZeros(List<Long> list){ for (Long l : list) { if (l != 0) { return false; } } return true; }
}
