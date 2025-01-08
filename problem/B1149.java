import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제: RGB거리
 * 분류: 그리디
 * 난이도: 실버 1
 * URL: https://www.acmicpc.net/problem/1149
 */

public class B1149 {
    public static int N;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input/B1149.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] dp = new int[N][3];
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
        }

        System.out.println(Arrays.stream(dp[N-1]).min().orElseThrow());
    }
}
