package by.kosolobov.task4.parser;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.van.Van;
import by.kosolobov.task4.reader.SourcesReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourcesParserTest {
    private static final String filePath = "files/sources.txt";
    private static final SourcesReader reader = new SourcesReader();
    private static final SourcesParser parser = new SourcesParser();
    private static final List<String> source = reader.read(filePath);

    @Test
    void parsePackages() {
        Box expected = new Box(500);
        Box box = parser.parsePackages(source).get(0);

        assertEquals(expected, box);
    }

    @Test
    void parseVans() {
        Van expected = new Van(500, 120);
        Van van = parser.parseVans(source).get(0);

        assertEquals(expected, van);
    }
}