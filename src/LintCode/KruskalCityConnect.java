package LintCode;

import java.util.*;

/**
 * @FileName: KruskalCityConnect.java
 * @Description: 最小生成树
 * @Author: ABCpril
 * @Date: 2021/11/27
 */
public class KruskalCityConnect {
    int[] p;

    Comparator<Connection> myComparator = new Comparator<Connection>() {
        @Override
        public int compare(Connection c1, Connection c2) {
            if (c1.cost == c2.cost) {
                if (c1.city1.equals(c2.city1)) {
                    // city1名称相同，按city2排序
                    return c1.city2.compareTo(c2.city2);
                }
                // 第二按city1名称排序
                return c1.city1.compareTo(c2.city1);
            }
            // 优先按连接成本排序
            return c1.cost - c2.cost;
        }
    };

    // 找到可以连接所有城市并且花费最少的边。
    // 如果可以连接所有城市，则返回连接方法，否则返回空列表。
    public List<Connection> lowestCost(List<Connection> connections) {
        // 先提取所有城市名
        Map<String, Integer> mapping = new HashMap<>();
        int idx = 1;
        for (Connection connection : connections) {
            String city1 = connection.city1, city2 = connection.city2;
            if (!mapping.containsKey(city1)) {
                mapping.put(city1, idx++);
            }
            if (!mapping.containsKey(city2)) {
                mapping.put(city2, idx++);
            }
        }
        int cityCnt = mapping.size();
        p = new int[cityCnt + 1];
        for (int i = 1; i <= cityCnt; i++) {
            p[i] = i;
        }
        // 连接排序
        connections.sort(myComparator);

        return kruskal(connections, mapping);
    }

    private List<Connection> kruskal(List<Connection> connections, Map<String, Integer> mapping) {
        List<Connection> res = new ArrayList<>();
        int cityCnt = mapping.size();
        int connectedCityCnt = 1;

        for (Connection connection : connections) {
            int c1 = mapping.get(connection.city1);
            int c2 = mapping.get(connection.city2);
            c1 = find(c1); c2 = find(c2);
            // 新边加入不形成环
            if (c1 != c2) {
                res.add(connection);
                p[c1] = c2;
                connectedCityCnt++;
                if (connectedCityCnt == cityCnt) {
                    return res;
                }
            }
        }
        // 没有连接所有城市，返回空列表
        return new ArrayList<>();
    }

    private int find(int x) {
        if (x != p[x]) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}

// 边缘两端的城市名称和它们之间的开销
class Connection {
    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }
}



