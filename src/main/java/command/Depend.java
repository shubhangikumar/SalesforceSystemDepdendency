package command;

import bean.Component;
import com.google.common.collect.Sets;
import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Depend implements Command {

    private final String executionLine;
    private final HashMap<Component, Set<Component>> dependencyGraph;

    public Depend(String executionLine, HashMap<Component, Set<Component>> dependencyGraph) {
        Objects.requireNonNull(executionLine);
        this.executionLine = executionLine;
        this.dependencyGraph = dependencyGraph;
    }

    @Override
    public String call() throws IllegalArgumentException {
        String[] components = executionLine.split(Util.SPACE_DELIMITER);
        validateDependStatement(components);
        String dependentComponent = components[1];
        final Set<Component> dependencies = Sets.newHashSet();

        Arrays.asList(components).stream().skip(2).forEach(component -> {
            dependencies.add(new Component(component, Boolean.FALSE));
        });

        dependencyGraph.put(new Component(dependentComponent, Boolean.FALSE), dependencies);

        return null;
    }

    private void validateDependStatement(String[] components) throws IllegalArgumentException {
        if (components.length <= 2)
            throw new IllegalArgumentException("Depend statement is not correct, please provide atleast one dependency");
    }


}
