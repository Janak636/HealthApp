import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Töötleb kasutaja sisendit ja näitab tervise jälgimise süsteemi
public class Peaklass {
    public static List<Food> loeSöögid(String failinimi) throws Exception {
        List<Food> söögid = new ArrayList<>();
        File fail = new File(failinimi);

        try (Scanner sc = new Scanner(fail, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tykid = line.split(",");
                String name = tykid[0];
                double calories = Double.parseDouble(tykid[1]);
                double proteins = Double.parseDouble(tykid[2]);
                double carbs = Double.parseDouble(tykid[3]);
                double fats = Double.parseDouble(tykid[4]);
                söögid.add(new Food(name, calories, proteins, carbs, fats));
            }
        }
        return söögid;
    }

    public static void main(String[] args) throws Exception {
        List<Food> söögidFailist = loeSöögid("söögid.txt");
        String[] femaleNames = {"Anni", "Liisi", "Katre", "Kreete", "Eliise", "Mari"};
        String[] maleNames = {"Martin", "Toomas", "Jaan", "Tõnis", "Sander", "Markus", "Ekke"};
        String[] genders = {"M", "N"};
        String[] complaints = {"Olen väga väsinud. Energiat trenni tegemiseks üldse pole.", "Mu kaal ei lange isegi kui söön tervislikult.", "Olen ülikooli pärast kogu aeg stressis, mistõttu söön tihti üle."};

        List<User> users = new ArrayList<>();

        // Genereerime suvelisi isikuid
        for (int i = 0; i < 3; i++) {
            String gender = genders[(int)(Math.random() * 2)];
            String name;
            if(gender.equals("M")){
                name = maleNames[(int)(Math.random() * maleNames.length)];
            }else{
                name = femaleNames[(int)(Math.random() * femaleNames.length)];
            }
            int age = (int)(Math.random() * 50) + 18;
            double height = Math.random() * 40 + 150;
            double weight = Math.random() * 50 + 50;
            int activityLevel = (int)(Math.random() * 5) + 1;
            int goal = (int)(Math.random() * 3) + 1;
            int dailySteps = (int)(Math.random() * 12000) + 2000;

            users.add(new User(name, gender, age, height, weight, activityLevel, goal, dailySteps));
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("   TERE TULEMAST TERVISEÄPPI   \n");
        // Kasutaja andmete sisestamine
        System.out.print("Sisesta oma nimi: ");
        String name = sc.nextLine();
        System.out.print("Sisesta oma sugu (M või N): ");
        String gender = sc.nextLine().toUpperCase();
        System.out.print("Sisesta oma vanus: ");
        int age = sc.nextInt();
        System.out.print("Sisesta oma pikkus (cm): ");
        double height = sc.nextDouble();
        System.out.print("Sisesta oma kaal (kg): ");
        double weight = sc.nextDouble();
        System.out.println("Vali oma aktiivsuse tase (1-5):");
        System.out.println("1 - Istuv eluviis");
        System.out.println("2 - Kergelt aktiivne (trenn 2-3x nädalas)");
        System.out.println("3 - Mõõdukalt aktiivne (trenn 4+ korda nädalas)");
        System.out.println("4 - Aktiivne (füüsiliselt nõudlik töö)");
        System.out.println("5 - Väga aktiivne (füüsiliselt nõudlik töö + trenn)");
        int activityLevel = sc.nextInt();
        System.out.println("Vali oma eesmärk (1-3):");
        System.out.println("1 - Aktiivne rasvakaotus");
        System.out.println("2 - Tervislik ja tasakaalustatud eluviis");
        System.out.println("3 - Lihasmassi kasvatamine ja kaalutõus");
        int goal = sc.nextInt();
        System.out.print("Sisesta keskmine sammude arv päevas: ");
        int dailySteps = sc.nextInt();
        User mina = new User(name, gender, age, height, weight, activityLevel, goal, dailySteps);
        // Kuva kasutaja info
        System.out.println("       SINU PROFIIL");
        System.out.println(mina);
        System.out.println("BMI: " + String.format("%.1f", mina.calculateBMI()) + " - " + mina.getBMICategory());
        System.out.println("\nSOOVITUSED:");
        System.out.println("Päevane kalorivajadus: " + String.format("%.0f", mina.calculateCalorieNeeds()) + " kcal");
        System.out.println("Makrotoitained: " + mina.calculateMacros());
        sc.nextLine();

        // Treeningute sisestamine
        System.out.print("Sisesta tänase treeningu aktiivsuse tase (Madal, Keskmine, Kõrge): ");
        String levelInput = sc.nextLine();

        System.out.print("Sisesta treeningu kestus tundides: ");
        double duration = sc.nextDouble();
        sc.nextLine();

        Workout w = new Workout(levelInput, duration);
        w.calculateBurnedCalories(mina.getWeight());
        System.out.println(w.toString());

        System.out.print("Mitu tundi magasid? ");
        double hours = sc.nextDouble();
        sc.nextLine();

        Sleep s = new Sleep(hours);
        System.out.println(s.getSleepQualityAdvice());

        System.out.print("Kirjelda oma kaebust (kaebus peab olema seotud toitumise, trennide, une, stressi või valuga): ");
        String complaint = sc.nextLine();
        HealthAdvice advice = new HealthAdvice(mina);
        System.out.println(advice.giveAdvice(complaint));

        sc.close();

        // Testime loodud isikuid
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("\n       KASUTAJA #" + (i+1));
            // Prindi kasutaja info
            System.out.println(user);
            System.out.println("BMI: " + String.format("%.1f", user.calculateBMI()) + " - " + user.getBMICategory());
            System.out.println("Päevane kalorivajadus: " + String.format("%.0f", user.calculateCalorieNeeds()) + " kcal");
            System.out.println("Makrotoitained: " + user.calculateMacros());
            System.out.println("\n--- PÄEVA TOIDUD ---");

            // Loome suvalised söögikorrad
            Meal hommik = new Meal("Hommikusöök");
            Food randomFood1 = söögidFailist.get((int)(Math.random() * söögidFailist.size()));
            double grams1 = 100 + Math.random() * 200;
            hommik.addFood(randomFood1, grams1);
            System.out.println(hommik);

            Meal lõuna = new Meal("Lõuna");
            Food randomFood2 = söögidFailist.get((int)(Math.random() * söögidFailist.size()));
            double grams2 = 200 + Math.random() * 300;
            lõuna.addFood(randomFood2, grams2);
            System.out.println(lõuna);

            Meal õhtu = new Meal("Õhtusöök");
            Food randomFood3 = söögidFailist.get((int)(Math.random() * söögidFailist.size()));
            double grams3 = 150 + Math.random() * 250;
            õhtu.addFood(randomFood3, grams3);
            System.out.println(õhtu);

            // Kokku päevas
            double totalCals = hommik.getCalories() + lõuna.getCalories() + õhtu.getCalories();
            double targetCals = user.calculateCalorieNeeds();
            System.out.println(String.format("\nKOKKU: %.0f kcal (eesmärk: %.0f kcal, jääb: %.0f kcal)",
                    totalCals, targetCals, targetCals - totalCals));

            // Treeningu info
            String[] treeninguTüübid = {"Madal", "Keskmine", "Kõrge"};
            System.out.println("\n--- TREENING ---");
            String workoutType = treeninguTüübid[(int)(Math.random() * 3)];
            double durationHours = 0.5 + Math.random() * 1.5;
            Workout workout = new Workout(workoutType, durationHours);
            workout.calculateBurnedCalories(user.getWeight());
            System.out.println(workout);

            // Une info
            System.out.println("\n--- UNI ---");
            double sleepHours = 5 + Math.random() * 4;
            Sleep sleep = new Sleep(sleepHours);
            System.out.println(sleep);

            // Tervisenõuanded
            System.out.println("\n--- TERVISENÕUANDED ---");
            HealthAdvice adviceRandom = new HealthAdvice(user);
            String randomComplaint = complaints[(int)(Math.random() * complaints.length)];
            System.out.println("Kaebus: " + randomComplaint);
            System.out.println(adviceRandom.giveAdvice(randomComplaint));
        }
    }
}
