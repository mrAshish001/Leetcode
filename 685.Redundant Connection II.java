class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentEdge = new int[n + 1];

        int firstCandidate = -1;
        int secondCandidate = -1;

        for (int i = 0; i < n; i++) {
            int child = edges[i][1];

            if (parentEdge[child] == 0) {
                parentEdge[child] = i;
            } else {
                firstCandidate = parentEdge[child];
                secondCandidate = i;
                break;
            }
        }

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            if (i == secondCandidate) {
                continue;
            }

            int from = edges[i][0];
            int to = edges[i][1];

            int rootFrom = find(from, parent);
            int rootTo = find(to, parent);

            if (rootFrom == rootTo) {
                return firstCandidate == -1
                        ? edges[i]
                        : edges[firstCandidate];
            }

            parent[rootTo] = rootFrom;
        }

        return edges[secondCandidate];
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}
