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
