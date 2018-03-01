package hash.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.lang3.tuple.Pair;

public class PizzaCutter {

  private char[][] input;
  private int[][] visited;
  private int x;
  private int y;
  private int min;
  private int max;

  private List<Pair<Node, Node>> slices = new ArrayList<>();

  PizzaCutter(String filePath) throws IOException {
    // may want to move this outside the class and pass the URI...maybe later
    String file = this.getClass().getClassLoader().getResource(filePath).getFile();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        if (lineNumber == 0) {
          String[] parts = line.split("\\s+");
          x = Integer.parseInt(parts[0]);
          y = Integer.parseInt(parts[1]);
          input = new char[x][y];
          visited = new int[x][y];
          min = Integer.parseInt(parts[2]);
          max = Integer.parseInt(parts[3]);
          lineNumber++;
          continue;
        }

        for (int i = 0; i < y; i++) {
          input[lineNumber - 1][i] = line.charAt(i);
        }

        lineNumber++;
      }
    }
  }

  void process() {
    int optimum = 1;
    for (int k = 1; k < max -1; k++) {
      List<Pair<Node, Node>> slices = new ArrayList<>();
      for (int l = 1; l < max -1; l++) {
        slices.add(slice(l));
      }
      int score = slices.stream().mapToInt(this::score).sum();
      if (score > optimum) {
        this.slices = slices;
      }
    }
  }

  private Pair<Node, Node> slice(int limit) {
    Queue<Node> stack = new LinkedList<>();
    stack.add(new Node(0,0));
    while (!stack.isEmpty()) {
      Node n = stack.remove();
      if (visited[n.x][n.y] == 0) {
        visited[n.x][n.y] = 1;
      }
    }
    return Pair.of(new Node(0, 0), new Node(1,1));
  }

  private int score(Pair<Node, Node> slice) {
    int length = Math.abs(slice.getLeft().x - slice.getRight().x);
    int width = Math.abs(slice.getLeft().y - slice.getRight().y);
    return length * width;
  }

  void print(String filename) throws IOException {
    Path path = Paths.get("/tmp/" + filename);
    Files.write(path, String.format("%d\n", slices.size()).getBytes());
    for (Pair<Node, Node> bounds : slices) {
      Node left = bounds.getLeft();
      Node right = bounds.getRight();
      Files.write(path, String.format("%d %d %d %d\n", left.x, left.y, right.x, right.y).getBytes(), StandardOpenOption.APPEND);
    }
  }

}
