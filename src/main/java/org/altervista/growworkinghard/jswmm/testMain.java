package org.altervista.growworkinghard.jswmm;

import java.io.File;
import java.io.IOException;

public class testMain {
    public static void main(String[]args) throws IOException {

        File outFile = new File("2D.out");

        OUTparser testOut = new OUTparser(outFile);

        testOut.reader();
    }
}