package utils.logs;

import java.io.*;

public class BattleLogger {
    private BufferedWriter writer;
    private BufferedReader reader;
    private String fileName;
    private boolean logEnabled = false;

    public BattleLogger(boolean logEnabled) {
        this.logEnabled = logEnabled;
        if (this.logEnabled) {
            this.fileName = generateFileName();
            try {
                this.writer = new BufferedWriter(new FileWriter(this.fileName, true));
            } catch (IOException e) {
                System.out.println("\t Error opening log file.");
            }
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
        if (logEnabled){
            try {
                writer.write(message);
                writer.write("\n");
                writer.flush();
            } catch (Exception e) {
                System.out.println("\t Error writing to log file." + e.getMessage());
            }
        }
        System.out.print(message);
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
        if (logEnabled) {
            try {
                if (writer != null) this.writer.close();
            } catch (IOException e) {
                System.out.println("\t Error closing log file." + e.getMessage());
            }
        }
    }
}

