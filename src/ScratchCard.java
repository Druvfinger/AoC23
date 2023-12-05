import java.util.List;

public class ScratchCard {
    int id;
    List<String> winningNumbers;
    List<String> scratchedNumbers;
    int matches;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<String> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public List<String> getScratchedNumbers() {
        return scratchedNumbers;
    }

    public void setScratchedNumbers(List<String> scratchedNumbers) {
        this.scratchedNumbers = scratchedNumbers;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public ScratchCard(int id, List<String> winningNumbers, List<String> scratchedNumbers, int matches) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.scratchedNumbers = scratchedNumbers;
        this.matches = matches;
    }
}
