package hash.code;

public class Ride {

  private Node start;
  private Node finish;
  private int min;
  private int max;
  private final int index;

  Ride(int index, Node start, Node finish, int min, int max) {
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

  public int getIndex() {
    return index;
  }

  Integer score() {
    return min + (finish.x - start.x) + (finish.y - start.y);
  }

  boolean viable() {
    return score() < max;
  }
}
