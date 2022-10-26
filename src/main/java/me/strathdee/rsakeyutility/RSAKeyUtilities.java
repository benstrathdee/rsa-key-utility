package me.strathdee.rsakeyutility;

import me.strathdee.colours.ColourString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Scanner;

public class RSAKeyUtilities {
    private final Arguments args;
    private final Scanner scan = new Scanner(System.in);

    // Running this generates new keys to the specified folder
    public void createAndWriteKeys() {
        try {
            KeyPair keyPair = createKeys();
            writeKeyToFile(keyPair.getPublic(), args.directoryName + "/" + args.publicKeyName);
            System.out.println("\n----\n");
            writeKeyToFile(keyPair.getPrivate(), args.directoryName + "/" + args.privateKeyName);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(
                    new ColourString("red", "There was an issue with the Algorithm or KeySize")
            );
        }
    }

    // Generates RSA keypair
    private KeyPair createKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(args.keySize);
        return generator.generateKeyPair();
    }

    // Writes a key to file specified by fullPath
    private void writeKeyToFile(Key key, String fullPath) {
        File file = new File(fullPath);

        // Ensure that the specified directory exists
        String path = file.getParent();
        if (path != null) {
            try {
                Files.createDirectory(Paths.get(path));
                System.out.println(new ColourString("green", "Created directory " + path + "/"));
            } catch (FileAlreadyExistsException ignore) {
                System.out.println("Directory " + path + "/ already exists");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Attempts to write the key to the specified path
        System.out.println("Writing key to " + fullPath);
        try {
            if (file.isFile()) {
                // File already exists at path, confirm overwriting file
                if (!args.forceOverride) {
                    String output =
                            new ColourString("red", fullPath + " already exists! Overwrite? ") +
                            new ColourString("red", "white", "(Y/N)").toString();
                    System.err.println(output);
                }
                if (args.forceOverride || scan.next().equalsIgnoreCase("y")) {
                    try (FileOutputStream keyOutput = new FileOutputStream(fullPath)) { // try-with-resources
                        keyOutput.write(key.getEncoded());
                        System.out.println(
                                new ColourString("green", "Successfully wrote key to " + fullPath)
                        );
                    }
                } else {
                    System.out.println("Skipped writing key to " + fullPath);
                }
            } else {
                // File doesn't already exist, write to file
                try (FileOutputStream keyOutput = new FileOutputStream(fullPath)) {
                    keyOutput.write(key.getEncoded());
                    System.out.println(
                            new ColourString("green", "Successfully wrote key to " + fullPath)
                    );
                }
            }
        } catch (IOException e) {
            System.err.println(
                    new ColourString("red", "Failed to write key to file! " + e.getMessage())
            );
        }
    }

    public RSAKeyUtilities(Arguments args) {
        this.args = args;
    }
}
