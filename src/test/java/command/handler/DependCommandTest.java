package command;


import bean.Component;
import com.google.common.collect.Maps;
import command.handler.DependCommand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.Set;

import static org.hamcrest.core.Is.is;

/**
 * Unit test for {@link DependCommand}
 */
public class DependCommandTest {

    public static final String TELNET = "TELNET";
    public static final String TCPIP = "TCPIP";
    public static final String DNS = "DNS";


    @Test
    public void testOfDependencyGraph() {
        String executionLine = "DEPEND TELNET TCPIP NETCARD";
        final HashMap<Component, Set<Component>> dependencyGraph = Maps.newHashMap();
        DependCommand dependCommand = new DependCommand(executionLine, dependencyGraph);
        DependCommand dependCommandSpy = Mockito.spy(dependCommand);
        dependCommandSpy.call();
        Assert.assertTrue(dependencyGraph.containsKey(new Component(TELNET, Boolean.FALSE)));
        Assert.assertThat(1, is(dependencyGraph.size()));
        Set<Component> componentSet = dependencyGraph.get(new Component(TELNET, Boolean.FALSE));
        Assert.assertTrue(componentSet.contains(new Component(TCPIP, Boolean.FALSE)));

        executionLine = "DEPEND DNS TCPIP NETCARD";
        dependCommand = new DependCommand(executionLine, dependencyGraph);
        dependCommandSpy = Mockito.spy(dependCommand);
        dependCommandSpy.call();
        Assert.assertTrue(dependencyGraph.containsKey(new Component(DNS, Boolean.FALSE)));
        Assert.assertThat(2, is(dependencyGraph.size()));
        componentSet = dependencyGraph.get(new Component(DNS, Boolean.FALSE));
        Assert.assertTrue(componentSet.contains(new Component(TCPIP, Boolean.FALSE)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidArgument(){
        String executionLine = "DEPEND TELNET";
        final HashMap<Component, Set<Component>> dependencyGraph = Maps.newHashMap();
        DependCommand dependCommand = new DependCommand(executionLine, dependencyGraph);
        DependCommand dependCommandSpy = Mockito.spy(dependCommand);
        dependCommandSpy.call();

    }

}