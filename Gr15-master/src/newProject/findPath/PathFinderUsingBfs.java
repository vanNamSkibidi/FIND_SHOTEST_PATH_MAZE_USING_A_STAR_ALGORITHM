package newProject.findPath;

import newProject.test2.GamePanel;

import java.util.*;

public class PathFinderUsingBfs {
    GamePanel gp;

    public static class Node {
        public int x, y;
        public Node parent;

        public Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public ArrayList<String> path;

    public PathFinderUsingBfs(GamePanel gp) {
        this.gp = gp;
    }

    private String key(int x, int y) {
        return x + "," + y;
    }

    public void findPathUsingBFS(int startRow, int startCol, int endRow, int endCol) {
        int rows = gp.tileManager.map.length;
        int cols = gp.tileManager.map[0].length;

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Node startNode = new Node(startRow, startCol, null);
        queue.offer(startNode);
        visited.add(key(startRow, startCol));

        Node endNode = null;

        int[][] directions = {
                {-1, 0}, // up
                {0, -1}, // left
                {1, 0},  // down
                {0, 1}   // right
        };

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == endRow && current.y == endCol) {
                endNode = current;
                break;
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(newX, newY, rows, cols) && !visited.contains(key(newX, newY))) {
                    Node neighbor = new Node(newX, newY, current);
                    queue.offer(neighbor);
                    visited.add(key(newX, newY));
                }
            }
        }

        if (endNode != null) {
            reconstructPath(endNode);
        } else {
            path = new ArrayList<>();
            path.add("No Path Found");
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols &&
                gp.tileManager.map[x][y] != 1 &&
                gp.tileManager.map[x][y] != 4 &&
                gp.tileManager.map[x][y] != 5 &&
                gp.tileManager.map[x][y] != 8;
    }

    private void reconstructPath(Node endNode) {
        path = new ArrayList<>();
        Node current = endNode;

        while (current != null && current.parent != null) {
            int dx = current.x - current.parent.x;
            int dy = current.y - current.parent.y;

            if (dx == -1) path.add("up");
            else if (dx == 1) path.add("down");
            else if (dy == -1) path.add("left");
            else if (dy == 1) path.add("right");

            current = current.parent;
        }

        if (!path.isEmpty()) {
            Collections.reverse(path);
        }
    }
}
