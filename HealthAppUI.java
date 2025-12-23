import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class HealthAppUI extends Application {

    private List<Food> söögidFailist;

    @Override
    public void start(Stage stage) {
        // Load foods from file
        //try {
        //    söögidFailist = Peaklass.loeSöögid(".idea/söögid.txt");
        //} catch (Exception e) {
        //    new Alert(Alert.AlertType.ERROR, "Viga söökide laadimisel: " + e.getMessage()).showAndWait();
        //    return;
        //}

        // ============ LEFT SIDE UI ============

        // input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Sisesta nimi");

        ChoiceBox<String> genderBox = new ChoiceBox<>();
        genderBox.getItems().addAll("M", "N");
        genderBox.setValue("N");

        //fixed max and min value for all the input options
        Spinner<Integer> ageSpin = new Spinner<>(16, 101, 25);
        ageSpin.setEditable(true);

        Spinner<Double> heightSpin = new Spinner<>(140.0, 220.0, 170.0, 1.0);
        heightSpin.setEditable(true);

        Spinner<Double> weightSpin = new Spinner<>(35.0, 200.0, 70.0, 0.5);
        weightSpin.setEditable(true);

        Spinner<Integer> activitySpin = new Spinner<>(1, 5, 3);
        activitySpin.setEditable(true);

        Spinner<Integer> goalSpin = new Spinner<>(1, 3, 2);
        goalSpin.setEditable(true);

        Spinner<Integer> stepsSpin = new Spinner<>(0, 40000, 6000, 500);
        stepsSpin.setEditable(true);

        ChoiceBox<String> workoutLevelBox = new ChoiceBox<>();
        workoutLevelBox.getItems().addAll("Madal", "Keskmine", "Kõrge");
        workoutLevelBox.setValue("Keskmine");

        Spinner<Double> workoutDurationSpin = new Spinner<>(0.0, 10.0, 1.0, 0.25);
        workoutDurationSpin.setEditable(true);

        Spinner<Double> sleepSpin = new Spinner<>(0.0, 24.0, 7.5, 0.5);
        sleepSpin.setEditable(true);

        TextArea complaintArea = new TextArea();
        complaintArea.setPromptText("Kirjelda oma kaebust...");
        complaintArea.setPrefRowCount(3);

        // form grid
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        // column constraints to make labels visible
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(150);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        form.getColumnConstraints().addAll(col1, col2);

        int row = 0;

        Label nameLabel = new Label("Nimi:");
        nameLabel.setStyle("-fx-font-weight: bold;");
        form.add(nameLabel, 0, row);
        form.add(nameField, 1, row++);

        Label genderLabel = new Label("Sugu:");
        genderLabel.setStyle("-fx-font-weight: bold;");
        form.add(genderLabel, 0, row);
        form.add(genderBox, 1, row++);

        Label ageLabel = new Label("Vanus:");
        ageLabel.setStyle("-fx-font-weight: bold;");
        form.add(ageLabel, 0, row);
        form.add(ageSpin, 1, row++);

        Label heightLabel = new Label("Pikkus (cm):");
        heightLabel.setStyle("-fx-font-weight: bold;");
        form.add(heightLabel, 0, row);
        form.add(heightSpin, 1, row++);

        Label weightLabel = new Label("Kaal (kg):");
        weightLabel.setStyle("-fx-font-weight: bold;");
        form.add(weightLabel, 0, row);
        form.add(weightSpin, 1, row++);

        Label activityLabel = new Label("Aktiivsus (1-5):");
        activityLabel.setStyle("-fx-font-weight: bold;");
        form.add(activityLabel, 0, row);
        form.add(activitySpin, 1, row++);

        Label goalLabel = new Label("Eesmärk (1-3):");
        goalLabel.setStyle("-fx-font-weight: bold;");
        form.add(goalLabel, 0, row);
        form.add(goalSpin, 1, row++);

        Label stepsLabel = new Label("Sammud päevas:");
        stepsLabel.setStyle("-fx-font-weight: bold;");
        form.add(stepsLabel, 0, row);
        form.add(stepsSpin, 1, row++);

        Label workoutLevelLabel = new Label("Treeningu tase:");
        workoutLevelLabel.setStyle("-fx-font-weight: bold;");
        form.add(workoutLevelLabel, 0, row);
        form.add(workoutLevelBox, 1, row++);

        Label workoutDurationLabel = new Label("Treeningu kestus (h):");
        workoutDurationLabel.setStyle("-fx-font-weight: bold;");
        form.add(workoutDurationLabel, 0, row);
        form.add(workoutDurationSpin, 1, row++);

        Label sleepInputLabel = new Label("Uni (h):");
        sleepInputLabel.setStyle("-fx-font-weight: bold;");
        form.add(sleepInputLabel, 0, row);
        form.add(sleepSpin, 1, row++);

        Label complaintLabel = new Label("Kaebus:");
        complaintLabel.setStyle("-fx-font-weight: bold;");
        form.add(complaintLabel, 0, row);
        form.add(complaintArea, 1, row++);


        Button computeBtn = new Button("Arvuta");
        computeBtn.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        computeBtn.setMaxWidth(Double.MAX_VALUE);

        Label inputTitle = new Label("SISEND");
        inputTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.getChildren().addAll(inputTitle, form, computeBtn);
        leftPanel.setStyle("-fx-background-color: #f5f5f5;");

        // ============ RIGHT SIDE - OUTPUT ============

        Label resultsTitle = new Label("TULEMUSED");
        resultsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label profileLabel = new Label("Profiil: -");
        Label bmiLabel = new Label("BMI: -");
        Label caloriesLabel = new Label("Kalorivajadus: -");
        Label macrosLabel = new Label("Makrod: -");
        Label workoutLabel = new Label("Treening: -");
        Label sleepLabel = new Label("Uni: -");

        Label adviceTitle = new Label("Nõuanne:");
        adviceTitle.setStyle("-fx-font-weight: bold;");

        Label adviceLabel = new Label("-");
        adviceLabel.setWrapText(true);
        adviceLabel.setMaxWidth(400);

        VBox resultsBox = new VBox(8);
        resultsBox.setPadding(new Insets(10));
        resultsBox.getChildren().addAll(
                resultsTitle,
                new Separator(),
                profileLabel,
                bmiLabel,
                caloriesLabel,
                macrosLabel,
                workoutLabel,
                sleepLabel,
                new Separator(),
                adviceTitle,
                adviceLabel
        );

        // chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> mealChart = new BarChart<>(xAxis, yAxis);
        mealChart.setTitle("Kalorid söögikordade kaupa");
        xAxis.setLabel("Söögikord");
        yAxis.setLabel("Kalorid (kcal)");
        mealChart.setPrefHeight(300);

        VBox rightPanel = new VBox(15);
        rightPanel.setPadding(new Insets(10));
        rightPanel.getChildren().addAll(resultsBox, mealChart);

        // ============ BUTTON ACTION ============

        computeBtn.setOnAction(e -> {
            try {
                // Get input values
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Palun sisesta nimi!");
                }

                String gender = genderBox.getValue();
                int age = ageSpin.getValue();
                double height = heightSpin.getValue();
                double weight = weightSpin.getValue();
                int activity = activitySpin.getValue();
                int goal = goalSpin.getValue();
                int steps = stepsSpin.getValue();
                String workoutLevel = workoutLevelBox.getValue();
                double workoutDuration = workoutDurationSpin.getValue();
                double sleepHours = sleepSpin.getValue();
                String complaint = complaintArea.getText();

                // ===== IMPLEMENTING CREATED CLASSES =====

                // Create User
                User mina = new User(name, gender, age, height, weight, activity, goal, steps);

                double bmi = mina.calculateBMI();
                String bmiCategory = mina.getBMICategory();
                double calories = mina.calculateCalorieNeeds();
                String macros = mina.calculateMacros();

                // Create Workout object
                Workout workout = new Workout(workoutLevel, workoutDuration);
                workout.calculateBurnedCalories(weight);

                // Create Sleep object
                Sleep sleep = new Sleep(sleepHours);
                String sleepAdvice = sleep.getSleepQualityAdvice();

                // Get health advice
                HealthAdvice healthAdvice = new HealthAdvice(mina);
                String advice = healthAdvice.giveAdvice(complaint);

                // Calculate daily meals
                //DailySummary summary = Peaklass.arvutaPaev(mina, söögidFailist);

                // ===== UPDATE UI =====

                // After clicking arvuta the app prints out feedback and data about the user
                profileLabel.setText("Profiil: " + mina.toString());
                bmiLabel.setText(String.format("BMI: %.1f - %s", bmi, bmiCategory));
                caloriesLabel.setText(String.format("Päevane kalorivajadus: %.0f kcal", calories));
                macrosLabel.setText("Makrod: " + macros);
                workoutLabel.setText("Treening: " + workout.toString());
                sleepLabel.setText("Uni: " + sleepAdvice);
                adviceLabel.setText(advice);

                // chart updating with actual meal data
                mealChart.getData().clear();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(name);

                Meal breakfast = new Meal("Hommikusöök");
                Meal lunch = new Meal("Lõunasöök");
                Meal dinner = new Meal("Õhtusöök");

                double targetCal = calories;
                breakfast.addFood(new Food("Hommik", targetCal * 0.3, 20, 40, 10), 100);
                lunch.addFood(new Food("Lõuna", targetCal * 0.4, 30, 50, 15), 100);
                dinner.addFood(new Food("Õhtu", targetCal * 0.3, 25, 45, 12), 100);

                series.getData().add(new XYChart.Data<>("Hommikusöök", breakfast.getCalories()));
                series.getData().add(new XYChart.Data<>("Lõuna", lunch.getCalories()));
                series.getData().add(new XYChart.Data<>("Õhtusöök", dinner.getCalories()));

                mealChart.getData().add(series);

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Viga");
                alert.setHeaderText("Arvutamisel tekkis viga");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        // ============ MAIN LAYOUT ============

        ScrollPane leftScroll = new ScrollPane(leftPanel);
        leftScroll.setFitToWidth(true);
        leftScroll.setPrefWidth(350);

        ScrollPane rightScroll = new ScrollPane(rightPanel);
        rightScroll.setFitToWidth(true);

        HBox mainLayout = new HBox();
        mainLayout.getChildren().addAll(leftScroll, new Separator(), rightScroll);
        HBox.setHgrow(rightScroll, Priority.ALWAYS);

        Scene scene = new Scene(mainLayout, 1100, 700);
        stage.setTitle("Terviseäpp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

