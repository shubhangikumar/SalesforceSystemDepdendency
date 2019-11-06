package command.handler;

import bean.Component;
import com.google.common.collect.Maps;
import command.handler.ListCommmand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ListCommmandTest {
    private static final Integer DEFAULT_VAUE = 1;

    @Test
    public void testListCommandOfComponents() {

        final HashMap<Component, Integer> installedComponents = Maps.newHashMap();
        installedComponents.put(new Component("TELNET", Boolean.TRUE), DEFAULT_VAUE);
        installedComponents.put(new Component("FOO", Boolean.TRUE), DEFAULT_VAUE);
        installedComponents.put(new Component("NETCARD", Boolean.TRUE), DEFAULT_VAUE);
        installedComponents.put(new Component("DNS", Boolean.TRUE), DEFAULT_VAUE);
        ListCommmand listComponent = new ListCommmand(installedComponents);
        ListCommmand listComponentSpy = Mockito.spy(listComponent);
        String result = listComponentSpy.call();
        Set<String> componentSet = new HashSet<>(Arrays.asList(result.split(Util.SPACE_DELIMITER)));
        Assert.assertTrue(componentSet.contains("TELNET"));
        Assert.assertTrue(componentSet.contains("FOO"));
        Assert.assertTrue(componentSet.contains("DNS"));
        Assert.assertFalse(componentSet.contains("HELLO"));
    }


}