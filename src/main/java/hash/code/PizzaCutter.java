package hash.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PizzaCutter {

  private int[][] input;
  private int min;
  private int max;



  PizzaCutter(String filePath) throws IOException {
    // may want to move this outside the class and pass the URI...maybe later
    String file = this.getClass().getClassLoader().getResource(filePath).getFile();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      int lineNumber = 0;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\s+");
        if (lineNumber == 0) {
          int x = Integer.parseInt(parts[0]);
          int y = Integer.parseInt(parts[1]);
          input = new int[x][y];
          min = Integer.parseInt(parts[2]);
          max = Integer.parseInt(parts[3]);
          lineNumber++;
          continue;
        }

        for (int i = 0; i < parts.length; i++) {
          input[lineNumber - 1][i] = parts[i].equals("T") ? 1 : 0;
        }

        lineNumber++;
      }
    }
  }

  void process() {

  }

  void print() {

  }

}
