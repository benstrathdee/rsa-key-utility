package me.strathdee.colours;

public class ColourString {
    private String bgColorCode = null;
    private final String colorCode;
    private final String string;
    private final String resetCode = ANSI.RESET.code;

    public String toString() {
        if (bgColorCode == null) {
            return this.colorCode + this.string + this.resetCode;
        }
        return this.bgColorCode + this.colorCode + this.string + this.resetCode;
    }

    public ColourString(String colorCode, String string) {
        this.colorCode = switch (colorCode.toLowerCase()) {
            case "black" -> ANSI.BLACK.code;
            case "red" -> ANSI.RED.code;
            case "green" -> ANSI.GREEN.code;
            case "yellow" -> ANSI.YELLOW.code;
            case "blue" -> ANSI.BLUE.code;
            case "purple" -> ANSI.PURPLE.code;
            case "cyan" -> ANSI.CYAN.code;
            case "white" -> ANSI.WHITE.code;
            default -> "";
        };

        this.string = string;
    }

    public ColourString(String bgColorCode, String colorCode, String string) {
        // Bold appears better on a coloured background
        this.colorCode = switch (colorCode.toLowerCase()) {
            case "black" -> ANSI.BLACK.boldCode;
            case "red" -> ANSI.RED.boldCode;
            case "green" -> ANSI.GREEN.boldCode;
            case "yellow" -> ANSI.YELLOW.boldCode;
            case "blue" -> ANSI.BLUE.boldCode;
            case "purple" -> ANSI.PURPLE.boldCode;
            case "cyan" -> ANSI.CYAN.boldCode;
            case "white" -> ANSI.WHITE.boldCode;
            default -> "";
        };

        this.bgColorCode = switch (bgColorCode.toLowerCase()) {
            case "black" -> ANSI.BLACK.bgCode;
            case "red" -> ANSI.RED.bgCode;
            case "green" -> ANSI.GREEN.bgCode;
            case "yellow" -> ANSI.YELLOW.bgCode;
            case "blue" -> ANSI.BLUE.bgCode;
            case "purple" -> ANSI.PURPLE.bgCode;
            case "cyan" -> ANSI.CYAN.bgCode;
            case "white" -> ANSI.WHITE.bgCode;
            default -> null;
        };

        this.string = string;
    }
}
