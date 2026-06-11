import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int maxDepth = 0;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int node = q.poll();

                for (int next : graph[node]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }

            if (!q.isEmpty()) {
                maxDepth++;
            }
        }

        return (int) modPow(2, maxDepth - 1);
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}