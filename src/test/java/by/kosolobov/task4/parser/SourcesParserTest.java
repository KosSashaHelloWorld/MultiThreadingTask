package by.kosolobov.task4.parser;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.CustomPackage;
import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.entity.van.CustomVan;
import by.kosolobov.task4.reader.SourcesReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SourcesParserTest {
    private static final String filePath = "files/sources.txt";
    private static final SourcesReader reader = new SourcesReader();
    private static final SourcesParser parser = new SourcesParser();
    private static final List<String> source = reader.read(filePath);

    @Test
    void parsePackages() {
        BasePackage expected = new CustomPackage(500);
        BasePackage pkg = parser.parsePackages(source).get(0);

        assertEquals(expected, pkg);
    }

    @Test
    void parseVans() {
        BaseVan expected = new CustomVan(500, 120);
        BaseVan van = parser.parseVans(source).get(0);

        assertEquals(expected, van);
    }
}