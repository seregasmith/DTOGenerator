package codegenerator.data;

public class StringUtils {
    public static String toCamelCase(String... args) {
        if (args.length < 2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(args[0].toLowerCase());
        for (int i = 1; i < args.length; i++) {
            sb.append(capitalize(args[i]));
        }
        return sb.toString();
    }

    public static String capitalize(final String string) {
        String s = string.toLowerCase();
        s = Character.toString(s.charAt(0)).toUpperCase() + s.substring(1);
        return s;
    }
}
