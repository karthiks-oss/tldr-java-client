package com.seenukarthi.tldr;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import static com.seenukarthi.tldr.AnsiColor.*;

@Slf4j
public class Parser {

    private static final String TOKEN_REGEX = "\\{\\{(.*?)}}";
    private static final String INLINE_CODE_REGEX = "`(.*?)`";

    public static String parseIntoString(String fullDoc) {
        boolean toLeaveABlankLine = true;
        StringBuilder builder = new StringBuilder();

        String[] strings = fullDoc.split("\n");
        builder.append(System.lineSeparator());
        for (String string : strings) {
            if (string.startsWith("#")) {
                builder.append(WHITE).append(HIGH_INTENSITY)
                        .append(string.substring(1).trim())
                        .append(RESET)
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            } else if (string.startsWith("`")) {
                builder.append(parseExample("    " + string.substring(1, string.lastIndexOf('`'))))
                        .append(RESET)
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            } else if (string.startsWith(">")) {
                builder.append(YELLOW).append(ITALIC)
                        .append(string.substring(1).trim())
                        .append(RESET)
                        .append(System.lineSeparator());
            } else if (!StringUtils.isEmpty(string.trim())) {
                if (toLeaveABlankLine) {
                    builder.append(System.lineSeparator());
                    toLeaveABlankLine = false;
                }
                builder.append(parseDesc(string));
                builder.append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

    private static String parseExample(String str) {
        return RED + str.replaceAll(TOKEN_REGEX, BLUE + "$1" + RED);
    }

    private static String parseDesc(String str) {
        return str.replaceAll(INLINE_CODE_REGEX, RED + "$1" + RESET);
    }
}
