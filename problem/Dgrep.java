import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Dgrep {
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE * 4);

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3 || !args[0].equals("dgrep")) {
            System.out.println("Usage: dgrep {keyword} {relative path}");
            return;
        }

        String keyword = args[1];
        String relativePath = args[2];
        File root = new File(relativePath);

        if (!root.exists()) {
            System.out.println("파일 또는 디렉토리가 존재하지 않습니다.");
            return;
        }

        long startTime = System.nanoTime();

        List<Future<?>> futures = new ArrayList<>();
        if (root.isDirectory()) {
            scanDirectory(root, keyword, futures);
        } else if (root.isFile()) {
            futures.add(executorService.submit(new FileSearchTask(root, keyword)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                System.err.println("오류 발생: " + e.getMessage());
            }
        }

        executorService.shutdown();

        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("총 실행 시간: %.3f초%n", elapsedTimeInSeconds);
    }

    private static void scanDirectory(File directory, String keyword, List<Future<?>> futures) {
        Queue<File> queue = new ArrayDeque<>();
        queue.add(directory);

        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();

            if (files == null) continue;

            for (File file : files) {
                if (file.isDirectory()) {
                    queue.add(file); // 서브 디렉토리를 큐에 추가
                } else if (file.isFile()) {
                    futures.add(executorService.submit(new FileSearchTask(file, keyword))); // 파일 검색 태스크 추가
                }
            }
        }
    }

    static class FileSearchTask implements Runnable {
        private final File file;
        private final String keyword;

        public FileSearchTask(File file, String keyword) {
            this.file = file;
            this.keyword = keyword;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String str;
                int lineNumber = 1;

                boolean found = false;
                while ((str = br.readLine()) != null) {
                    if (str.contains(keyword)) {
                        if (!found) {
                            sb.append("파일명: ").append(file.getAbsolutePath()).append("\n").append("줄 번호: ");
                            found = true;
                        }
                        sb.append(lineNumber).append(" ");
                    }
                    lineNumber++;
                }

                if (found) {
                    System.out.println(sb);
                }
            } catch (IOException e) {
                System.err.println("파일 처리 중 오류 발생: " + file.getAbsolutePath());
            }
        }
    }
}
