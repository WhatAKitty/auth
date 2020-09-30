package cn.dustlight.oauth2.uim.handlers.generator;

import cn.dustlight.generator.UniqueGenerator;

public class UniqueLongToStringGenerator implements UniqueGenerator<String> {

    public static final char[] DEFAULT_HEX = "0123456789abcdefghijklnmopqrstuvwxyz".toCharArray();
    private UniqueGenerator<Long> generator;
    private char[] hex;

    public UniqueLongToStringGenerator(UniqueGenerator<Long> generator, char[] hex) {
        this.generator = generator;
        this.hex = hex;
    }

    public UniqueLongToStringGenerator(UniqueGenerator<Long> generator) {
        this(generator, DEFAULT_HEX);
    }

    @Override
    public String generate() {
        return convert(generator.generate());
    }

    protected String convert(Long number) {
        StringBuilder builder = new StringBuilder();
        int len = hex.length;
        while (number > 0) {
            builder.insert(0, hex[(int) (number % len)]);
            number = number / len;
        }
        return builder.toString();
    }

    public void setGenerator(UniqueGenerator<Long> generator) {
        this.generator = generator;
    }

    public void setHex(char[] hex) {
        this.hex = hex;
    }

    public char[] getHex() {
        return hex;
    }

    public UniqueGenerator<Long> getGenerator() {
        return generator;
    }
}
