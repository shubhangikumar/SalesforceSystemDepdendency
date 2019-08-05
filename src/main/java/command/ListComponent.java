package command;

import bean.Component;

import java.util.HashMap;
import java.util.Objects;

public class ListComponent implements Command {

    private final HashMap<Component, Integer> intalledComponents;

    public ListComponent(HashMap<Component, Integer> intalledComponents) {
        Objects.requireNonNull(intalledComponents);
        this.intalledComponents = intalledComponents;
    }

    @Override
    public Void call() {
        System.out.println("LIST");
        if (intalledComponents.isEmpty())
            System.out.println("None of the components have been installed");
        intalledComponents.keySet().stream().forEach(key -> System.out.println("\t" + key.getComponentName()));
        return null;
    }
}
