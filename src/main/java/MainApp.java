import driver.SystemDependenciesDriver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get("test1_input.dat"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }
        SystemDependenciesDriver.findSystemDependencies(contentBuilder.toString());
    }
}
