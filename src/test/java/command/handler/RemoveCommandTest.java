package command;

import bean.Component;
import com.google.common.collect.Maps;
import command.handler.DependCommand;
import command.handler.InstallCommand;
import command.handler.RemoveCommand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Set;

/**
 * Unit test for {@link RemoveCommand}
 */
public class RemoveCommandTest {

    @Test
    public void testOfRemoveComponents() {

        String executionLine = "DEPEND TELNET TCPIP NETCARD";
        final HashMap<Component, Set<Component>> dependencyGraph = Maps.newHashMap();
        final HashMap<Component, Integer> installedComponents = Maps.newHashMap();

        DependCommand dependCommand = new DependCommand(executionLine, dependencyGraph);
        DependCommand dependCommandSpy = Mockito.spy(dependCommand);
        dependCommandSpy.call();


        executionLine = "DEPEND DNS TCPIP NETCARD";
        dependCommand = new DependCommand(executionLine, dependencyGraph);
        dependCommandSpy = Mockito.spy(dependCommand);
        dependCommandSpy.call();

        executionLine = "INSTALL DNS";

        InstallCommand installCommand = new InstallCommand(dependencyGraph, installedComponents, executionLine);
        InstallCommand installCommandSpy = Mockito.spy(installCommand);
        installCommandSpy.call();

        executionLine = "INSTALL TELNET";

        installCommand = new InstallCommand(dependencyGraph, installedComponents, executionLine);
        installCommandSpy = Mockito.spy(installCommand);
        installCommandSpy.call();

        executionLine = "REMOVE NETCARD";

        RemoveCommand removeCommand = new RemoveCommand(dependencyGraph, installedComponents, executionLine);
        RemoveCommand removeCommandSpy = Mockito.spy(removeCommand);
        removeCommandSpy.call();
        Assert.assertEquals(4, installedComponents.size());

        executionLine = "REMOVE DNS";

        removeCommand = new RemoveCommand(dependencyGraph, installedComponents, executionLine);
        removeCommandSpy = Mockito.spy(removeCommand);
        removeCommandSpy.call();
        Assert.assertEquals(3, installedComponents.size());

        executionLine = "REMOVE TELNET";

        removeCommand = new RemoveCommand(dependencyGraph, installedComponents, executionLine);
        removeCommandSpy = Mockito.spy(removeCommand);
        removeCommandSpy.call();
        Assert.assertEquals(0, installedComponents.size());


    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidArgument(){
        String executionLine = "REMOVE";
        final HashMap<Component, Set<Component>> dependencyGraph = Maps.newHashMap();
        final HashMap<Component, Integer> installedComponents = Maps.newHashMap();
        RemoveCommand removeCommand = new RemoveCommand(dependencyGraph, installedComponents, executionLine);
        RemoveCommand removeCommandSpy = Mockito.spy(removeCommand);
        removeCommandSpy.call();

    }

}