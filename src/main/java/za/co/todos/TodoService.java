package za.co.todos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TodoService {

    private static final String TODO_MATCHING_CRITERIA = "todo:";

    public static void searchFilePath(String filePath, List<String> ignoreFiles, List<String> ignorePath) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        assert files != null : "Not a directory";
        assert files.length > 0 : "Directory is empty";

        for (File file1 : files) {
            if (file1.isDirectory() && !ignorePath.contains(file1.getAbsolutePath())) {
                searchFilePath(file1.getAbsolutePath(), ignoreFiles, ignorePath);
            } else {
                if (file1.canRead() && !ignoreFiles.contains(file1.getName())) {
                    readFileContents(file1.getAbsolutePath());
                }
            }

        }

    }

    public static void readFileContents(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                int i = line.toLowerCase().indexOf(TODO_MATCHING_CRITERIA);
                if (i != -1) {
                    System.out.printf("Path: <%s> Line: <%d> %s%n", filePath, lineNumber, line.substring(i));
                }
                lineNumber++;
            }

        } catch (IOException e) {
            System.out.println("e = " + e);
        }

    }


    public static void service(Map<InputOption, List<String>> input) {
        if (input.isEmpty()) {
            searchFilePath(".", Collections.emptyList(), Collections.emptyList());
        } else {
            if (input.get(InputOption.SEARCH_PATHS) != null) {
                for (String path : input.get(InputOption.SEARCH_PATHS)) {
                    searchFilePath(path, input.getOrDefault(InputOption.IGNORE_FILES, Collections.emptyList()), input.getOrDefault(InputOption.IGNORE_PATHS, Collections.emptyList()));
                }
            }
        }
    }

}
