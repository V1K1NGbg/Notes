import java.util.Scanner;

public class Bigger_sort {
    public static long[][] map;

    public static long collatz(long n) {
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
                count++;

            } else {
                n = (n * 3 + 1) / 2;
                count += 2;
            }
        }
        return count;
    }

    public static void merge(long[][] workSpace, int first, int second, int upperBound) {
        int j = 0;
        int lowerBound = first;
        int mid = second - 1;
        int n = (upperBound - lowerBound) + 1;
        while (first <= mid && second <= upperBound) {
            if (map[first][1] <= map[second][1]) {
                workSpace[j++] = map[first++];
            } else {
                workSpace[j++] = map[second++];
            }
        }
        while (first <= mid) {

            workSpace[j++] = map[first++];
        }
        while (second <= upperBound) {
            workSpace[j++] = map[second++];
        }
        for (j = 0; j < n; j++) {
            map[lowerBound + j] = workSpace[j];
        }
    }

    public static void recMergeSort(long[][] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            return;
        } else {

            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int min = in.nextInt();
        int max = in.nextInt();

        int index = in.nextInt();
        map = new long[(max - min) + 1][2];

        long StartTime = System.currentTimeMillis();
        for (int i = 0; i <= max - min; i++) {
            map[i][0] = min + i;
            map[i][1] = collatz(min + i);
        }
        long[][] workspace = new long[(max - min) + 1][2];
        recMergeSort(workspace, 0, max - min);
        long EndTime = System.currentTimeMillis();
        System.out.print(map[index - 1][0]);
    }
}