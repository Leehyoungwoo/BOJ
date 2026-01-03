import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do { c = readByte(); } while (c <= ' ');

            int sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();

        long[] seg = new long[n];
        for (int i = 0; i < n; i++) {
            int x = fs.nextInt();
            int y = fs.nextInt();
            // x 오름차순 정렬되게 상위 32비트에 x, 하위에 y
            seg[i] = (((long) x) << 32) ^ (y & 0xffffffffL);
        }

        Arrays.sort(seg);

        long curStart = (int) (seg[0] >> 32);
        long curEnd   = (int) seg[0];
        long sum = 0;

        for (int i = 1; i < n; i++) {
            long start = (int) (seg[i] >> 32);
            long end   = (int) seg[i];

            if (start <= curEnd) {
                if (end > curEnd) curEnd = end;
            } else {
                sum += (curEnd - curStart);
                curStart = start;
                curEnd = end;
            }
        }
        sum += (curEnd - curStart);

        System.out.println(sum);
    }
}
