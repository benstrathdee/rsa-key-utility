package me.strathdee.colours;

public enum ANSI {
    // Reset code for end of string
    RESET("\u001B[0m", null, null),
    // Text colour codes
    BLACK("\u001B[30m", "\u001B[40m", "\033[1;30m"),
    RED("\u001B[31m", "\u001B[41m", "\033[1;31m"),
    GREEN("\u001B[32m", "\u001B[42m", "\033[1;32m"),
    YELLOW("\u001B[33m", "\u001B[43m", "\033[1;33m"),
    BLUE("\u001B[34m", "\u001B[44m", "\033[1;34m"),
    PURPLE("\u001B[35m", "\u001B[45m","\033[1;35m"),
    CYAN("\u001B[36m", "\u001B[46m", "\033[1;36m"),
    WHITE("\u001B[37m", "\u001B[47m", "\033[1;37m");

    public final String code;
    public final String bgCode;
    public final String boldCode;


    ANSI(String code, String bgCode, String boldCode) {
        this.code = code;
        this.bgCode = bgCode;
        this.boldCode = boldCode;
    }
}
