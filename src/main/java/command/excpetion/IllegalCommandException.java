package command.excpetion;

/**
 *  Raises whenever the command id not found in set of valid commands {@link command.ValidCommands}
 *  Extends {@link IllegalArgumentException}
 *  @Author : Saumitra.shukla
 */
public class IllegalCommandException extends IllegalArgumentException {

    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }
}
