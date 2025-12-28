import java.io.*;
import java.time.*;
import java.util.*;

public class Statistics {
    private User currentUser;
    private List<DailyLog> logs;
    private String filePath;

    public Statistics(User user) {
        this.currentUser = user;
        this.logs = new ArrayList<>();
        this.filePath = user.getName() + "_päevik.dat";

        // Laeb sisse olemasolevad logid
        loadLogs();
    }

    public void saveDailyLog(DailyLog log) {
        logs.add(log);
        saveAllLogsToFile();
    }

    private void saveAllLogsToFile() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {

            dos.writeInt(logs.size());

            for (DailyLog dailyLog : logs) {
                dailyLog.writeToDataOutputStream(dos);
            }

            System.out.println("Päevik salvestatud!");

        } catch (IOException e) {
            System.out.println("Viga salvestamisel: " + e.getMessage());
        }
    }

    public void loadLogs() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Alustan uue päevikuga.");
            return;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {

            int logCount = dis.readInt();

            for (int i = 0; i < logCount; i++) {
                DailyLog log = DailyLog.readFromDataInputStream(dis);
                logs.add(log);
            }

            System.out.println("Laaditud " + logs.size() + " päeviku sissekannet.");

        } catch (Exception e) {
            System.out.println("Viga lugemisel: " + e.getMessage());
        }
    }

    public String getWeeklyStatistics() {
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        List<DailyLog> weekLogs = new ArrayList<>();

        for (DailyLog log : logs) {
            if (!log.getDate().isBefore(weekAgo)) {
                weekLogs.add(log);
            }
        }

        return calculateStatistics(weekLogs, "NÄDALA");
    }

    public String getMonthlyStatistics() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        List<DailyLog> monthLogs = new ArrayList<>();

        for (DailyLog log : logs) {
            if (!log.getDate().isBefore(monthAgo)) {
                monthLogs.add(log);
            }
        }

        return calculateStatistics(monthLogs, "KUU");
    }

    private String calculateStatistics(List<DailyLog> periodLogs, String period) {
        if (periodLogs.isEmpty()) {
            return period + " STATISTIKA:\nPole andmeid.\n";
        }

        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;
        double totalSteps = 0;
        double totalSleep = 0;
        double totalBurned = 0;
        int daysWithSleep = 0;

        for (DailyLog log : periodLogs) {
            totalCalories += log.getTotalCalories();
            totalProtein += log.getTotalProtein();
            totalCarbs += log.getTotalCarbs();
            totalFats += log.getTotalFats();
            totalSteps += log.getSteps();
            totalBurned += log.getCaloriesBurned();
            if (log.getSleep() != null) {
                totalSleep += log.getSleep().getDurationHours();
                daysWithSleep++;
            }
        }

        int days = periodLogs.size();
        double avgCalories = totalCalories / days;
        double avgProtein = totalProtein / days;
        double avgCarbs = totalCarbs / days;
        double avgFats = totalFats / days;
        double avgSteps = totalSteps / days;
        double avgBurned = totalBurned / days;
        double avgSleep = (daysWithSleep > 0) ? totalSleep / daysWithSleep : 0;

        double startWeight = periodLogs.get(0).getWeight();
        double endWeight = periodLogs.get(days - 1).getWeight();
        double weightChange = endWeight - startWeight;

        StringBuilder report = new StringBuilder();
        report.append(period).append("STATISTIKA\n");

        report.append("ÜLEVAADE:\n");
        report.append("Päevikuid: ").append(days).append(" päeva\n");
        report.append(String.format("Kaalumuutus: %+.1f kg\n\n", weightChange));

        report.append("🍽KESKMINE PÄEVAS:\n");
        report.append(String.format("Kalorid: %.0f kcal\n", avgCalories));
        report.append(String.format("Valgud: %.0f g\n", avgProtein));
        report.append(String.format("Süsivesikud: %.0f g\n", avgCarbs));
        report.append(String.format("Rasvad: %.0f g\n\n", avgFats));

        report.append("AKTIIVSUS:\n");
        report.append(String.format("Sammud: %.0f\n", avgSteps));
        report.append(String.format("Põletatud: %.0f kcal\n\n", avgBurned));

        report.append("UNI:\n");
        report.append(String.format("Keskmine: %.1f tundi\n", avgSleep));

        return report.toString();
    }

    public List<DailyLog> getAllLogs() {
        return logs;
    }

    public void clearLogs() {
        logs.clear();
        saveAllLogsToFile();
    }
}