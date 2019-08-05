package command;

import bean.Component;
import util.Util;

import java.util.HashMap;
import java.util.Objects;

public class ListComponent implements Command {

    private final HashMap<Component, Integer> installedComponents;

    public ListComponent(HashMap<Component, Integer> installedComponents) {
        Objects.requireNonNull(installedComponents);
        this.installedComponents = installedComponents;
    }

    @Override
    public String call() {
        StringBuilder listResult = new StringBuilder();
        System.out.println("LIST");
        if (installedComponents.isEmpty())
            System.out.println("None of the components have been installed");
        installedComponents.keySet().stream().forEach(key -> {
            System.out.println("\t" + key.getComponentName());
            listResult.append(key.getComponentName() + Util.SPACE_DELIMITER);
        });
        return listResult.toString();
    }
}
