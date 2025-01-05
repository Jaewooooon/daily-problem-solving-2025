/**
 *
 /**
 * 문제: 잃어버린 괄호
 * 분류: 그리디
 * 난이도: 실버 2
 * URL: https://www.acmicpc.net/problem/1541
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1541 {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input/B1541.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums = br.readLine().split("-");

        int answer = 0;
        String[] firstGroup = nums[0].split("\\+");
        for (String num : firstGroup) {
            answer += Integer.parseInt(num);
        }

        for (int i = 1; i < nums.length; i++) {
            String[] group = nums[i].split("\\+");
            int sum = 0;
            for (String num : group) {
                sum += Integer.parseInt(num);
            }
            answer -= sum;
        }

        // 결과 출력
        System.out.println(answer);
    }
}
