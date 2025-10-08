enum ActivityLevel { LOW, MEDIUM, HIGH }

final class Workout {
    private final ActivityLevel type;
    private final double durationHours;
    private int burnedCalories;

    public Workout(ActivityLevel type, double durationHours, int burnedCalories) {
        if (type == null || durationHours <= 0 || burnedCalories < 0) throw new IllegalArgumentException();
        this.type = type;
        this.durationHours = durationHours;
        this.burnedCalories = burnedCalories;
    }

    // calculateBurnedCalories() – arvutab treeningu energiakulu
    public int calculateBurnedCalories(double weightKg) {
        if (weightKg <= 0) throw new IllegalArgumentException();
        double f = switch (type) { case LOW -> 2.5; case MEDIUM -> 5.0; case HIGH -> 8.0; };
        return burnedCalories = (int) Math.round(weightKg * f * durationHours);
    }
}

final class Sleep {
    private double durationHours;

    // setSleepDuration() – kasutaja sisestab une pikkuse
    public void setSleepDuration(double h) {
        if (h < 0) throw new IllegalArgumentException();
        durationHours = h;
    }

    // sleepQualityAdvice() – annab lihtsa soovituse une parandamiseks
    public String sleepQualityAdvice() {
        if (durationHours < 6)  return "Mine varem magama ja vähenda ekraaniaega.";
        if (durationHours < 7)  return "Hea algus — püüa 7–9 tundi.";
        if (durationHours <= 9) return "Tubli! Hoia ühtlane unerütm.";
        return "Võib-olla liiga palju — sea kindel ärkamisaeg.";
    }
}

// Minimal demo
public class HealthDemo {
    public static void main(String[] args) {
        Workout w = new Workout(ActivityLevel.MEDIUM, 1.5, 0);
        System.out.println(w.calculateBurnedCalories(70));
        Sleep s = new Sleep();
        s.setSleepDuration(6.5);
        System.out.println(s.sleepQualityAdvice());
    }
}
