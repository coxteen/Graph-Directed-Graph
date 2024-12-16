import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class File {

    public static void writeInFiles(Graph graph) {
        try {
            writeMatrix(graph);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writeList(graph);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeMatrix(Graph graph) throws IOException {

        String path = Paths.get("matrice-adiacenta.txt").toString();

        try (BufferedWriter matrixWriter = new BufferedWriter(new FileWriter(path))) {

            int[][] matrix = new int[graph.nodes.size()][graph.nodes.size()];

            for (Edge e : graph.edges) {
                matrix[e.startNode.value - 1][e.endNode.value - 1] = 1;
                if (!graph.isOriented) {
                    matrix[e.endNode.value - 1][e.startNode.value - 1] = 1;
                }
            }

            for (int i = 0; i < graph.nodes.size(); ++i) {
                for (int j = 0; j < graph.nodes.size(); ++j) {
                    matrixWriter.write(matrix[i][j] + " ");
                }
                matrixWriter.newLine();
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while writing the matrix to the file.");
        }
    }

    private static void writeList(Graph graph) throws IOException {

        String path = Paths.get("lista-adiacenta.txt").toString();

        try (BufferedWriter listWriter = new BufferedWriter(new FileWriter(path))) {

            HashMap<Integer, ArrayList<Integer>> list = new HashMap<>();
            for (int i = 1; i <= graph.nodes.size(); ++i) {
                list.put(i, new ArrayList<>());
            }
            for (Edge e : graph.edges) {
                list.get(e.startNode.value).add(e.endNode.value);
                if (!graph.isOriented) {
                    list.get(e.endNode.value).add(e.startNode.value);
                }
            }

            for (int i = 1; i <= graph.nodes.size(); ++i) {
                listWriter.write(i + " : " + list.get(i).toString() + "\n");
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while writing the list to the file.");
        }
    }
}
