package me.strathdee.rsakeyutility;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arguments {
    @Parameter
    public List<String> parameters = new ArrayList<>();

    @Parameter(
            names = { "-d", "--directory" },
            description = "The name of the directory to save the keys"
    )
    public String directoryName = "certificates";

    @Parameter(
            names = { "-pub", "--public" },
            description = "The name of the public key to be created"
    )
    public String publicKeyName = "key.pub";

    @Parameter(
            names = { "-priv", "--private" },
            description = "The name of the private key to be created"
    )
    public String privateKeyName = "key.priv";

    @Parameter(
            names = { "-k", "--keysize" },
            description = "The keysize to generate the keys with (2048 + for JWT compatible keys)"
    )
    public Integer keySize = 2048;

    @Parameter(
            names = { "-F", "--force" },
            description = "Force an override of any existing keys"
    )
    public Boolean forceOverride = false;

    @Parameter(
            names = { "-h", "--help" },
            description = "I think you know what this one does!",
            help = true
    )
    public Boolean help;

    public boolean isHelp() {
        return Objects.requireNonNullElse(help, false);
    }
}
