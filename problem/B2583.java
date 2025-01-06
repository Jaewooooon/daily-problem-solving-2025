/**
 * 문제: 영역 구하기
 * 분류: BFS
 * 난이도: 실버 1
 * URL: https://www.acmicpc.net/problem/2583
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2583 {
    static int N, M, K;
    static boolean[][] visited;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    visited[y][x] = true;
                }
            }
        }

        List<Integer> areas = new ArrayList<>();
        int division = 0;

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    int count = bfs(y, x);
                    areas.add(count);
                    division++;
                }
            }
        }

        Collections.sort(areas);

        System.out.println(division);
        for (int area : areas) {
            System.out.print(area + " ");
        }
    }

    private static int bfs(int sy, int sx) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] position = q.poll();
            int y = position[0];
            int x = position[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny >= 0 && ny < M && nx >= 0 && nx < N && !visited[ny][nx]) {
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
