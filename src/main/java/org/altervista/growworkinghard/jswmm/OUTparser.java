/*
 * OUTparser Reading and store data from a SWMM .out file
 *
 * Copyright (C) 2017 Daniele Dalla Torre

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

/**
 * @author ftt01 <dallatorre.daniele@gmail.com></>
 * @version 0.1
 */

package org.altervista.growworkinghard.jswmm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Arrays.copyOfRange;

public class OUTparser {

    private File outFile;
    //private FileInputStream outStream;
    private byte[] fullByteFile;
    private int currentOffset;
    private byte[] currentBuffer;
    private static final int recordSize = 4;

    public OUTparser(File outFile)
            throws IOException {

        this.outFile = outFile;
        //outStream = new FileInputStream(this.outFile);
        fullByteFile = Files.readAllBytes(outFile.toPath());
    }

    public void setFile(File outFile){
        this.outFile = outFile;
    }

    public File getFile(){
        return this.outFile;
    }


    public LinkedHashMap<List<String>, List<List<Object>>> reader()
            throws IOException {

        LinkedHashMap<List<String>, List<List<Object>>> table =
                new LinkedHashMap<>();

        // Closing Records
        closingRecordsReader();

        // Opening Records
        openingRecordsReader();

        // Object ID Names
        objectIDReader();

        // Object Properties
        objectPropertiesReader();

        // Reporting Variables
        reportingVariablesReader();

        // Reporting Interval
        reportingIntervalReader();

        // Computed Results
        computedResultsReader();

/*
        try {
            closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        return table;
    }

/*    private void closeFile() throws IOException {
        outStream.close();
    }*/

    private void openingRecordsReader()
            throws IOException {

        /**
         * Read the engine number equal to version
         */
        currentOffset = fullByteFile.length - 6*recordSize;

        /**
         * Read the code number for the flow units
         */

        /**
         * Read the number of subcatchments reported
         */

        /**
         * Read the number of nodes reported
         */

        /**
         * Read the number of links reported
         */

        /**
         * Read the number of pollutants reported
         */
    }

    private void objectIDReader() {

    }

    private void objectPropertiesReader() {

    }

    private void reportingVariablesReader() {

    }

    private void reportingIntervalReader() {

    }

    private void computedResultsReader() {

    }

    private void closingRecordsReader() {

    }

    private int readByteInt(int offset, int readingSize) {
        currentBuffer = copyOfRange(fullByteFile, offset, offset + readingSize);

        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private Double readByteDouble(int offset, int readingSize) {
        currentBuffer = copyOfRange(fullByteFile, offset, offset + readingSize);

        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    private Character readByteChar(int offset, int readingSize) {
        currentBuffer = copyOfRange(fullByteFile, offset, offset + readingSize);
        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
    }

    private String readByteString(int offset, int readingSize) {
        String string = null;
        int relativeOffset = offset + readingSize;
        for(int i=0; i<readingSize; i++) {
            string += readByteChar(relativeOffset, recordSize);
        }
        return string;
    }

    private String readID(int offset, int readingSize) {
        int IDlength = readByteInt(offset,readingSize);
        return readByteString(offset+readingSize, IDlength);
    }

}
