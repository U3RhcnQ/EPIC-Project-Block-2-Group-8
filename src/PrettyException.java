public class PrettyException extends Exception {
    private final String formattedMessage;

    // Constructor with custom message, expression, and index
    public PrettyException(String message, String expression, int index) {
        super(message); // Set the base exception message
        this.formattedMessage = formatErrorMessage(message, expression, index);
    }

    // Format the error message
    private String formatErrorMessage(String message, String expression, int index) {
        StringBuilder formatted = new StringBuilder();

        formatted.append("\nOh no it looks like we ran into an issue with the expression :( \n");
        formatted.append(message).append("\n\n");
        formatted.append(expression).append("\n");

        // Add spaces before the caret "^"
        for (int i = 0; i < index; i++) {
            formatted.append(" ");
        }
        formatted.append("^\n");

        return formatted.toString();
    }

    @Override
    public String getMessage() {
        return formattedMessage;
    }
}