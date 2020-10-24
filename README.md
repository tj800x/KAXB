## Synopsis

This project is used to generate native Kotlin classes from an xsd schema, similar to the JAXB tool for Java. The project will include a plugin for gradle and Intellij IDEA.

## Build Status

Compiles and runs under Kotlin 1.4.10.

## Motivation

I needed a tool that would generate native Kotlin classes rather than Java classes and then convert to Kotlin.

## Installation

Build using Kotlin 1.4.10 branch and run from source.  This is a work-in-progress at this point.

## Running

Once the archive has been extracted run the **bin/kaxb --P \<destination package\> --S \<schema file\> --T \<target directory\>**

## Tests

## Contributors

* Simon Wiehe
* Tom Johnson

## License

Apache License 2.0
