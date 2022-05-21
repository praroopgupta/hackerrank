import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;



class Result {

    /*
     * Complete the 'fountainActivation' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY locations as parameter.
     */

    public static int fountainActivation(List<Integer> locations) {
        int N = locations.size();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = -1;
        }
        int left;
        int right;
        for (int i = 0; i < N; i++) {
            left = Math.max(i - locations.get(i), 0);
            right = Math.min(i + (locations.get(i) + 1), N);
            arr[left] = Math.max(arr[left], right);
        }
        int ans = 1;
        int next = 0;
        right = arr[0];
        for (int i = 0; i < N; i++) {
            next = Math.max(next, arr[i]);
            if (i == right) {
                ans++;
                right = next;
            }
        }
        return ans;
    }
}

public class ActivateFountain {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int locationsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> locations = IntStream.range(0, locationsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.fountainActivation(locations);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
