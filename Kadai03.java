import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;

class Kaidai03 {
    public static void main(String[] args) {

        // creat a list to save all the data
        var arr = new ArrayList<String[]>();
        String separator = System.getProperty("line.separator");

        Path path = Paths.get(".", "scores.csv");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(",");

                // clear the space
                for (int i = 0; i < datas.length; i++) {
                    datas[i] = datas[i].trim();
                }

                arr.add(count, datas);
            }

            // remove the first line with the message "id, name, score, date"
            // and paste it to a string arr
            String[] datas = arr.remove(arr.size() - 1);

            // increase the list
            arr.sort((x, y) -> Integer.parseInt(x[2]) -
                    Integer.parseInt(y[2]));

            System.out.printf("%-10s %-10s %-10s %-10s%s", datas[2], datas[0], datas[1], datas[3], separator);

            // iriterate from the last
            for (int i = arr.size() - 1; i > -1; i--) {
                System.out.printf("%-10s %-10s %-10s %-10s%s",
                        arr.get(i)[2], arr.get(i)[0], arr.get(i)[1], arr.get(i)[3], separator);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}