import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) {
        User user1 = new User("Jana", "N", 22, 173.5, 58.5, 2, 3, 10000);
        System.out.println(user1.calculateMacros());
        Food kreekaJogurt = new Food("Kreeka jogurt", 171, 7.3, 22.0, 5.7);
        Food pastaKrevettidega = new Food("Pasta krevettidega", 219, 8.2, 15.5, 14.0);
        Food kanaRiisiga = new Food("Kana riisiga", 131, 8.8, 20.3, 1.5);
        Meal hommikusöök = new Meal("Hommikusöök");
        Meal lõuna = new Meal("Lõuna");
        Meal õhtusöök = new Meal("Õhtusöök");
        hommikusöök.addFood(kreekaJogurt, 350.2);
        lõuna.addFood(pastaKrevettidega, 420.5);
        õhtusöök.addFood(kanaRiisiga, 472.8);
        System.out.println(hommikusöök.toString());
        System.out.println(lõuna.toString());
        System.out.println(õhtusöök.toString());
        Statistics stats = new Statistics(user1);
        System.out.println(stats.weeklyReport());
        HealthAdvice advice = new HealthAdvice(user1);
        System.out.println(advice.giveAdvice("Olen nii väsinud, ei viitsi midagi teha."));

        Scanner sc = new Scanner(System.in);

        // --- Workout input ---
        System.out.print("Sisesta treeningu tase (LOW, MEDIUM, HIGH): ");
        String levelInput = sc.nextLine().toUpperCase();
        ActivityLevel level = ActivityLevel.valueOf(levelInput);

        System.out.print("Sisesta treeningu kestus tundides: ");
        double duration = sc.nextDouble();

        System.out.print("Sisesta kehakaal kilogrammides: ");
        double weight = sc.nextDouble();

        Workout w = new Workout(level, duration);
        System.out.println("Treeninguga kulutasid " + w.calculateBurnedCalories(weight) + " kalorit.");

        // --- Sleep input ---
        System.out.print("Mitu tundi magasid? ");
        int hours = sc.nextInt();

        Sleep s = new Sleep();
        s.setSleepDuration(hours);
        System.out.println(s.sleepQualityAdvice());

        sc.close();

    }
}


