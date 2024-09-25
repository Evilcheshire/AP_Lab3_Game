package utils.logs;

import java.io.*;

public class BattleLogger {
    private BufferedWriter writer;
    private BufferedReader reader;
    private String fileName;

    public BattleLogger() {
        this.fileName = generateFileName();
        try {
            this.writer = new BufferedWriter(new FileWriter(this.fileName, true));
        } catch (IOException e) {
            System.out.println("\t Error opening log file.");
        }
    }

    public BattleLogger(String fileName) {
        this.fileName = fileName;
        try {
            this.reader = new BufferedReader(new FileReader(this.fileName));
        } catch (IOException e) {
            System.out.println("\t Error opening log file.");
        }
    }

    public String generateFileName() {
        String logName;
        int i = 1;

        do {
            logName = "battleLog" + i + ".txt";
             i++;
        } while (new File(logName).exists());

        return logName;
    }

    public void log(String message) {
        try {
            writer.write(message);
            writer.write("\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println("\t Error writing to log file." + e.getMessage());
        }
    }

    public void readLog() {
        try {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("\t Error opening log file." + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("\t Error closing log file.");
            }
        }
    }

    public void close() {
        try {
            if (writer != null) this.writer.close();
        } catch (IOException e) {
            System.out.println("\t Error closing log file." + e.getMessage());
        }
    }

}

