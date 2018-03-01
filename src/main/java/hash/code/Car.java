package hash.code;

public class Car {

  private Ride ride;
  private Ride journey;
  private int stepCount;

  public Ride getRide() {
    return ride;
  }

  public void setRide(Ride ride) {
    this.ride = ride;
  }

  public Ride getJourney() {
    return journey;
  }

  public void setJourney(Ride journey) {
    this.journey = journey;
  }

  public int getStepCount() {
    return stepCount;
  }

  public void setStepCount(int stepCount) {
    this.stepCount = stepCount;
  }

  Integer destination() {
    return journey.initialLocation() + journey.distance();
  }

  boolean isAvailable() {
    return stepCount >= destination();
  }
}
