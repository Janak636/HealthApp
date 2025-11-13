class User {
    private String name;
    private String gender; // "M" või "N"
    private int age;
    private double height; // cm
    private double weight; // kg
    private int activityLevel;
    /* 1 = Istuv eluviis: vähe või üldse pole liikumist, enamasti istuv töö (nt kontoritöö)
       2 = Kergelt aktiivne: istuv töö, kuid trenn 2–3 korda nädalas
       3 Mõõdukalt aktiivne: istuv töö, kuid regulaarne trenn 4 või enam korda nädalas
       4 Aktiivne: füüsiliselt nõudlik töö, kus liigutakse palju või ollakse päeva jooksul jalgadel
       5 Väga aktiivne: füüsiliselt nõudlik töö ja lisaks regulaarne trenn
     */
    private int goal; /*
       1 = Keskendun aktiivsele rasvakaotusele
       2 = Keskendun tervislikule ja tasakaalustatud eluviisile
       3 = Keskendun aktiivsele lihasmassi kasvatamisele ja stabiilsele kaalutõusule
     */
    private int dailySteps;

    // Constructor
    public User(String name, String gender, int age, double height, double weight,
                int activityLevel, int goal, int dailySteps) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.dailySteps = dailySteps;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public int getGoal() {
        return goal;
    }

    public int getDailySteps() {
        return dailySteps;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        if(gender.equals("M") || gender.equals("N")){
            this.gender = gender;
        }
    }

    public void setAge(int age) {
        if(age > 16 && age < 100){
            this.age = age;
        }else{
            System.out.println("Äppi kasutamiseks pead olema vähemalt 16 aastane!");
        }
    }

    public void setHeight(double height) {
        if(height > 120 && height < 300){
            this.height = height;
        }else{
            System.out.println("Sisesta oma pikkus sentimeetrites!");
        }
    }

    public void setWeight(double weight) {
        if(weight > 30 && weight < 300){
            this.weight = weight;
        }else{
            System.out.println("Sisesta oma kaal kilogrammides!");
        }
    }

    public void setActivityLevel(int activityLevel) {
        if(activityLevel > 0 && activityLevel < 6){
            this.activityLevel = activityLevel;
        }else{
            System.out.println("Vali aktiivsuse tase ühest viieni!");
        }
    }

    public void setGoal(int goal) {
        if(goal > 0 && goal < 4){
            this.goal = goal;
        }else{
            System.out.println("Vali oma eesmärk kolmest antud valikust (1-3)!");
        }
    }

    public void setDailySteps(int dailySteps) {
        if(dailySteps > 0 && dailySteps < 100000){
            this.dailySteps = dailySteps;
        }else{
            System.out.println("Sisesta oma realistlik sammude arv päevas!");
        }
    }

    public String getActivityLevelText() {
        return switch (activityLevel) {
            case 1 -> "Istuv eluviis: vähe või üldse pole liikumist";
            case 2 -> "Kergelt aktiivne: istuv töö, kuid trenn 2–3 korda nädalas";
            case 3 -> "Mõõdukalt aktiivne: istuv töö, kuid regulaarne trenn 4+ korda nädalas";
            case 4 -> "Aktiivne: füüsiliselt nõudlik töö";
            case 5 -> "Väga aktiivne: füüsiliselt nõudlik töö ja regulaarne trenn";
            default -> "Teadmata";
        };
    }

    public String getGoalText() {
        return switch (goal) {
            case 1 -> "Aktiivne rasvakaotus";
            case 2 -> "Tervislik ja tasakaalustatud eluviis";
            case 3 -> "Aktiivne lihasmassi kasvatamine ja stabiilne kaalutõus";
            default -> "Teadmata";
        };
    }

    public double calculateBMI() {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "BMI < 18.5: võimalik alakaalulisus ";
        } else if (bmi < 25) {
            return "18.5 < BMI < 25: tervislik kaal";
        } else if (bmi < 30) {
            return "25 < BMI < 30: võimalik ülekaal ";
        }
        else{
            return "BMI > 30: võimalik ebatervislikult kõrge kaal";
        }
    }

    public boolean isGoalSuitableForBMI() {
        double bmi = calculateBMI();
        // Kui BMI < 18.5 ja eesmärk on rasvakaotus (goal == 1), siis ei ole sobiv
        if (bmi < 18.5 && goal == 1) {
            System.out.println("Sinu kehamassiindeks on alla 18.5 – kaalulangetus võib olla ohtlik! Palun vali selle asemel 'Tervislik ja tasakaalustatud eluviis'");
            return false;
        }
        return true;
    }

    private double calculateBMR() { // Baasainevahetuseks kuuluv energia
        if (gender.equalsIgnoreCase("M")) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    public double calculateCalorieNeeds() {
        double bmr = calculateBMR();
        double activityMultiplier;

        // Aktiivsuse parameeter
        activityMultiplier = switch (activityLevel) {
            case 1 -> 1.2;    // Istuv
            case 2 -> 1.375;  // Kergelt aktiivne
            case 3 -> 1.55;   // Mõõdukalt aktiivne
            case 4 -> 1.725;  // Aktiivne
            case 5 -> 1.9;    // Väga aktiivne
            default -> 1.2;   // Vaikimisi istuv
        };

        double calories = bmr * activityMultiplier;
        if (goal == 1) {
            calories -= 500; // Rasvakaotus: defitsiit
        } else if (goal == 3) {
            calories += 500; // Kaalutõus: ülejääk
        }
        return calories;
    }

    public String calculateMacros() {
        double calories = calculateCalorieNeeds();
        double protein, carbs, fats;
        if (goal == 1) {  // Rasvakaotus
            protein = calories * 0.40 / 4;  // 40%
            fats = calories * 0.30 / 9;     // 30%
            carbs = calories * 0.30 / 4;    // 30%
        } else if (goal == 3) {  // Kaalutõus
            protein = calories * 0.25 / 4;  // 25%
            fats = calories * 0.25 / 9;     // 25%
            carbs = calories * 0.50 / 4;    // 50%
        } else {  // Tervislik eluviis
            protein = calories * 0.25 / 4;  // 25%
            fats = calories * 0.30 / 9;     // 30%
            carbs = calories * 0.45 / 4;    // 45%
        }

        return String.format("Valgud: %.0fg, Süsivesikud: %.0fg, Rasvad: %.0fg",
                protein, carbs, fats);
    }

    public String toString() {
        return "KASUTAJA ANDMED\n" +
                "Nimi: " + name + "\n" +
                "Sugu: " + (gender.equalsIgnoreCase("M") ? "Mees" : "Naine") + "\n" +
                "Vanus: " + age + " aastat\n" +
                "Pikkus: " +  String.format("%.1f", height)  + " cm\n" +
                "Kaal: " + String.format("%.1f", weight) + " kg\n" +
                "Aktiivsustase: " + getActivityLevelText() + "\n" +
                "Eesmärk: " + getGoalText() + "\n" +
                "Sammud päevas: " + dailySteps + "\n";
    }
}




