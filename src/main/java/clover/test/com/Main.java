package clover.test.com;

import picocli.CommandLine;

public class Main {

    public static void main(final String[] args) {
        System.exit(new CommandLine(new RevenueCommand()).execute(args));
    }
}
