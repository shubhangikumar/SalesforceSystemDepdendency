package bean;

import java.util.Objects;

public class Component {


    private final String componentName;
    private final Boolean isInstalled;


    public Component(String componentName, Boolean isInstalled) {
        this.componentName = componentName;
        this.isInstalled = isInstalled;
    }


    public String getComponentName() {
        return componentName;
    }

    public Boolean isInstalled() {
        return isInstalled;
    }


    @Override
    public String toString() {
        return "Component{" +
                "componentName='" + componentName + '\'' +
                "installed=" + isInstalled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(this.componentName, component.componentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.componentName);
    }
}
