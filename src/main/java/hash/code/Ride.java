package hash.code;

public class Ride {

  private Node start;
  private Node finish;
  private int min;
  private int max;
  private final String index;

  Ride(String index, Node start, Node finish, int min, int max) {
    this.start = start;
    this.finish = finish;
    this.min = min;
    this.max = max;
    this.index = index;
  }

  public Node getStart() {
    return start;
  }

  public void setStart(Node start) {
    this.start = start;
  }

  public Node getFinish() {
    return finish;
  }

  public void setFinish(Node finish) {
    this.finish = finish;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public String getIndex() {
    return index;
  }

  Integer distance() {
    return (finish.x - start.x) + (finish.y - start.y);
  }

  Integer score() {
    return min + distance();
  }

  Integer initialLocation() {
    return start.x + start.y;
  }

  boolean viable() {
    return score() < max;
  }
}
