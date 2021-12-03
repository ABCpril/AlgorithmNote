package LintCode;

import java.util.*;

/**
 * @FileName: TopSort.java
 * @Description: 拓扑排序
 * @Author: ABCpril
 * @Date: 2021/12/02
 */
public class TopSort {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<Integer, Integer> inDegreeMap = getInDegrees(graph);
        Queue<DirectedGraphNode> queue = putIntoQueue(inDegreeMap, graph);
        return bfs(queue, inDegreeMap);
    }

    private Map<Integer, Integer> getInDegrees(ArrayList<DirectedGraphNode> graph) {
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                inDegreeMap.put(neighbor.label, inDegreeMap.getOrDefault(neighbor.label, 0) + 1);
            }
        }
        return inDegreeMap;
    }

    private Queue<DirectedGraphNode> putIntoQueue(Map<Integer, Integer> inDegreeMap, ArrayList<DirectedGraphNode> graph) {
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            // 将入度为 0 的节点作为队列第一层节点
            if (!inDegreeMap.containsKey(node.label)) {
                queue.offer(node);
            }
        }
        return queue;
    }

    private ArrayList<DirectedGraphNode> bfs(Queue<DirectedGraphNode> queue, Map<Integer, Integer> inDegreeMap) {
        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        // 从队列中取出一层节点，并更新出边指向点入度 - 1，如果减少为0，拓展为队列下一层节点
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                DirectedGraphNode curt = queue.poll();
                res.add(curt);
                for (DirectedGraphNode neighbor : curt.neighbors) {
                    int newInDegree = inDegreeMap.get(neighbor.label) - 1;
                    inDegreeMap.put(neighbor.label, newInDegree);
                    // 更新入度后为 0 的节点，加进队列
                    if (newInDegree == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return res;
    }
}

class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}