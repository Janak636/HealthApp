class Workout {
    private String workoutType; // "Madal", "Keskmine", "Kõrge"
    private double durationHours;
    private double burnedCalories;

    // Jälgib treeninguinfot ja arvutab põletatud kalorid
    public Workout(String workoutType, double durationHours) {
        this.workoutType = workoutType;
        this.durationHours = durationHours;
        this.burnedCalories = 0; // Arvutatakse hiljem
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public double getBurnedCalories() {
        return burnedCalories;
    }

    public void calculateBurnedCalories(double weight) {
        double multiplier;
        if (workoutType.equalsIgnoreCase("Madal")) {
            multiplier = 2.5;
        } else if (workoutType.equalsIgnoreCase("Keskmine")) {
            multiplier = 5.0;
        } else if (workoutType.equalsIgnoreCase("Kõrge")) {
            multiplier = 8.0;
        } else {
            multiplier = 2.5; // Default
        }

        burnedCalories = weight * multiplier * durationHours;
    }

    @Override
    public String toString() {
        return String.format("%s intensiivsusega treening (%.1f tundi): %.0f kcal põletatud",
                workoutType, durationHours, burnedCalories);
    }
}

// Jälgib unekestust ja annab nõu une kvaliteedi parandamiseks
class Sleep {
    private double durationHours;

    public Sleep(double durationHours) {
        this.durationHours = durationHours;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double hours) {
        if (hours >= 0 && hours <= 24) {
            this.durationHours = hours;
        } else {
            System.out.println("Sisesta realistlik uneaeg tundides!");
        }
    }

    public String getSleepQualityAdvice() {
        if (durationHours < 6) {
            return "Mine varem magama ja vähenda ekraaniaega.";
        } else if (durationHours < 7) {
            return "Hea algus, aga võta eesmärgiks saada 7–9 tundi und.";
        } else if (durationHours <= 9) {
            return "Tubli! Jätka samas vaimus ja hoia ühtlast unerütmi.";
        } else {
            return "Olid vist väga väsinud.";
        }
    }

    @Override
    public String toString() {
        return String.format("Und: %.1f tundi | %s", durationHours, getSleepQualityAdvice());
    }
}
