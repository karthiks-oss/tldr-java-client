package com.seenukarthi.tldr;

import com.beust.jcommander.Parameter;
import lombok.Getter;


public class Options {

    @Getter
    @Parameter(description = "SEARCH")
    private String command;

    @Getter
    @SuppressWarnings("FieldMayBeFinal")
    @Parameter(names = {"--version"}, description = "Print version and exit")
    private boolean version;

    @Getter
    @SuppressWarnings("FieldMayBeFinal")
    @Parameter(names = {"--help","-h"}, description = "Print help information and exit",help = true)
    private boolean help = false;

    @Getter
    @Parameter(names = {"--update","-u"},description = "Update local database")
    private boolean updateCache;

    @Getter
    @Parameter(names = {"--clear-cache","-c"},description = "Clear local cache")
    private boolean clearCache;

    @Getter
    @Parameter(names = {"--platform","-p"},description = "Platform [windows, macos|osx, linux]")
    private String platform;

}
