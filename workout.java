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
}

public class Sleep {
    private double durationHours;

    // setSleepDuration() – kasutaja sisestab une pikkuse
    public void setSleepDuration(double h) {
        if (h < 0) throw new IllegalArgumentException();
        durationHours = h;
    }

    // sleepQualityAdvice() – annab lihtsa soovituse une parandamiseks
    public String sleepQualityAdvice() {
        if (durationHours < 6)  return "Mine varem magama ja vähenda ekraaniaega.";
        if (durationHours < 7)  return "Hea algus, aga võta eesmärgiks saada 7–9 tundi und.";
        if (durationHours <= 9) return "Tubli! Jätka samas vaimus ja hoia ühtlast unerütmi.";
        return "Olid vist väga väsinud. ";
    }
}



