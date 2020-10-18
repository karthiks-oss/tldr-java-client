package com.seenukarthi.tldr;

import lombok.Getter;

public class Util {
    public enum OS {
        WINDOWS("windows"), LINUX("linux"), MAC("osx"), SOLARIS("sunos"), COMMON("common");

        @Getter
        private final String os;

        OS(String os) {
            this.os = os;
        }
    }

    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            os = getOS(System.getProperty("os.name").toLowerCase());
        }
        return os;
    }

    public static OS getOS(String platform) {
        if (platform.contains("win")) {
            return OS.WINDOWS;
        } else if (platform.contains("nix")
                || platform.contains("nux")
                || platform.contains("aix")) {
            return OS.LINUX;
        } else if (platform.contains("mac")
                || platform.contains("macos")
                || platform.contains("osx")) {
            return OS.MAC;
        } else if (platform.contains("sunos")) {
            return OS.SOLARIS;
        }
        throw new TldrException("Invalid Platform");
    }
}