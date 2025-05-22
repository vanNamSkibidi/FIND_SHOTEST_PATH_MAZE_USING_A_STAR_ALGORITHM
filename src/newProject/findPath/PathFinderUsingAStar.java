package newProject.findPath;

import newProject.test2.GamePanel;

import java.util.*;

public class PathFinderUsingAStar {
    GamePanel gp;

    public static class Node implements Comparable<Node> {
        public int row, col;       // vị trí trên map
        public int gCost;          // chi phí từ start đến node này
        public int hCost;          // ước lượng chi phí từ node đến đích
        public int fCost;          // tổng chi phí = gCost + hCost
        public Node parent;        // node cha để truy vết đường đi

        Node(int row, int col, int gCost, int hCost, Node parent) {
            this.row = row;
            this.col = col;
            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = gCost + hCost;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            if (other == null) return -1;
            if (this.fCost != other.fCost) {
                return Integer.compare(this.fCost, other.fCost);
            }
            // Ưu tiên node có heuristic nhỏ hơn nếu fCost bằng nhau
            return Integer.compare(this.hCost, other.hCost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public PathFinderUsingAStar(GamePanel gp) {
        this.gp = gp;
    }

    private PriorityQueue<Node> openList;
    private Set<String> closedSet;
    private Map<String, Node> allNodes;
    public ArrayList<String> path;

    // Tạo key để đánh dấu node trên map
    private String key(int row, int col) {
        return row + "," + col;
    }

    public void findPathUsingAStar(int startRow, int startCol, int targetRow, int targetCol) {
        int rows = gp.tileManager.map.length;
        int cols = gp.tileManager.map[0].length;

        openList = new PriorityQueue<>();
        closedSet = new HashSet<>();
        allNodes = new HashMap<>();
        path = new ArrayList<>();

        Node startNode = new Node(startRow, startCol, 0, heuristic(startRow, startCol, targetRow, targetCol), null);
        openList.add(startNode);
        allNodes.put(key(startRow, startCol), startNode);

        Node endNode = null;

        // 4 hướng di chuyển: lên, xuống, trái, phải
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current == null) break;

            String currentKey = key(current.row, current.col);
            if (closedSet.contains(currentKey)) continue;
            closedSet.add(currentKey);

            if (current.row == targetRow && current.col == targetCol) {
                endNode = current;
                break;
            }

            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (!isValid(newRow, newCol, rows, cols)) continue;
                String neighborKey = key(newRow, newCol);
                if (closedSet.contains(neighborKey)) continue;

                int gCost = current.gCost + 1;
                int hCost = heuristic(newRow, newCol, targetRow, targetCol);

                Node neighbor = allNodes.get(neighborKey);

                if (neighbor == null) {
                    neighbor = new Node(newRow, newCol, gCost, hCost, current);
                    allNodes.put(neighborKey, neighbor);
                    openList.add(neighbor);
                } else if (gCost < neighbor.gCost) {
                    // Cập nhật chi phí tốt hơn và cha mới
                    neighbor.gCost = gCost;
                    neighbor.hCost = hCost;
                    neighbor.fCost = gCost + hCost;
                    neighbor.parent = current;

                    // Cập nhật lại vị trí trong PriorityQueue
                    openList.remove(neighbor);
                    openList.add(neighbor);
                }
            }
        }

        if (endNode != null) {
            reconstructPath(endNode);
        } else {
            path.add("No Path Found");
        }
    }

    // Kiểm tra ô hợp lệ (không chặn, trong biên giới)
    private boolean isValid(int row, int col, int maxRow, int maxCol) {
        if (row < 0 || row >= maxRow || col < 0 || col >= maxCol) return false;
        int tile = gp.tileManager.map[row][col];
        return tile != 1 && tile != 4 && tile != 5 && tile != 8;
    }

    // Heuristic sử dụng khoảng cách Manhattan
    private int heuristic(int row, int col, int targetRow, int targetCol) {
        return Math.abs(row - targetRow) + Math.abs(col - targetCol);
    }

    // Truy vết đường đi từ node đích về start
    private void reconstructPath(Node endNode) {
        LinkedList<String> revPath = new LinkedList<>();
        Node current = endNode;

        while (current.parent != null) {
            int dRow = current.row - current.parent.row;
            int dCol = current.col - current.parent.col;

            if (dRow == -1) revPath.addFirst("up");
            else if (dRow == 1) revPath.addFirst("down");
            else if (dCol == -1) revPath.addFirst("left");
            else if (dCol == 1) revPath.addFirst("right");

            current = current.parent;
        }

        path.addAll(revPath);
    }
}
