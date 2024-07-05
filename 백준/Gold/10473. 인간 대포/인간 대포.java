import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static double sx;
    private static double sy;
    private static double ex;
    private static double ey;
    private static int n;
    private static double[][] canons;
    private static double[] dist;
    private static List<double[]> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        sx = Double.parseDouble(tokenizer.nextToken());
        sy = Double.parseDouble(tokenizer.nextToken());
        graph.add(new double[]{sx, sy});
        tokenizer =  new StringTokenizer(input.readLine());
        ex = Double.parseDouble(tokenizer.nextToken());
        ey = Double.parseDouble(tokenizer.nextToken());
        n = Integer.parseInt(input.readLine());
        canons = new double[n][2];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            canons[i][0] = Double.parseDouble(tokenizer.nextToken());
            canons[i][1] = Double.parseDouble(tokenizer.nextToken());
            graph.add(new double[]{canons[i][0], canons[i][1]});
        }
        graph.add(new double[]{ex, ey});
        dist = new double[n + 2];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[0] = 0;
    }

    private static void findAnswer() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sx, sy, 0));
        boolean first = true;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            double curX = node.x;
            double curY = node.y;
            double curTime = node.time;
            for(int i = 1; i < graph.size(); i++) {
                double walktime = distance(new double[]{curX, curY}, graph.get(i)) / 5.0;
                double rocketime = 2 + Math.abs(distance(new double[]{curX, curY}, graph.get(i)) - 50) / 5.0;
                double mintime = Math.min(walktime, rocketime);

                if (first) {
                    mintime = walktime;
                }

                if (dist[i] > mintime + curTime) {
                    dist[i] = mintime + curTime;
                    pq.offer(new Node(graph.get(i)[0], graph.get(i)[1], dist[i]));
                }
            }
            first = false;
        }

        System.out.printf("%.6f\n", dist[n + 1]);
    }

    private static double distance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}
class Node implements Comparable<Node>{
    double x;
    double y;
    double time;
    public Node(double x, double y, double time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.time, o.time);
    }
}