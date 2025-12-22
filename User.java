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
                int activityLevel, int goal, int dailySteps) throws UniversalUserException {
        this.name = name;
        setGender(gender);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setActivityLevel(activityLevel);
        setGoal(goal);
        setDailySteps(dailySteps);
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

    public void setGender(String gender) throws UniversalUserException {
        if(!gender.equals("M") && !gender.equals("N")) {
            throw new UniversalUserException("Sugu peab olema 'M' või 'N'!");
        }
        this.gender = gender;
    }

    public void setAge(int age) throws UniversalUserException {
        if(age < 16 || age > 100) {
            throw new UniversalUserException("Äppi kasutamiseks pead olema vähemalt 16 aastane!");
        }
        this.age = age;
    }

    public void setHeight(double height) throws UniversalUserException {
        if(height < 120 || height > 300) {
            throw new UniversalUserException("Sisesta oma pikkus sentimeetrites!");
        }
        this.height = height;
    }

    public void setWeight(double weight) throws UniversalUserException {
        if(weight < 30 || weight > 300) {
            throw new UniversalUserException("Sisesta oma kaal kilogrammides!");
        }
        this.weight = weight;
    }

    public void setActivityLevel(int activityLevel) throws UniversalUserException {
        if(activityLevel < 1 || activityLevel > 5) {
            throw new UniversalUserException("Vali aktiivsuse tase ühest viieni!");
        }
        this.activityLevel = activityLevel;
    }

    public void setGoal(int goal) throws UniversalUserException {
        if(goal < 1 || goal > 3) {
            throw new UniversalUserException("Vali oma eesmärk kolmest antud valikust (1-3)!");
        }
        this.goal = goal;
    }

    public void setDailySteps(int dailySteps) throws UniversalUserException {
        if(dailySteps < 0 || dailySteps > 100000) {
            throw new UniversalUserException("Sisesta oma realistlik sammude arv päevas!");
        }
        this.dailySteps = dailySteps;
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

    // Ohtlik eesmärk
    public boolean isGoalSuitableForBMI() {
        double bmi = calculateBMI();
        if (bmi < 18.5 && goal == 1) {
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

