# TLDR Java Client

This is a Java client for [tldr-pages](https://tldr.sh/)

## Building

### Prerequisite

- Java Development Kit 1.8.
- Maven 3.6.X.

### Build.

Run `mvn clean package` to build the project and extract `./target/tldr.zip` and add the `$EXTRACTED_FOLDER\bin` to the path.

## Usage

```
Usage: tldr [options] SEARCH

  Options:
    --help, -h
      Print help information and exit
    --platform, -p
      Platform [windows, macos|osx, linux]
    --version
      Print version and exit
```

## Work in progress.

* Language Support
* Caching support.
* Local file rendering.

## Download

Download tldr from [releases](https://github.com/seenukarthi/tldr-java-client/releases)
