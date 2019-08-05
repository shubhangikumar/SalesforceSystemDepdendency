package command.executor;

import bean.Component;
import command.ValidCommands;
import command.excpetion.IllegalCommandException;
import command.handler.DependCommand;
import command.handler.InstallCommand;
import command.handler.ListCommmand;
import command.handler.RemoveCommand;
import util.Util;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Class which directs the command to the appropriate command handler.
 */

public class CommandExecutor implements Callable<Void> {


    private final HashMap<Component, Set<Component>> dependencyGraph;
    private final HashMap<Component, Integer> installedComponents;

    private final String executionStatement;

    public CommandExecutor(String executionStatement, HashMap<Component, Set<Component>> dependencyGraph, HashMap<Component, Integer> installedComponents) {
        this.executionStatement = executionStatement;
        this.dependencyGraph = dependencyGraph;
        this.installedComponents = installedComponents;

    }


    /**
     * Checks if command is part of valid command list {@link ValidCommands}
     */

    private void validateCommand(String command) throws IllegalCommandException {
        for (ValidCommands commands : ValidCommands.values()) {
            if (commands.name().equalsIgnoreCase(command)) {
                return;
            }
        }
        throw new IllegalCommandException("Command is not valid, please correct the input");
    }

    /**
     * Route the command to the valid command handlers.
     */
    @Override
    public Void call() throws IllegalCommandException {
        String command = executionStatement.split(Util.SPACE_DELIMITER)[0];
        validateCommand(command);
        findCommand(command);
        return null;
    }

    private void findCommand(String command) {
        switch (command.toUpperCase()) {
            case "DEPEND":
                new DependCommand(this.executionStatement, this.dependencyGraph).call();
                break;
            case "INSTALL":
                new InstallCommand(this.dependencyGraph, this.installedComponents, this.executionStatement).call();
                break;
            case "LIST":
                new ListCommmand(this.installedComponents).call();
                break;
            case "REMOVE":
                new RemoveCommand(this.dependencyGraph, this.installedComponents, this.executionStatement).call();
                break;
            case "END":
                System.out.println("END");
                break;
            default:
                System.out.println("Default");
        }
    }
}
