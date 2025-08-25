package za.co.todos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsHandler {

    public static Map<InputOption, List<String>> handle(String[] args) {

        //Options
        // p <path>         -- list of path to search
        // i <ignore path>  -- list of path to ignore
        // f <ignore file>  -- lit of files to ignore
        // java -jar todos-1.0.jar -p /etc/examples /bin/exec -i /etc/examples/homework -f /bin/exec/secret.txt

        HashMap<InputOption, List<String>> results = new HashMap<>();

        if (args.length == 0) {
            printDefaultPathUsage();
            return results;
        }

        InputOption CURRENT_INPUT = InputOption.DEFAULT;
        boolean processingArgs = false;
        int currentOptionValuesCount = 0;

        //java -jar <jarname> -i /etc/src/.. /etc/src/.. -f

        //Todo: allow users to enter args like: - i /etc/src/..
        //Todo: handle help arg

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-") && args[i].length() == 2) {
                String argOption = args[i].replace("-", "");
                if (InputOption.fromValue(argOption) == InputOption.DEFAULT) {
                    throw new RuntimeException("Unknown argument encountered: " + args[i]);
                } else {

                    if (processingArgs && currentOptionValuesCount == 0) {
                        // preventing adding args without values...
                        // Avoiding users from doing java -jar <jarname> -i  -f
                        throw new RuntimeException("Args: " + args[i] + " should always have values.");
                    }

                    currentOptionValuesCount = 0;
                    processingArgs = true;
                    CURRENT_INPUT = InputOption.fromValue(argOption);
                }
            } else if (args[i].isBlank() || args[i].isEmpty()) {
                //Do nothing on empty space is encountered
                continue;
            } else {
                if (!processingArgs) {
                    throw new RuntimeException("Invalid argument encountered: " + args[i]);
                }
                currentOptionValuesCount++;
                if (results.get(CURRENT_INPUT) != null) {
                    results.get(CURRENT_INPUT).add(args[i]);
                } else {
                    List<String> ag = new ArrayList<>();
                    ag.add(args[i]);
                    results.put(CURRENT_INPUT, ag);
                }
            }

        }
        return results;

    }

    private static void printDefaultPathUsage() {
        System.out.println("No path specified...");
        System.out.println("Using the current directory path to search for todos");
    }

    private static void help() {
        throw new RuntimeException("Not implemented");
    }

}
