
import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[][] up;
    private int[] tin, tout, depth;
    private int timer, LOG;
    private BIT bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        timer = 0;
        dfs(0, 0);

        bit = new BIT(n + 2);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int mask =
                        (1 << (chars[u] - 'a')) ^
                        (1 << (c - 'a'));

                    bit.update(tin[u], mask);
                    bit.update(tout[u] + 1, mask);

                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int l = lca(u, v);

                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ (1 << (chars[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int root, int parent) {
        int[] nodes = new int[graph.length];
        int[] parents = new int[graph.length];
        int[] index = new int[graph.length];

        int top = 0;
        nodes[0] = root;
        parents[0] = parent;

        while (top >= 0) {
            int u = nodes[top];
            int p = parents[top];

            if (index[top] == 0) {
                tin[u] = ++timer;
                up[0][u] = p;

                for (int j = 1; j < LOG; j++) {
                    up[j][u] = up[j - 1][up[j - 1][u]];
                }
            }

            if (index[top] < graph[u].size()) {
                int v = graph[u].get(index[top]++);

                if (v == p) continue;

                depth[v] = depth[u] + 1;
                nodes[++top] = v;
                parents[top] = u;
                index[top] = 0;
            } else {
                tout[u] = timer;
                top--;
            }
        }
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                a = up[j][a];
            }
        }

        if (a == b) return a;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][a] != up[j][b]) {
                a = up[j][a];
                b = up[j][b];
            }
        }

        return up[0][a];
    }

    private static class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int i, int mask) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= mask;
            }
        }

        int query(int i) {
            int result = 0;

            for (; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
