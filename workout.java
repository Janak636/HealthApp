enum ActivityLevel { LOW, MEDIUM, HIGH }

public class Workout {
    private ActivityLevel type;
    private double durationHours;
    private int workoutburnedCalories;

    public Workout(ActivityLevel type, double durationHours) {
        if (type == null || durationHours <= 0) throw new IllegalArgumentException();
        this.type = type;
        this.durationHours = durationHours;
    }

    // calculateBurnedCalories() – arvutab treeningu energiakulu
    public int calculateBurnedCalories(double weight) {
        if (weight <= 0) throw new IllegalArgumentException();
        double f = switch (type) { case LOW -> 2.5; case MEDIUM -> 5.0; case HIGH -> 8.0; };
        workoutburnedCalories = (int) Math.round(weight * f * durationHours);
        return workoutburnedCalories;
    }
@Override
    public String toString() {
        return "Treeningu{" +
                "tüüp=" + type +
                ", kestus" + durationHours +
                ", kulutatud kalorid " + workoutburnedCalories +
                '}';
    
}








