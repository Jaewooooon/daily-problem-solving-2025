/**
 * 문제: 수 찾기
 * 분류: 이분 탐색
 * 난이도: 실버 4
 * URL: https://www.acmicpc.net/problem/1920
 */

import java.io.*;
import java.util.*;

public class B1920 {
    public static int N, M;
    public static int[] arr;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("input/B1920.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            System.out.println(binarySearch(Integer.parseInt(st.nextToken())));
        }

        br.close();
    }

    private static int binarySearch(int num) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (arr[middle] == num) {
                return 1;
            } else if (arr[middle] < num) {
                left = middle + 1;
            } else if (arr[middle] > num) {
                right = middle - 1;
            }
        }

        return 0;
    }
}
