import java.time.LocalDate;
import java.util.*;

public class Peaklass {

    public static void main(String[] args) {
        System.out.println("TEST 1: Sobiv kasutaja");

        try {
            User user = new User("Mari", "N", 25, 165, 47, 3, 3, 8000);
            List<String> complaints = new ArrayList<>();
            HealthAdvice advice = new HealthAdvice(user);
            complaints.add("Olen nii väsinud, ei viitsi jätkata.");
            complaints.add("Mul on pidev unetus...");
            complaints.add("Kaal üldse ei lange.");
            complaints.add("Trenni tegemine on nii tüütu.");
            complaints.add("Lihased valutavad.");
            System.out.println("Kasutaja loodud");
            System.out.println("BMI: " + String.format("%.1f", user.calculateBMI()));
            System.out.println("Kalorivajadus: " + String.format("%.0f", user.calculateCalorieNeeds()));
            System.out.println("Nõuanded: ");
            for(String complaint : complaints){
                System.out.println(advice.giveAdvice(complaint));
            }
        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

        System.out.println("TEST 2: Ebasobiv vanus");
        try {
            User user = new User("Laps", "M", 9, 140, 35, 1, 2, 5000);
            System.out.println("Ei visanud viga!");
        } catch (UniversalUserException e) {
            System.out.println("Õigesti viskas vea: " + e.getMessage());
        }

        System.out.println("TEST 3: Ebasobiv kaal");
        try {
            User user = new User("Suur", "M", 30, 180, 1000, 2, 2, 8000);
            System.out.println("Ei visanud viga!");
        } catch (UniversalUserException e) {
            System.out.println("Õigesti viskas vea: " + e.getMessage());
        }

        System.out.println("TEST 4: Toit");
        try {
            Food apple = new Food("Õun", 52, 0.3, 14, 0.2);
            System.out.println("Toit loodud: " + apple);
        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

        System.out.println("TEST 5: Ebasobiv toit");
        try {
            Food badFood = new Food("Vale", 500, 50, 40, 30); // 120g total
            System.out.println("Ei visanud viga!");
        } catch (UniversalUserException e) {
            System.out.println("Õigesti viskas vea: " + e.getMessage());
        }

        System.out.println("TEST 6: Treening");
        try {
            Workout workout = new Workout("Keskmine", 1.5);
            workout.calculateBurnedCalories(70);
            System.out.println("Treening: " + workout);
        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

        System.out.println("TEST 7: Ebasobiv treening");
        try {
            Workout badWorkout = new Workout("Super", 1);
            System.out.println("Ei visanud viga!");
        } catch (UniversalUserException e) {
            System.out.println("Õigesti viskas vea: " + e.getMessage());
        }

        System.out.println("TEST 8: Uni");
        try {
            Sleep sleep = new Sleep(7.5);
            System.out.println("Uni: " + sleep);
        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

        System.out.println("TEST 9: Ebasobiv uni");
        try {
            Sleep badSleep = new Sleep(25);
            System.out.println("Ei visanud viga!");
        } catch (UniversalUserException e) {
            System.out.println("Õigesti viskas vea: " + e.getMessage());
        }

        System.out.println("TEST 10: Täielik päeviku sissekanne");
        try {
            User user = new User("Test", "M", 30, 175, 75, 3, 2, 10000);

            DailyLog log = new DailyLog(LocalDate.now(), user.getName());
            log.setWeight(75);
            log.setSteps(12000);
            log.setSleep(new Sleep(8));

            Meal breakfast = new Meal("Hommikusöök");
            Food eggs = new Food("Munad", 155, 13, 1.1, 11);
            breakfast.addFood(eggs, 100);
            log.addMeal(breakfast);

            Workout workout = new Workout("Madal", 0.5);
            workout.calculateBurnedCalories(75);
            log.addWorkout(workout);

            System.out.println("Päevik loodud");
            System.out.println("Netokalorid: " + String.format("%.0f", log.getNetCalories()));

        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

        System.out.println("TEST 11: Statistika");
        try {
            User user = new User("Statistika", "N", 28, 168, 62, 2, 1, 9000);
            Statistics stats = new Statistics(user);

            for (int i = 2; i >= 0; i--) {
                DailyLog log = new DailyLog(LocalDate.now().minusDays(i), user.getName());
                log.setWeight(62 - (i * 0.1));
                log.setSteps(9000);
                log.setSleep(new Sleep(7));
                stats.saveDailyLog(log);
            }

            System.out.println("Statistika loodud " + stats.getAllLogs().size() + " sissekandega");

        } catch (UniversalUserException e) {
            System.out.println("Viga: " + e.getMessage());
        }

    }
}