package com.seenukarthi.tldr;

import com.beust.jcommander.Parameter;
import lombok.Data;
import lombok.Getter;

@Data
public class Options {
    @Parameter(names={"--length", "-l"},required = true)
    int length;
    @Parameter(names={"--pattern", "-p"},required = true)
    int pattern;

    @Getter
    @Parameter(names = {"--help","-h"}, help = true)
    @SuppressWarnings("FieldMayBeFinal")
    private boolean help = false;
}
