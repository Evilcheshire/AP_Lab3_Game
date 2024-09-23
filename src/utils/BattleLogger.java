package utils;

import java.io.*;

public class BattleLogger {
    private Writer writer;
    private String fileName;

    public BattleLogger(String fileName) {
        this.fileName = generateFileName();
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("\t Error opening log file.");
        }
    }

    public String generateFileName() {
        String logName;
        int i = 1;

        do {
            logName = i + "_" + fileName + ".txt";
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

    public void readLog(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("\t Error opening log file." + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            System.out.println("\t Error closing log file." + e.getMessage());
        }
    }

    public String getFileName() { return fileName; }

}

