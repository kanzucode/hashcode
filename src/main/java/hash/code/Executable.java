package hash.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executable {

  public static void main(String[] args) {
    List<String> files = new ArrayList<>();
    files.add("example.in");

    System.out.println("And so it begins...");
    try {
      for (String file : files) {
        System.out.println("Processing " + file);
        PizzaCutter cutter = new PizzaCutter(file);
        cutter.process();
        cutter.print();
        System.out.println("Completed " + file);
      }
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
    System.out.println("All done!");
  }
}
