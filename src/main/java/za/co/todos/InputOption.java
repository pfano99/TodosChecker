package za.co.todos;

public enum InputOption {
    SEARCH_PATHS("p"),
    IGNORE_PATHS("i"),
    IGNORE_FILES("f"),
    HELP("h"),
    DEFAULT("");

    public final String value;

    InputOption(String value) {
        this.value = value;
    }

    public static InputOption fromValue(String value) {
        for (InputOption option : InputOption.values()) {
            if (option.value.equals(value)) {
                return option;
            }
        }
        return InputOption.DEFAULT; // or throw an exception
    }

}
