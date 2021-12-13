package by.kosolobov.task4.parser;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.CustomPackage;
import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.entity.van.CustomVan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourcesParser {
    private static final String REGEX_PACKAGES = "p\\d+";
    private static final String REGEX_VANS = "v\\d+,\\d+";
    private static final String REGEX_INTEGER = "\\d+";

    public List<BasePackage> parsePackages(List<String> source) {
        List<BasePackage> packages = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_INTEGER);

        for (String line : source) {
            if (line.matches(REGEX_PACKAGES)) {
                Matcher matcher = pattern.matcher(line);

                int weight = 0;
                if (matcher.find()) {
                    weight = Integer.parseInt(matcher.group());
                }

                packages.add(new CustomPackage(weight));
            }
        }

        return packages;
    }

    public List<BaseVan> parseVans(List<String> source) {
        List<BaseVan> vans = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_INTEGER);

        for (String line : source) {
            if (line.matches(REGEX_VANS)) {
                Matcher matcher = pattern.matcher(line);

                int storage = 0;
                if (matcher.find()) {
                    storage = Integer.parseInt(matcher.group());
                }

                int speed = 0;
                if (matcher.find()) {
                    speed = Integer.parseInt(matcher.group());
                }

                vans.add(new CustomVan(storage, speed));
            }
        }

        return vans;
    }
}
