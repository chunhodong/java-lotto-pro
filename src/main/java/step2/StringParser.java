package step2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringParser {


    private static final String DEFAULT_DELIMITER = ",|:";
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\\n(.*)");

    public static int[] parseToIntArray(String text){
        String[] tokens = parseToStringArray(text);
        return Stream.of(tokens).mapToInt(Integer::parseInt).toArray();
    }

    public static List<Integer> parseToIntegerArray(String text){
        String[] tokens = parseToStringArray(text);
        return Stream.of(tokens).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

    private static String[] parseToStringArray(String text){
        Matcher matcher = CUSTOM_PATTERN.matcher(text);
        if(matcher.find()) {
            return matcher.group(2).split(matcher.group(1));
        }
        return text.split(DEFAULT_DELIMITER);
    }
}
