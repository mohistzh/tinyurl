package me.mohistzh.tinyurl.util;

/**
 * A utility static class to generate shorten url from given URL path.
 *
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLShortenUtil {

    private static final char[] letters = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * Base 62 numbers of characters (including 0~9,A~Z,a~z)
     * @param input
     * @return
     */
    public static String to62Digits(final long input) {
        StringBuilder stringBuilder = new StringBuilder();
        long remain = input;
        do {
            int index =(int) remain % 62;
            stringBuilder.append(letters[index]);
            remain = remain / 62;
        } while (remain > 0);

        return stringBuilder.reverse().toString();
    }

    /**
     * Recover identity from given hash(string)
     * @param input
     * @return
     */
    public static long toLong(final String input) {
        byte[] bytes = input.getBytes();
        long result = 0L;
        for (byte bt : bytes) {
            if (bt >= 'A' && bt <= 'Z') {
                result = (result * 62) + (bt - 55);
            } else if (bt >= 'a' && bt <= 'z') {
                result = (result * 62) + (bt - 61);
            } else if (bt >= '0' && bt <= '9') {
                result = (result * 62) + (bt - 48);
            } else {
                throw new IllegalArgumentException("Occurs illegal character: "+ (char) bt);
            }
        }
        return result;

    }

}
