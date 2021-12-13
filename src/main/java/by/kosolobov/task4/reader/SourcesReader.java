package by.kosolobov.task4.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SourcesReader {
    private static final Logger log = LogManager.getLogger(SourcesReader.class);

    public List<String> read(String filePath) {
        URL fileUrl = SourcesReader.class
                .getClassLoader()
                .getResource(filePath);

        if (fileUrl == null) {
            log.log(Level.ERROR, "File has not found: {}", filePath);
            return new ArrayList<>();
        }

        File file = new File(fileUrl.getFile());

        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            while (reader.ready()) {
                result.add(reader.readLine());
            }
        } catch (IOException e) {
            log.log(Level.ERROR, "File is not readable: {}", e.getMessage());
        }

        return result;
    }
}
