import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class File {

    public static void write_in_files(ArrayList<Edge> edges, int size) {
        try {
            write_matrix(edges, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            write_list(edges, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write_matrix(ArrayList<Edge> edges, int size) throws IOException {

        String path = "C:\\Stuff\\Informatica\\Facultate\\Anul 2\\Semestrul 1\\AG\\Teme\\Tema 1\\Directed Graph\\src\\matrice-adiacenta.txt";

        try (BufferedWriter matrix_writer = new BufferedWriter(new FileWriter(path))) {

            int[][] matrix = new int[size][size];

            for (Edge e : edges) {
                matrix[e.node1.value - 1][e.node2.value - 1] = 1;
                if (!Graph.is_oriented) {
                    matrix[e.node2.value - 1][e.node1.value - 1] = 1;
                }
            }

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    matrix_writer.write(matrix[i][j] + " ");
                }
                matrix_writer.newLine();
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while writing the matrix to the file.");
            e.printStackTrace();
        }
    }

    private static void write_list(ArrayList<Edge> edges, int size) throws IOException {

        String path = "C:\\Stuff\\Informatica\\Facultate\\Anul 2\\Semestrul 1\\AG\\Teme\\Tema 1\\Directed Graph\\src\\lista-adiacenta.txt";

        try (BufferedWriter list_writer = new BufferedWriter(new FileWriter(path))) {

            HashMap<Integer, ArrayList<Integer>> list = new HashMap<>();
            for (int i = 1; i <= size; ++i) {
                list.put(i, new ArrayList<>());
            }
            for (Edge e : edges) {
                list.get(e.node1.value).add(e.node2.value);
                if (!Graph.is_oriented) {
                    list.get(e.node2.value).add(e.node1.value);
                }
            }

            for (int i = 1; i <= size; ++i) {
                list_writer.write(i + " : " + list.get(i).toString() + "\n");
            }

        }
        catch (Exception e) {
            System.out.println("An error occurred while writing the list to the file.");
            e.printStackTrace();
        }
    }
}
