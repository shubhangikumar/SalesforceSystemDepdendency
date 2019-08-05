package driver;

import bean.Component;
import command.executor.CommandExecutor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class SystemDependenciesDriver {

    private static final HashMap<Component, Set<Component>> dependencyGraph = new HashMap<>();
    private static final HashMap<Component, Integer> installedComponents = new LinkedHashMap<>();

    public static void main(String[] args) {
        String input = "DEPEND TELNET TCPIP NETCARD\n" +
                "DEPEND TCPIP NETCARD\n" +
                "DEPEND DNS TCPIP NETCARD\n" +
                "DEPEND BROWSER TCPIP HTML\n" +
                "INSTALL NETCARD\n" +
                "INSTALL TELNET\n" +
                "INSTALL foo\n" +
                "REMOVE NETCARD\n" +
                "INSTALL BROWSER\n" +
                "INSTALL DNS\n" +
                "LIST\n" +
                "REMOVE TELNET\n" +
                "REMOVE NETCARD\n" +
                "REMOVE DNS\n" +
                "REMOVE NETCARD\n" +
                "INSTALL NETCARD\n" +
                "REMOVE TCPIP\n" +
                "REMOVE BROWSER\n" +
                "REMOVE TCPIP\n" +
                "LIST\n" +
                "END";
        findSystemDependencies(input);
    }

    public static void findSystemDependencies(String input) {
        Arrays.stream(input.split("\\r?\\n")).forEach(line -> {
            takeAction(line);
        });
    }

    private static void takeAction(String line) {
        new CommandExecutor(line, dependencyGraph, installedComponents).call();

    }

}
