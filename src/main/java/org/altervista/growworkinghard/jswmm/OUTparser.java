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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class OUTparser {

    private File outFile;
    private FileInputStream outStream;

    public OUTparser(File outFile)
            throws FileNotFoundException {
        this.outFile = outFile;
        outStream = new FileInputStream(this.outFile);
    }

    public void setFile(File outFile){
        this.outFile = outFile;
    }

    public File getFile(File outFile){
        return this.outFile;
    }


    public LinkedHashMap<List<String>, List<List<Object>>> reader()
            throws IOException {

        LinkedHashMap<List<String>, List<List<Object>>> table =
                new LinkedHashMap<>();

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

        // Closing Records
        closingRecordsReader();

        try {
            closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return table;
    }

    private void closeFile() throws IOException {
        outStream.close();
    }

    private void openingRecordsReader() {
        /**
         * Read the engine number equal to version
         */
        
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

}
