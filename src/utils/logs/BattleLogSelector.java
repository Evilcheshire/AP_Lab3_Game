package utils.logs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BattleLogSelector {

    public static List<String> getAvailableLogs() {
        List<String> logs = new ArrayList<>();
        String fileType = "battleLog\\d+\\.txt";
        Pattern pattern = Pattern.compile(fileType);

        File dir = new File(".");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && pattern.matcher(file.getName()).matches())
                    logs.add(file.getName());
            }
        }
        return logs;
    }
}
