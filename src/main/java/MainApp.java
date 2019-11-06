import driver.SystemDependenciesDriver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Main class to run java program to evaluate System dependencies
 */
public class MainApp {
    private static final String DEFAULT_FILE_PATH = "test2_input.dat";

    public static void main(String[] args) {
        StringBuilder contentBuilder = new StringBuilder();
        String path = getFilePath(args);
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }
        SystemDependenciesDriver.findSystemDependencies(contentBuilder.toString());
    }

    private static String getFilePath(String[] args) {
        if (args.length == 1) {
            return args[0];
        } else if (args.length > 1) {
            throw new IllegalArgumentException("Please provide correct argument, expected is only the file path");
        }
        return DEFAULT_FILE_PATH;
    }
}
