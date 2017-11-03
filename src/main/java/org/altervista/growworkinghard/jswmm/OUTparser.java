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

import org.altervista.growworkinghard.jswmm.SWMMobjects.SWMMhydraulics.SWMMjunction;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Arrays.copyOfRange;

public class OUTparser {

    private File outFile;
    private byte[] fullByteFile;
    private int currentOffset;
    private byte[] currentBuffer;
    private static final int recordSize = 4;

    private int identifyingNumberTop;
    private int versionSWMMNumber;
    private int flowUnits;
    private int numberOfSubcatchments;
    private int numberOfNodes;
    private int numberOfLinks;
    private int numberOfPollutants;

    private int positionIDnames;
    private int positionProperties;
    private int positionComputedResults;

    private int numberOfReportedPeriod;
    private int errorCode;
    private int identifyingNumberBottom;

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

        LinkedHashMap<List<String>, List<List<Object>>> table = new LinkedHashMap<>();

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

        return table;
    }

    private void openingRecordsReader()
            throws IOException {

        currentOffset = 0;

        /**
         * Read the identifying number at top
         */
        identifyingNumberTop = readByteInt();

        /**
         * Read the version number of SWMM engine
         */
        versionSWMMNumber = readByteInt();

        /**
         * Read the code number for the flow units
         */
        flowUnits = readByteInt();

        /**
         * Read the number of subcatchments reported
         */
        numberOfSubcatchments = readByteInt();

        /**
         * Read the number of nodes reported
         */
        numberOfNodes = readByteInt();

        /**
         * Read the number of links reported
         */
        numberOfLinks = readByteInt();

        /**
         * Read the number of pollutants reported
         */
        numberOfPollutants = readByteInt();
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

        currentOffset = fullByteFile.length - 6*recordSize;

        /**
         * Position of the Object ID Names section
         */
        positionIDnames = readByteInt();

        /**
         * Position of the Object Properties section
         */
        positionProperties = readByteInt();

        /**
         * Position of the Object ID Names section
         */
        positionComputedResults = readByteInt();

        /**
         * Position of the Object ID Names section
         */
        numberOfReportedPeriod = readByteInt();

        /**
         * Position of the Object ID Names section
         * TODO{error code management}
         */
        errorCode = readByteInt();

        /**
         * Read the identifying number at the bottom
         */
        identifyingNumberBottom = readByteInt();
    }

    private int readByteInt() {
        currentBuffer = copyOfRange(fullByteFile, currentOffset, currentOffset + recordSize);
        currentOffset += recordSize;

        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private Double readByteDouble() {
        currentBuffer = copyOfRange(fullByteFile, currentOffset, currentOffset + recordSize);
        currentOffset += recordSize;

        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    private Character readByteChar() {
        currentBuffer = copyOfRange(fullByteFile, currentOffset, currentOffset + recordSize/4);
        currentOffset += recordSize/4;

        return ByteBuffer.wrap(currentBuffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
    }

    private String readByteString(int readingSize) {
        String string = null;
        for(int i=0; i<readingSize; i++) {
            string += readByteChar();
        }
        return string;
    }

    private String readID(int offset, int readingSize) {
        int IDlength = readByteInt();
        return readByteString(IDlength);
    }

}
