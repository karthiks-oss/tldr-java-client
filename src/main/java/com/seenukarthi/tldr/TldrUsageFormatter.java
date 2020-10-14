package com.seenukarthi.tldr;

import com.beust.jcommander.*;

import java.util.EnumSet;
import java.util.List;

public class TldrUsageFormatter extends DefaultUsageFormatter {
    public TldrUsageFormatter(JCommander commander) {
        super(commander);
    }

    /**
     * Appends the details of all parameters in the given order to the argument string builder, indenting every
     * line with indentCount-many indent.
     *
     * @param out the builder to append to
     * @param indentCount the amount of indentation to apply
     * @param indent the indentation
     * @param sortedParameters the parameters to append to the builder
     */
    public void appendAllParametersDetails(StringBuilder out, int indentCount, String indent,
                                           List<ParameterDescription> sortedParameters) {
        if (sortedParameters.size() > 0) {
            out.append(indent).append("\n  Options:\n");
        }

        for (ParameterDescription pd : sortedParameters) {
            WrappedParameter parameter = pd.getParameter();
            String description = pd.getDescription();
            boolean hasDescription = !description.isEmpty();

            // First line, command name
            out.append(indent)
                    .append("  ")
                    .append(parameter.required() ? "* " : "  ")
                    .append(pd.getNames())
                    .append("\n");

            if (hasDescription) {
                wrapDescription(out, indentCount, s(indentCount) + description);
            }
            Object def = pd.getDefault();

            if (pd.isDynamicParameter()) {
                String syntax = "Syntax: " + parameter.names()[0] + "key" + parameter.getAssignment() + "value";

                if (hasDescription) {
                    out.append(newLineAndIndent(indentCount));
                } else {
                    out.append(s(indentCount));
                }
                out.append(syntax);
            }

            Class<?> type = pd.getParameterized().getType();



            if (def != null && !pd.isHelp() && !"boolean".equals(type.getName())) {
                String displayedDef = Strings.isStringEmpty(def.toString()) ? "<empty string>" : def.toString();
                String defaultText = "Default: " + (parameter.password() ? "********" : displayedDef);

                if (hasDescription) {
                    out.append(newLineAndIndent(indentCount));
                } else {
                    out.append(s(indentCount));
                }
                out.append(defaultText);
            }


            if (type.isEnum()) {
                String valueList = EnumSet.allOf((Class<? extends Enum>) type).toString();
                String possibleValues = "Possible Values: " + valueList;

                // Prevent duplicate values list, since it is set as 'Options: [values]' if the description
                // of an enum field is empty in ParameterDescription#init(..)
                if (!description.contains("Options: " + valueList)) {
                    if (hasDescription) {
                        out.append(newLineAndIndent(indentCount));
                    } else {
                        out.append(s(indentCount));
                    }
                    out.append(possibleValues);
                }
            }
            out.append("\n");
        }
    }

    /**
     * Returns new line followed by indent-many spaces.
     *
     * @return new line followed by indent-many spaces
     */
    private static String newLineAndIndent(int indent) {
        return "\n" + s(indent);
    }
}
