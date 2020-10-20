package com.seenukarthi.tldr;

import com.diogonunes.jcolor.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.*;

@Slf4j
public class Parser {

    private static final Attribute[] heading = {WHITE_TEXT(), BOLD()};
    private static final Attribute[] blockQuote = {YELLOW_TEXT(), ITALIC()};
    private static final Attribute[] code = {RED_TEXT()};

    public static String parseIntoString(String fullDoc){
        boolean toLeaveABlankLine = true;
        StringBuilder builder = new StringBuilder();

        String[] strings = fullDoc.split("\n");
        builder.append(System.lineSeparator());
        for (String string : strings) {
            if (string.startsWith("#")) {
                builder.append(colorize(string.substring(1).trim(), heading));
                builder.append(System.lineSeparator());
                builder.append(System.lineSeparator());
            } else if (string.startsWith("`")) {
                builder.append(parseExample("    " + string.substring(1, string.lastIndexOf('`'))));
                builder.append(System.lineSeparator());
                builder.append(System.lineSeparator());
            } else if (string.startsWith(">")) {
                builder.append(colorize(string.substring(1).trim(), blockQuote));
                builder.append(System.lineSeparator());
            } else if (!StringUtils.isEmpty(string.trim())) {
                if (toLeaveABlankLine) {
                    builder.append(System.lineSeparator());
                    toLeaveABlankLine = false;
                }
                builder.append(string);
                builder.append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

    private static String parseExample(String str) {
        String[] strings = str.split("\\s");
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            String s = colorize(string, code);
            s = s.replaceAll("\\{\\{", "\u001B[34m");
            s = s.replaceAll("}}", "\u001B[31m");
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

}
