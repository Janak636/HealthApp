import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DailyLog {
    private LocalDate date;
    private String userName;
    private List<Meal> meals;
    private List<Workout> workouts;
    private Sleep sleep;
    private double weight;
    private int steps;
    private String notes;

    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFats;
    private double caloriesBurned;

    public DailyLog(LocalDate date, String userName) {
        this.date = date;
        this.userName = userName;
        this.meals = new ArrayList<>();
        this.workouts = new ArrayList<>();
        this.steps = 0;
        this.notes = "";
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
        updateTotals();
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        caloriesBurned += workout.getBurnedCalories();
    }

    public void setSleep(Sleep sleep) {
        this.sleep = sleep;
    }

    public void setWeight(double weight) throws UniversalUserException {
        if (weight < 30 || weight > 300) {
            throw new UniversalUserException("Päeviku kaal peab olema 30-300 kg!");
        }
        this.weight = weight;
    }

    public void setSteps(int steps) throws UniversalUserException {
        if (steps < 0 || steps > 100000) {
            throw new UniversalUserException("Sammud peavad olema 0-100000!");
        }
        this.steps = steps;
    }

    public void setNotes(String notes) {
        if (notes == null) {
            this.notes = "";
        } else {
            this.notes = notes;
        }
    }

    private void updateTotals() {
        totalCalories = 0;
        totalProtein = 0;
        totalCarbs = 0;
        totalFats = 0;

        for (Meal meal : meals) {
            totalCalories += meal.getCalories();
            totalProtein += meal.getProtein();
            totalCarbs += meal.getCarbs();
            totalFats += meal.getFats();
        }
    }

    public LocalDate getDate() {
        return date;
    }
    public double getTotalCalories() {
        return totalCalories;
    }
    public double getTotalProtein() {
        return totalProtein;
    }
    public double getTotalCarbs() {
        return totalCarbs;
    }
    public double getTotalFats() {
        return totalFats;
    }
    public double getCaloriesBurned() {
        return caloriesBurned;
    }
    public double getWeight() {
        return weight;
    }
    public int getSteps() {
        return steps;
    }
    public Sleep getSleep() {
        return sleep;
    }
    public List<Meal> getMeals() {
        return meals;
    }
    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void writeToDataOutputStream(DataOutputStream dos) throws IOException {
        if (sleep == null) {
            throw new IOException("Lisa uni!");
        }
        dos.writeUTF(date.toString());
        dos.writeUTF(userName);
        dos.writeDouble(weight);
        dos.writeInt(steps);
        dos.writeUTF(notes);
        dos.writeDouble(totalCalories);
        dos.writeDouble(totalProtein);
        dos.writeDouble(totalCarbs);
        dos.writeDouble(totalFats);
        dos.writeDouble(caloriesBurned);
        dos.writeDouble(sleep.getDurationHours());
    }

    public static DailyLog readFromDataInputStream(DataInputStream dis) throws IOException, UniversalUserException {
        LocalDate date = LocalDate.parse(dis.readUTF());
        String userName = dis.readUTF();
        double weight = dis.readDouble();
        int steps = dis.readInt();
        String notes = dis.readUTF();
        double totalCalories = dis.readDouble();
        double totalProtein = dis.readDouble();
        double totalCarbs = dis.readDouble();
        double totalFats = dis.readDouble();
        double caloriesBurned = dis.readDouble();
        double sleepHours = dis.readDouble();
        DailyLog log = new DailyLog(date, userName);

        try {
            log.setWeight(weight);
            log.setSteps(steps);
            log.setSleep(new Sleep(sleepHours));
        } catch (UniversalUserException e) {
            System.err.println("Vigased andmed failis: " + e.getMessage());
        }

        log.setNotes(notes);
        log.totalCalories = totalCalories;
        log.totalProtein = totalProtein;
        log.totalCarbs = totalCarbs;
        log.totalFats = totalFats;
        log.caloriesBurned = caloriesBurned;

        return log;
    }

    public double getNetCalories() {
        return totalCalories - caloriesBurned;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("Päeviku sissekanne: ").append(date.format(formatter)).append("\n");
        sb.append(String.format("Kaal: %.1f kg\n", weight));
        sb.append(String.format("Sammud: %d\n", steps));

        sb.append("Toidukorrad:\n");
        for (Meal meal : meals) {
            sb.append("- ").append(meal.toString()).append("\n");
        }

        sb.append(String.format("Päeva kokkuvõte:\n"));
        sb.append(String.format("- Kalorid: %.0f kcal\n", totalCalories));
        sb.append(String.format("- Valgud: %.0fg | Süsivesikud: %.0fg | Rasvad: %.0fg\n",
                totalProtein, totalCarbs, totalFats));

        return sb.toString();

    }
}