package by.kosolobov.task4.parser;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.van.Van;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourcesParser {
    private static final String REGEX_PACKAGES = "p\\d+";
    private static final String REGEX_VANS = "v\\d+,\\d+";
    private static final String REGEX_INTEGER = "\\d+";

    public List<Box> parsePackages(List<String> source) {
        List<Box> packages = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_INTEGER);

        for (String line : source) {
            if (line.matches(REGEX_PACKAGES)) {
                Matcher matcher = pattern.matcher(line);

                int weight = 0;
                if (matcher.find()) {
                    weight = Integer.parseInt(matcher.group());
                }

                packages.add(new Box(weight));
            }
        }

        return packages;
    }

    public List<Van> parseVans(List<String> source) {
        List<Van> vans = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_INTEGER);

        for (String line : source) {
            if (line.matches(REGEX_VANS)) {
                Matcher matcher = pattern.matcher(line);

                int storageLimit = 0;
                if (matcher.find()) {
                    storageLimit = Integer.parseInt(matcher.group());
                }

                int speed = 0;
                if (matcher.find()) {
                    speed = Integer.parseInt(matcher.group());
                }

                vans.add(new Van(storageLimit, speed));
            }
        }

        return vans;
    }
}
