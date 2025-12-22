class Workout {
    private String workoutType;
    private double durationHours;
    private double burnedCalories;

    public Workout(String workoutType, double durationHours) throws UniversalUserException {
        if (!workoutType.equalsIgnoreCase("Madal") &&
                !workoutType.equalsIgnoreCase("Keskmine") &&
                !workoutType.equalsIgnoreCase("Kõrge")) {
            throw new UniversalUserException("Treeningu intensiivsus peab olema 'Madal', 'Keskmine' või 'Kõrge'!");
        }
        if (durationHours <= 0 || durationHours > 24) {
            throw new UniversalUserException("Treeningu kestus peab olema vahemikus 0-24 tundi!");
        }
        this.workoutType = workoutType;
        this.durationHours = durationHours;
        this.burnedCalories = 0;
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

class Sleep {
    private double durationHours;

    public Sleep(double durationHours) throws UniversalUserException {
        setDurationHours(durationHours);
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double hours) throws UniversalUserException {
        if (hours < 0 || hours > 24) {
            throw new UniversalUserException("Sisesta realistlik uneaeg tundides (0-24)!");
        }
        this.durationHours = hours;
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
