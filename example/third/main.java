package za.co.todos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static Map<String, String> ff = new HashMap<>();
    private static final String TODO_SUBSTRING = "todo:";

    public static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null){
                int i = line.toLowerCase().indexOf(TODO_SUBSTRING);
                if ( i != -1 ){
                    ff.put(filePath, line.substring(i));
                }

            }

        } catch (IOException e) {
            System.out.println("e = " + e);
        }

    }

    //Todo: hello world


    public static List<String> listFilesInDir(String filePath) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        //Todo: "im good
        assert files != null : "Not a directory";
        assert files.length > 0 : "Directory is empty";

        for (File file1 : files) {
            if (file1.canRead()) {
                readFile(file1.getAbsolutePath());
            }

        }

        return null;
    }

    public static void main(String[] args) {

        listFilesInDir("D:\\DEV\\Todos\\example");
        //Todo: i'm good main
        for (String key : ff.keySet()) {
            System.out.println("%s %s  :  %s %s ".formatted(ANSI_RED, key,ANSI_GREEN ff.get(key)));
        }
        //Todo: Hello there
    }

}
