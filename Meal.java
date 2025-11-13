import java.util.ArrayList;
import java.util.List;

// Esindab toiduainet koos toiteväärtuse teabega 100 g kohta
class Food {
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;

    public Food(String name, double calories, double protein, double carbs, double fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    @Override
    public String toString() {
        return String.format("%s (100g kohta): %.0f kcal, Valgud:%.0fg, Süsivesikud:%.0fg, Rasvad:%.0fg",
                name, calories, protein, carbs, fats);
    }
}

// Esindab toidukorda, mis koosneb mitmest toiduainest
class Meal {
    private String mealName;
    private List<Food> foods;
    private List<Double> grams; // Söögikorra kaal grammides

    public Meal(String mealName) {
        this.mealName = mealName;
        this.foods = new ArrayList<>();
        this.grams = new ArrayList<>();
    }

    public double getProtein() {
        double total = 0;
        for(int i = 0; i < foods.size(); i++) {
            total += foods.get(i).getProtein() * (grams.get(i) / 100.0);
        }
        return total;
    }

    public double getCarbs() {
        double total = 0;
        for(int i = 0; i < foods.size(); i++) {
            total += foods.get(i).getCarbs() * (grams.get(i) / 100.0);
        }
        return total;
    }

    public double getFats() {
        double total = 0;
        for(int i = 0; i < foods.size(); i++) {
            total += foods.get(i).getFats() * (grams.get(i) / 100.0);
        }
        return total;
    }

    public void addFood(Food food, double gramsEaten) {
        foods.add(food);
        grams.add(gramsEaten);
    }

    public double getCalories() {
        double total = 0;
        for(int i = 0; i < foods.size(); i++) {
            total += foods.get(i).getCalories() * (grams.get(i) / 100.0);
        }
        return total;
    }

    public String getMacros() {
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;

        for(int i = 0; i < foods.size(); i++) {
            double multiplier = grams.get(i) / 100.0;
            totalProtein += foods.get(i).getProtein() * multiplier;
            totalCarbs += foods.get(i).getCarbs() * multiplier;
            totalFats += foods.get(i).getFats() * multiplier;
        }

        return String.format("Valgud: %.0fg, Süsivesikud: %.0fg, Rasvad: %.0fg",
                totalProtein, totalCarbs, totalFats);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mealName + ":\n");
        for(int i = 0; i < foods.size(); i++) {
            sb.append(foods.get(i).getName() + " " + String.format("%.0f", grams.get(i)) + "g");
        }
        sb.append(String.format(" Kokku: %.0f kcal | %s", getCalories(), getMacros()));
        return sb.toString();
    }
}
