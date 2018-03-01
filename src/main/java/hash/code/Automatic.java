package hash.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Automatic {

  private int simulationSteps;
  private int bonus;
  private int[][] grid;
  private List<Ride> rides = new ArrayList<>();
  private Node[] cars;
  private Map<Integer, List<String>> result = new HashMap<>();

  Automatic(String filepath) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\s+");
        if (lineNumber == 0) {
          int x = Integer.parseInt(parts[0]);
          int y = Integer.parseInt(parts[1]);
          int z = Integer.parseInt(parts[2]);
          this.bonus = Integer.parseInt(parts[3]);
          this.simulationSteps = Integer.parseInt(parts[4]);
          this.grid = new int[x][y];
          this.cars = new Node[z];

          for (int i = 0; i < z; i++) {
            this.cars[i] = new Node(0, 0);
          }

          lineNumber++;
          continue;
        }

        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);
        int s = Integer.parseInt(parts[4]);
        int f = Integer.parseInt(parts[5]);

        rides.add(new Ride(String.valueOf(lineNumber -1), new Node(x1, y1), new Node(x2, y2), s, f));

        lineNumber++;
      }
    }
  }

  void process() {
    List<Ride> viable = rides.stream().filter(Ride::viable).sorted(Comparator.comparing(Ride::score)).collect(Collectors.toList());

    int index = 0;
    while(!viable.isEmpty()) {
      if (index < cars.length) {
        if (result.containsKey(index)) {
          result.get(index).add(viable.remove(0).getIndex());
        } else {
          List<String> assigned = new ArrayList<>();
          assigned.add(viable.remove(0).getIndex());
          result.put(index, assigned);
        }
      }
      index++;
      if (index > cars.length) {
        index = 0;
      }
    }

  }

  void print(String filename) throws IOException {
    Path path = Paths.get(filename);
    Files.deleteIfExists(path);
    result.forEach((k,v) -> {
      String assigned = String.join(" ", v);
      try {
        if (Files.exists(path)) {
          Files.write(path, String.format("%d %s\n", v.size(), assigned).getBytes(), StandardOpenOption.APPEND);
        } else {
          Files.write(path, String.format("%d %s\n", v.size(), assigned).getBytes());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
