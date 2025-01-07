/**
 * 문제: 회의실 배정
 * 분류: 그리디
 * 난이도: 골드 5
 * URL: https://www.acmicpc.net/problem/1931
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1931 {
    public static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input/B1931.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int answer = 1;
        int endTime = arr[0][1];
        for (int i = 1; i < N; i++) {
            if (arr[i][1] >= endTime && arr[i][0] >= endTime) {
                answer += 1;
                endTime = arr[i][1];
            }
        }

        System.out.println(answer);
    }
}
