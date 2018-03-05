package hash.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executable {

  public static void main(String[] args) {
    List<String> files = new ArrayList<>();
    files.add("a_example.in");
    files.add("b_should_be_easy.in");
    files.add("c_no_hurry.in");
    files.add("d_metropolis.in");
    files.add("e_high_bonus.in");

    System.out.println("And so it begins...");
    try {
      for (String file : files) {
        System.out.println("Processing " + file);
        Automatic simulation = new Automatic(file);
        simulation.process();
        simulation.print(file + ".out");
        System.out.println("Completed " + file);
      }
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
    System.out.println("All done!");
  }
}
