package com.seenukarthi.tldr;

import com.beust.jcommander.JCommander;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
public class Executor {
    private static final String TLDR_MASTER_PAGES_URL = "https://raw.githubusercontent.com/tldr-pages/tldr/master/pages/";
    private final Options options;
    private final JCommander jct;

    public Executor(Options options, JCommander jct) {
        this.options = options;
        this.jct = jct;
    }

    public void doRun() {
        if (this.options.isHelp()) {
            jct.usage();
            return;
        }

        if (this.options.isVersion()) {
            log.info("tldr 1.0.0");
            return;
        }

        if (StringUtils.isEmpty(this.options.getCommand())) {
            jct.usage();
            return;
        }
        try {
            log.info(Parser.parseIntoString(getContentForTheCommand(this.options.getCommand())));
        } catch (TldrCommandNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new TldrException(e.getMessage(), e);
        }

    }

    @SneakyThrows
    private String getContentForTheCommand(String theCommand) {
        Util.OS platform = Util.getOS();
        if (!StringUtils.isEmpty(this.options.getPlatform())) {
            platform = Util.getOS(this.options.getPlatform());
        }
        return getText(theCommand, platform);
    }

    public static String getText(String command, Util.OS platform) throws IOException {
        String platformRemoteFile = TLDR_MASTER_PAGES_URL + platform.getOs() + "/" + command + ".md";
        String commonRemoteFile = TLDR_MASTER_PAGES_URL + Util.OS.COMMON.getOs() + "/" + command + ".md";
        String content;

        try {
            content = IOUtils.toString(new URL(platformRemoteFile), Charset.defaultCharset());
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                content = IOUtils.toString(new URL(commonRemoteFile), Charset.defaultCharset());
            } catch (FileNotFoundException e) {
                throw new TldrCommandNotFoundException(command);
            }
        }

        return content;
    }
}
