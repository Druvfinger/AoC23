import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    /*
    puzzleInput = Almanac
    seeds: 79 14 55 13

    seed-to-soil map:
    50 98 2
    52 50 48
    Line 1 (50,98,2)
    Destination range start = 50 (first num in line 1)
    Source Range Start = 98 (second num in line 1)
    range length = 2 (last num in line 1)
    Destination range is then 50,51
    Source Range is then 98,99
    if seednum/source is in source range it goes in
    source/SeedNum 98 goes to soil 50
    Source/SeedNum 99 goes to soil 51

    Line 2
    Destination Range start = 52 (first num in line 1)
    Source Range Start  = 50 (second num in line 2)
    contains = 48 values (last num in line 2)
    Source range = 52-97
    Destination range = 52-99

    seed num 53 would correspond to soil 55

    if source number is not mapped its destination number is the same as it sourceNumber


    find the lowest location number that corresponds to any of the initial seeds
    WTF IS THIS DESCRIPTION ME DUMBO NO UNDERSTAND
     */

    List<Long> seeds = new ArrayList<>();
    static Map<String, List<Long>> map = Map.of(
            "seed-to-soil map:", new ArrayList<>(),
            "soil-to-fertilizer map:",new ArrayList<>(),
            "fertilizer-to-water map:", new ArrayList<>(),
            "water-to-light map:", new ArrayList<>(),
            "light-to-temperature map:", new ArrayList<>(),
            "temperature-to-humidity map:", new ArrayList<>(),
            "humidity-to-location map:", new ArrayList<>()
    );


    public static void main(String[] args) throws FileNotFoundException {
        Day5 day5 = new Day5();
        day5.sortInput("src/Inputs/Day5Test.txt");
        day5.partOne();
    }
    public void sortInput(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String activeKey = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("seeds:")) {
                seeds = Arrays.stream(line.split(":")[1].trim().split(" ")).map(Long::parseLong).toList();
            }
            if (map.containsKey(line)) { activeKey = line; }
            if (!line.startsWith("seeds:") && !map.containsKey(line)){
                List<Long> nums = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
                map.get(activeKey).addAll(nums);
            }
        }
    } // sortInput End
    public void partOne(){
        Map<Long, Long> locations = new HashMap<>();
        for (Long seed : seeds){
            for (Map.Entry<String, List<Long>> entry : map.entrySet()){
                List<Long> list = entry.getValue();
                for (int i = 0; i < list.size(); i+= 3) {
                    long destinationRangeStart = list.get(i);
                    long sourceRangeStart = list.get(i+1);
                    long rangeLength = list.get(i+2);
                    long destination = getDestination(seed,destinationRangeStart,sourceRangeStart,rangeLength);
                    locations.putIfAbsent(seed,destination);
                }
            }
        }
        System.out.println(locations);
    }// Part 1 End
    public long getDestination(Long seed,long destinationRangeStart,long sourceRangeStart, long rangeLength){
        long sourceRangeEnd = sourceRangeStart + rangeLength - 1;

        if (seed >= sourceRangeStart && seed <= sourceRangeEnd) {
            long offset = seed - sourceRangeStart;
            return destinationRangeStart + offset;
        }
        return seed;
    }
}//Day 5 End


