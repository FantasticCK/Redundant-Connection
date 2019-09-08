package com.CK;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        if (n == 0) return new int[0];

        DSU uf = new DSU(n);
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            if (uf.connected(edge[0], edge[1])) {
                res[0] = edge[0];
                res[1] = edge[1];
            } else {
                uf.union(edge[0], edge[1]);
            }
        }
        return res;
    }

    class DSU {
        int[] parents;
        int[] size;

        DSU(int n) {
            n++;
            parents = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            while (i != parents[i]) {
                parents[i] = find(parents[i]);
                i = parents[i];
            }
            return i;
        }

        private boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private void union(int p, int q) {
            int rp = find(p), rq = find(q);
            if (rp == rq) return;
            if (size[rp] >= size[rq]) {
                size[rp] += size[rq];
                parents[rq] = rp;
            } else {
                size[rq] += size[rp];
                parents[rp] = rq;
            }
        }
    }
}