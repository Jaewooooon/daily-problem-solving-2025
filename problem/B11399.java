import java.io.*;
import java.util.*;

/**
 * 백준 11399번 ATM
 * 실버4
 */

public class B11399 {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input/B11399.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i] * (N - i);
        }

        sb.append(sum);
        System.out.println(sb);

        br.close();
    }
}
