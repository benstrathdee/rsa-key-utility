package me.strathdee;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import me.strathdee.colours.ColourString;
import me.strathdee.rsakeyutility.Arguments;
import me.strathdee.rsakeyutility.RSAKeyUtilities;
import org.fusesource.jansi.AnsiConsole;

public final class Main {
    public static void main (String... args) throws Exception {
        // Initialise Jansi which allows for formatted output even on Windows terminals
        AnsiConsole.systemInstall();
        // Initialise the arguments
        Arguments arguments = new Arguments();
        JCommander jct = JCommander.newBuilder()
                .addObject(arguments)
                .build();

        try {
            // Try to parse the arguments from command line,
            // will throw ParameterException if something is wrong
            jct.parse(args);

            // If the help flag is used, show usage info
            if (arguments.isHelp()) jct.usage();
            else {
                // Run the app with command line arguments
                RSAKeyUtilities utils = new RSAKeyUtilities(arguments);
                utils.createAndWriteKeys();
            }
        } catch (ParameterException e) {
            String output = new ColourString(
                    "red",
                    "There was an issue with one or more of the arguments. Please see the help below for how to use."
            ).toString();
            System.err.println(output);
            jct.usage();
        }
    }
}
