package za.co.todos;

import java.util.List;
import java.util.Map;

public class Main {

    private static final String TODO_SUBSTRING = "todo:";
    private static final String TODO_SUBSTRING_1 = "todo: ";
    private static final String TODO_SUBSTRING_2 = "todo : ";

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public static void main(String[] args) {

        Map<InputOption, List<String>> processedArgs = ArgsHandler.handle(args);
        TodoService.service(processedArgs);

    }

}
