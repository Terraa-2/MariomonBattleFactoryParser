package battlefactory.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import battlefactory.dto.CaptureSet;

public class CaptureService {

	/*Main method that takes all the files in the arguments and reads them, to then write their parsed content into the file inputted as
	the last argument. The input files must be CSV files with the following columns:
	Capture	Ability Name	Ability Number	Item	Move1	Move2	Move3	Move4	Nature	HP EVS	ATK EVS	DEF EVS	SPA EVS	SPDEF EVS	SPE EVS
	The output format is the one indicated in CaptureSet's toString() method.
	*/
	public static void main (String[] args) {
		//If the args have at least 2 files (one to read and one to write)
		if(args.length > 1) {
			List<CaptureSet> captures = new ArrayList<>();
			try(BufferedWriter fileOut = new BufferedWriter(new FileWriter(args[args.length-1]))) {
				List<String> captureLines = new ArrayList<>();
				List<BufferedReader> fileList = new ArrayList<>();
				for(int i=0; i<args.length-1;i++) {
					BufferedReader file = new BufferedReader(new FileReader(args[i]));
					fileList.add(file);
				}
				
				//Iterate all the files and add their sets to the list
				Iterator<BufferedReader> fileIterator = fileList.iterator();
				while(fileIterator.hasNext()) {
					BufferedReader currentFile = fileIterator.next();
					String line = "";
					int linesRead = 0;
					while(line != null) {
						line = currentFile.readLine();
						//Skip column headers
						if(linesRead != 0) captureLines.add(line);
						linesRead++;
					}
				}
				captures.addAll(CaptureSetBuilder.buildAll(captureLines));
				String captureStr = captures.toString();
				//Removing first and last char because java lists automatically add a bracket at the beginning and at the end.
				fileOut.write(captureStr.substring(1,captureStr.length()-1));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
	}
}
