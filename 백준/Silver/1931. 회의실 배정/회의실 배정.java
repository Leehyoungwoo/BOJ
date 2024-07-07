import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleBufferedReader input = new SimpleBufferedReader(8192, new InputStreamReader(System.in));
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetings, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int prevEndTime = meetings[0][1];
        for (int i = 1; i < N; i++) {
            if (meetings[i][0] >= prevEndTime) {
                count++;
                prevEndTime = meetings[i][1];
            }
        }

        System.out.println(count);
    }
}
class SimpleBufferedReader {

    private InputStreamReader inputStreamReader;
    private char[] buffer;
    private int bufferSize;
    private int bufferPosition;
    private int bufferCount;

    public SimpleBufferedReader(int bufferSize, InputStreamReader inputStreamReader) {
        this.bufferSize = bufferSize;
        this.inputStreamReader = inputStreamReader;
        this.buffer = new char[bufferSize];
        this.bufferPosition = 0;
        this.bufferCount = 0;
    }

    private void fillBuffer() throws IOException {
        bufferCount = inputStreamReader.read(buffer, 0, bufferSize);
        bufferPosition = 0;
    }

    public String readLine() throws IOException {
        StringBuilder line = new StringBuilder();
        boolean endLine = false;

        while (!endLine) {
            if (bufferPosition >= bufferCount) {
                fillBuffer();
                if (bufferCount == -1) {
                    return line.length() > 0 ? line.toString() : null;
                }
            }

            for(; bufferPosition < bufferCount; bufferPosition++) {
                char ch = buffer[bufferPosition];
                if (ch == '\n') {
                    endLine = true;
                    bufferPosition++;
                    break;
                } else if (ch == '\r') {
                    endLine = true;
                    if (bufferPosition + 1 < bufferCount && buffer[bufferPosition + 1] == '\n') {
                        bufferPosition++;
                    }
                    bufferPosition++;
                    break;
                } else {
                    line.append(ch);
                }
            }
        }
        return line.toString();
    }

    public void close() throws IOException {
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
    }
}