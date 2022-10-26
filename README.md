# RSA Key Utility

A simple CLI for generating RSA keys for use with JWT encoding/decoding. Started as an embedded class in my 
Spring Boot JWT implementation, but it felt appropriate to flesh it out a bit more for reusability (plus a good 
chance to fiddle around with making a small CLI)

## Requirements

* JDK 18+

## Usage

### Build

1. `git clone` this repo
2. Open a terminal in the cloned directory and run the command `./mvnw clean install` to build an artifact
3. A standalone `.jar` file with bundled dependencies will be generated in the `/target` directory
4. The `.jar` file can be moved and run from any location

### Usage

The `.jar` file can be run with or without arguments. 

#### Without arguments

1. Simply run `java -jar [filename.jar]` in a terminal window
2. A `/certificates` directory will be created (if it does not exist already) containing `key.pub` and `key.priv` 
3. If one or more of the above files exist, you will be prompted whether to overwrite it or not

These are the default values for directory and filenames, with a keysize of 2048 bits 
(minimum recommended size for JWT usage)

#### With arguments

The following arguments are available:

* `-d` / `--directory` - specify the name of the directory to save the keys into (default `certificates\`)
* `-pub` / `--public` - specify the name of the Public Key (default `key.pub`)
* `-priv` / `--private` - specify the name of the Private Key (default `key.priv`)
* `-k` / `--keysize` - specify the KeySize for key generation (default `2048`)
* `-F` / `--force` - force override any existing keys in the specified location
* `-h` / `--help` - display this list of flags

e.g.
``` e.g.
java -jar RSA-Key-Utility-1.0.jar -d certs --public key.public --private key.private --keysize 4096
```

If any arguments values are invalid, `help` output will be displayed.

## Dependencies

* JCommander (for parsing arguments from command line)
* Jansi (for allowing formatted ANSI output on Windows terminals)
* Maven Assembly Plugin (for building an executable artifact with bundled dependencies)
