import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TicTacToeFieldToFile {
    public static void main(String[] args) {
        int[][] field = {
                {0, 1, 2},
                {3, 0, 1},
                {2, 3, 0}
        };

        String filePath = "field.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int[] row : field) {
                for (int value : row) {
                    writer.write(String.format("%03d", value));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
