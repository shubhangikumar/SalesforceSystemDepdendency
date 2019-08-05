package command.executor;

import bean.Component;
import command.*;
import command.excpetion.IllegalCommandException;
import util.Util;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

public class CommandExecutor implements Callable<Void> {


    private final HashMap<Component, Set<Component>> dependencyGraph;
    private final HashMap<Component, Integer> installedComponents;

    private final String executionStatement;

    public CommandExecutor(String executionStatement, HashMap<Component, Set<Component>> dependencyGraph, HashMap<Component, Integer> installedComponents) {
        this.executionStatement = executionStatement;
        this.dependencyGraph = dependencyGraph;
        this.installedComponents = installedComponents;

    }


    private void validateCommand(String command) throws IllegalCommandException {
        for (ValidCommands commands : ValidCommands.values()) {
            if (commands.name().equalsIgnoreCase(command)) {
                return;
            }
        }
        throw new IllegalCommandException("Command is not valid, please correct the input");
    }

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
                new Depend(this.executionStatement, this.dependencyGraph).call();
                break;
            case "INSTALL":
                new Install(this.dependencyGraph, this.installedComponents, this.executionStatement).call();
                break;
            case "LIST":
                new ListComponent(this.installedComponents).call();
                break;
            case "REMOVE":
                new Remove(this.dependencyGraph, this.installedComponents, this.executionStatement).call();
                break;
            case "END":
                System.out.println("END");
                break;
            default:
                System.out.println("Default");
        }
    }
}
