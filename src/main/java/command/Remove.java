package command;

import bean.Component;
import util.Util;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Remove implements Command {

    private final HashMap<Component, Set<Component>> dependencyGraph;
    private final HashMap<Component, Integer> installedComponents;
    private final String executionLine;


    public Remove(final HashMap<Component, Set<Component>> dependencyGraph, HashMap<Component, Integer> installedComponents, String executionLine) {
        Objects.requireNonNull(executionLine);
        this.dependencyGraph = dependencyGraph;
        this.installedComponents = installedComponents;
        this.executionLine = executionLine;

    }

    @Override
    public String call() throws IllegalArgumentException {

        String[] components = executionLine.split(Util.SPACE_DELIMITER);
        validateRemoveStatement(components);
        Component component = new Component(executionLine.split(Util.SPACE_DELIMITER)[1], Boolean.FALSE);
        System.out.println(this.executionLine);

        if (!installedComponents.containsKey(component))
            System.out.println("\t" +component.getComponentName() + " is not installed.");
        else if (installedComponents.get(component) == 1) {
            System.out.println("\tRemoving " + component.getComponentName());
            installedComponents.remove(component);
            checkForDependencyComponents(component);
        } else {
            System.out.println("\t" + component.getComponentName() + " is still needed");
        }
        return null;
    }

    private Boolean checkForInstalledComponent(Component component) {
        if (!installedComponents.containsKey(component))
            System.out.println("\t" + component + " is not installed");
        return Boolean.FALSE;

    }

    private void checkForDependencyComponents(Component component) {
        Set<Component> components = dependencyGraph.get(component);
        if (components != null) {
            components.stream().forEach(component1 -> {
                if (installedComponents.containsKey(component1)) {
                    installedComponents.put(component1, installedComponents.get(component1) - 1);
                    if (installedComponents.get(component1) == 1) {
                        System.out.println("\tRemoving " + component1.getComponentName());
                        installedComponents.remove(component1);
                    }
                }
            });
        }
    }

    private void validateRemoveStatement(String[] components) throws IllegalArgumentException {
        if (components.length != 2)
            throw new IllegalArgumentException("Remove statement is not correct, please provide only one remove component");
    }
}
