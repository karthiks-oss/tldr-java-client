package com.seenukarthi.tldr;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
            log.info("tldr version 1.0.0");
            return;
        }

        if(StringUtils.isEmpty(this.options.getCommand())){
            jct.usage();
            return;
        }

        log.info("The search is {}",this.options.getCommand());
    }
}
