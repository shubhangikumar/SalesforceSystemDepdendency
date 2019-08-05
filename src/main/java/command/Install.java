package command;

import bean.Component;
import util.Util;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Install implements Command {

    private final HashMap<Component, Set<Component>> dependencyGraph;
    private final HashMap<Component, Integer> installedComponents;
    private final String executionLine;


    public Install(final HashMap<Component, Set<Component>> dependencyGraph, HashMap<Component, Integer> installedComponents, String executionLine) {
        Objects.requireNonNull(executionLine);
        this.dependencyGraph = dependencyGraph;
        this.installedComponents = installedComponents;
        this.executionLine = executionLine;

    }

    @Override
    public String call() throws IllegalArgumentException {

        String[] components = executionLine.split(Util.SPACE_DELIMITER);
        validateInstallStatement(components);
        Component component = new Component(executionLine.split(Util.SPACE_DELIMITER)[1], Boolean.TRUE);
        System.out.println(this.executionLine);
        if (!installedComponents.containsKey(component))
            installComponent(component);
        else
            System.out.println("\t" + component.getComponentName() + " is already Installed ");
        return null;
    }

    private void installComponent(Component component) {


        if (dependencyGraph.containsKey(component)) {
            Set<Component> dependencyElements = dependencyGraph.get(component);
            dependencyElements.stream().forEach(dependencyElement -> {
                if (!installedComponents.containsKey(dependencyElement))
                    installComponent(dependencyElement);
                installedComponents.put(dependencyElement, installedComponents.getOrDefault(dependencyElement, 0) + 1);

            });
        }
        if (!installedComponents.containsKey(component)) {
            System.out.println("\t Installing " + component.getComponentName());
        }
        installedComponents.put(component, installedComponents.getOrDefault(component, 0) + 1);


    }

    private void validateInstallStatement(String[] components) throws IllegalArgumentException {
        if (components.length != 2)
            throw new IllegalArgumentException("Install statement is not correct, please provide only one install component");
    }
}
