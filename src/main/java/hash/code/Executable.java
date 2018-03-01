package hash.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executable {

  public static void main(String[] args) {
    List<String> files = new ArrayList<>();
    files.add("/tmp/a_example.in");
    files.add("/tmp/b_should_be_easy.in");
    files.add("/tmp/c_no_hurry.in");
    files.add("/tmp/d_metropolis.in");
    files.add("/tmp/e_high_bonus.in");

    System.out.println("And so it begins...");
    try {
      for (String file : files) {
        System.out.println("Processing " + file);
        Automatic simulation = new Automatic(file);
        simulation.process();
        //simulation.print(file + ".out");
        System.out.println("Completed " + file);
      }
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
    System.out.println("All done!");
  }
}
