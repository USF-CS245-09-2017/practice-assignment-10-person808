import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphImplementation implements Graph {

    private int[][] adjMatrix;

    public GraphImplementation(int vertices) {
        adjMatrix = new int[vertices][vertices];
    }

    @Override
    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = 1;
    }

    @Override
    public List<Integer> topologicalSort() {
        int[] incidents = new int[adjMatrix.length];
        Arrays.fill(incidents, 0);
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                incidents[i] += adjMatrix[j][i];
            }
        }

        List<Integer> sortedList = new ArrayList<>();

        int vertex;
        while ((vertex = getZeroCount(incidents)) >= 0) {
            sortedList.add(vertex);
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[vertex][i] > 0) {
                    incidents[i]--;
                }
            }
        }

        return sortedList;
    }

    private int getZeroCount(int[] incidents) {
        for (int i = 0; i < incidents.length; i++) {
            if (incidents[i] == 0) {
                incidents[i] = -1;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int[] neighbors(int vertex) {
        int numEdges = 0;
        for (int i : adjMatrix[vertex]) {
            if (i > 0) {
                numEdges++;
            }
        }

        int[] neighbors = new int[numEdges];
        for (int i = 0, j = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[vertex][i] > 0) {
                neighbors[j++] = i;
            }
        }
        return neighbors;
    }
}
