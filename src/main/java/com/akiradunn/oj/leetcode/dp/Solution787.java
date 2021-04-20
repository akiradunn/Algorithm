package com.akiradunn.oj.leetcode.dp;
//787. K 站中转内最便宜的航班
//有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
//
//现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
//
//
//
//示例 1：
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
//示例 2：
//
//输入:
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释:
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
//
//
//提示：
//
//n 范围是 [1, 100]，城市标签从 0 到 n - 1
//航班数量范围是 [0, n * (n - 1) / 2]
//每个航班的格式 (src, dst, price)
//每个航班的价格范围是 [1, 10000]
//k 范围是 [0, n - 1]
//航班没有重复，且不存在自环
//通过次数16,158提交次数43,645
import java.util.HashMap;
import java.util.Map;
public class Solution787 {
    class Solution {
        Map<String, Integer> meno = new HashMap<>();
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            if(flights.length==0) return -1;
            boolean[] visited = new boolean[flights.length];
            return dfs(flights, visited, src, dst, -1, K, 0);
        }

        public int dfs(int[][] flights, boolean[] visited, int src, int dst, int curK, int targetK, int sumTotal){
            String key = genKey(visited, src);
            if(meno.containsKey(key)){
                return meno.get(key);
            }
            if(curK > targetK){
                return -1;
            }
            if(src == dst){
                return sumTotal;
            }
            int res = Integer.MAX_VALUE;
            boolean flag = false;
            for(int i=0; i<flights.length; i++){
                if(sumTotal + flights[i][2] > res){
                    continue;
                }
                if(!visited[i] && flights[i][0] == src){
                    visited[i] = true;
                    int find = dfs(flights, visited, flights[i][1], dst, curK+1, targetK, sumTotal+flights[i][2]);
                    if(find > 0){
                        flag = true;
                        res = Math.min(res, find);
                    }
                    visited[i] = false;
                }
            }
            res = flag ? res : -1;
            meno.put(key, res);
            return res;
        }

        public String genKey(boolean[] visited, int src){
            StringBuilder builder = new StringBuilder(src);
            for(int i=0; i<visited.length; i++){
                builder.append(visited[i] ? '1' : '0');
            }
            return builder.toString();
        }
    }


    static class Solution2 {

        private int[][] graph;
        private boolean[] visited;
        private int res = Integer.MAX_VALUE;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            K = Math.min(K, n - 2);

            this.graph = new int[n][n];
            for (int[] flight : flights) {
                graph[flight[0]][flight[1]] = flight[2];
            }

            this.visited = new boolean[n];
            // 开始深度优先遍历，注意：这里传 K + 1，这是因为 K 次经停，总共 K + 1 个站
            dfs(src, dst, K + 1, 0);

            if (res == Integer.MAX_VALUE) {
                return -1;
            }
            return res;
        }


        /**
         * 从 src 出发，到 dst 为止，最多经过 k 站（这里 k 包括 src）
         *
         * @param src  起点站
         * @param dst  终点站
         * @param k    经过的站点数限制
         * @param cost 已经花费的价格
         */
        private void dfs(int src, int dst, int k, int cost) {
            if (src == dst) {
                res = cost;
                return;
            }

            if (k == 0) {
                return;
            }

            for (int i = 0; i < graph[src].length; i++) {
                if (graph[src][i] > 0) {
                    if (visited[i]) {
                        continue;
                    }

                    // 剪枝：跳过可能产生较高费用的路径，从而选出最少价格
                    if (cost + graph[src][i] > res) {
                        continue;
                    }

                    visited[i] = true;
                    dfs(i, dst, k - 1, cost + graph[src][i]);
                    visited[i] = false;
                }
            }
        }
    }
}
