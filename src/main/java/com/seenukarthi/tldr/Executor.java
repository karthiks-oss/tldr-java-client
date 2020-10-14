package com.seenukarthi.tldr;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
public class Executor {
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

        if(this.options.isVersion()){
            log.info("tldr 1.0.0");
            return;
        }

        if(StringUtils.isEmpty(this.options.getCommand())){
            jct.usage();
            return;
        }

        try {
            String remoteFile = "https://raw.githubusercontent.com/tldr-pages/tldr/master/pages/common/"+this.options.getCommand()+".md";
            log.info(remoteFile);
            String md = getText(remoteFile);
            Parser.parse(md);
        } catch (Exception e) {
            throw new TldrException(e.getMessage(),e);
        }

    }

    public static String getText(String url) throws Exception {
        return IOUtils.toString(new URL(url), Charset.defaultCharset());
    }
}
