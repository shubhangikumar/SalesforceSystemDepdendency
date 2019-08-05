package command.excpetion;

public class IllegalCommandException extends IllegalArgumentException {

    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }
}
