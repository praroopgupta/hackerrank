package main.java;

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
     * Complete the 'minTime' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY batchSize
     *  2. INTEGER_ARRAY processingTime
     *  3. INTEGER_ARRAY numTasks
     */

    public static long minTime(List<Integer> batchSize, List<Integer> processingTime, List<Integer> numTasks) {
    long bn = batchSize.size();
    long pn = processingTime.size();
    long nn = numTasks.size();
    
    if(bn != pn || pn != nn || nn != bn) {
        return 0;
    }
    if(bn == 0 || pn == 0 || nn == 0){
        return 0;
    }
    long max = 0;
    for(int i=0; i<bn ; i++) {
        long t = numTasks.get(i)%batchSize.get(i);
        long temp = numTasks.get(i)/batchSize.get(i) + (t == 0 ? 0 : 1);
        max = Math.max(max, temp*processingTime.get(i));
    }   
    return max;
    }

}
public class TaskQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int batchSizeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> batchSize = IntStream.range(0, batchSizeCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int processingTimeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> processingTime = IntStream.range(0, processingTimeCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int numTasksCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> numTasks = IntStream.range(0, numTasksCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.minTime(batchSize, processingTime, numTasks);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
