package command.handler;


import bean.Component;
import com.google.common.collect.Maps;
import command.handler.DependCommand;
import command.handler.InstallCommand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Set;

import static org.hamcrest.core.Is.is;

/**
 * Unit test for {@link InstallCommand}
 */
public class InstallCommandTest {

    public static final String TELNET = "TELNET";
    public static final String TCPIP = "TCPIP";
    public static final String DNS = "DNS";

    @Test
    public void testOfInstallCommand(){



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

        Assert.assertTrue(installedComponents.containsKey(new Component(DNS, Boolean.TRUE)));
        Assert.assertThat(3, is(installedComponents.size()));

        executionLine = "INSTALL TELNET";

        installCommand = new InstallCommand(dependencyGraph, installedComponents, executionLine);
        installCommandSpy = Mockito.spy(installCommand);
        installCommandSpy.call();

        Assert.assertTrue(installedComponents.containsKey(new Component(DNS, Boolean.TRUE)));
        Assert.assertThat(4, is(installedComponents.size()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidArgument(){
        String executionLine = "INSTALL TELNET DNS";
        final HashMap<Component, Set<Component>> dependencyGraph = Maps.newHashMap();
        final HashMap<Component, Integer> installedComponents = Maps.newHashMap();
        InstallCommand installCommand = new InstallCommand(dependencyGraph, installedComponents, executionLine);
        InstallCommand installCommandSpy = Mockito.spy(installCommand);
        installCommandSpy.call();

    }

}