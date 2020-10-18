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

    public static void parse(String fullDoc) {

        boolean toLeaveABlankLine = true;

        String[] strings = fullDoc.split("\n");
        log.info("");
        for (String string : strings) {
            if (string.startsWith("#")) {
                log.info(colorize(string.substring(1).trim(), heading));
                log.info("");
            } else if (string.startsWith("`")) {
                log.info(parseExample("    " + string.substring(1, string.lastIndexOf('`'))));
                log.info("");
            } else if (string.startsWith(">")) {
                log.info(colorize(string.substring(1).trim(), blockQuote));
            } else if (!StringUtils.isEmpty(string.trim())) {
                if (toLeaveABlankLine) {
                    log.info("");
                    toLeaveABlankLine = false;
                }
                log.info(string);
            }
        }
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
