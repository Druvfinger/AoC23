import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

record Node(String Id,String left, String right) {
}

public class Day8 {

    static char[] instructions;
    static List<Node> nodes = new ArrayList<>();
    static String id = "AAA"; //part 1
    public static void main(String[] args) throws FileNotFoundException {
        Day8 day8 = new Day8();
        day8.sortInput("src/Inputs/test.txt");
        //day8.partOne("ZZZ");
        day8.partTwo();
    }
    public static int gcd(int a, int b) { if (b == 0) { return a; } else { return gcd(b, a % b); } }
    public static int lcm(int a, int b) { return (a * b) / gcd(a, b); }

    private void partTwo(){
        List<Node> currentNodes = new ArrayList<>();
        for (Node node : nodes) { if (node.Id().endsWith("A")) { currentNodes.add(node); } }
        int steps = findStepsToZ(currentNodes);
        System.out.println(steps);
    }

    public static int findStepsToZ(List<Node> currentNodes) {
        int steps = 0;
        while (true) {
            List<Node> nextNodes = new ArrayList<>();
            for (Node node : currentNodes) {
                for (Node nextNode : nodes) {
                    if (nextNode.Id().equals(node.left()) || nextNode.Id().equals(node.right())) { nextNodes.add(nextNode); }
                }
            }
            currentNodes = nextNodes;
            steps++;
            if (currentNodes.stream().allMatch(node -> node.Id().endsWith("Z"))) { break; }
        }
        return steps;
    }



    private void partOne(String decider) {
        int total = 0;
        boolean done = false;
        while (!done) { if (id.equals(decider)) { done = true; } total += runInstructions(); }
        System.out.println(total);
    }
    public String getNextMapId(String id, Character instruction) {
        Node node =  nodes.stream().filter(n-> n.Id().equals(id)).toList().get(0);
        switch (instruction) {
            case 'R' -> { return node.right(); }
            case 'L' -> { return node.left(); }
        }
        return "ERROR";
    }
    public int runInstructions() {
        int steps = 0;
        for (int i = 0; i <= instructions.length - 1; i++) {
            char instruction = instructions[i];
            if (id.equals("ZZZ")) { return steps; } // part1
            id = getNextMapId(id, instruction);
            steps++;
        }
        return steps;
    }
    private void sortInput(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<String> tempList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) continue;
            tempList.add(line.trim());
        }
        instructions = tempList.get(0).toCharArray();
        List<String> lines = cleanUpLines(tempList.subList(1, tempList.size()));
        for (String line : lines) {
            String id = line.split(",")[0].trim();
            String leftVal = line.split(",")[1].trim();
            String rightVal = line.split(",")[2].trim();
            nodes.add(new Node(id,leftVal, rightVal));
        }
    }
    private List<String> cleanUpLines(List<String> list) {
        List<String> returnList = new ArrayList<>();
        for (String line : list) {
            String x = line.replace("=", ",").trim().replace("(", "").trim().replace(")", "").trim();
            returnList.add(x);
        }
        return returnList;
    }


}
