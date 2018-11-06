package fSIM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.Date;

public class fSimulator {

	public static void main(String[] args) {

		//path for Excel template yields
		String inputYieldFilePath = "InputFiles/FCYields.xlsx";
		String inputDuddFilePath = "InputFiles/dudd.xlsx";
		//path for static yield serialisation and deserialisation
		String staticYieldSerialisationPath = "serializedFiles/FCYields.ser";
		String serialisedTimeStampPath = "serializedFiles/FCYieldsTimeStamp.ser";

		
		//getting the current static yield structure Time Stamp through deserialising
		TimeStamp savedTimeStamp = null;
		try {
			savedTimeStamp = doDeSerializeTS(serialisedTimeStampPath);
		} catch (Error e) {
			System.out.println("Time stamp error detected");
		} 

		//getting the date of the current Excel file 
		File file = new File(inputYieldFilePath);
		Date date = new Date(file.lastModified());

		System.out.println("The file was last modified on: "+ date);	 
		System.out.println("The date for the current serialisation is: " + savedTimeStamp.getDate());

		StaticYieldCreator eR = null; // new StaticYieldCreator();
		//checking date stamp for current with already serialized structure
		if (date.equals(savedTimeStamp.getDate())) {
			System.out.println("The most recent file is already built. Loading built yields ...");

			eR = doDeSerialize(staticYieldSerialisationPath);

		} else {	
			System.out.println("The most recent file has not already been built, building yields now ...");
			//creating serialised time stamp for next import
			TimeStamp tsStaticYieldFile = new TimeStamp(inputYieldFilePath);
			//create Java yield structure from excel sheet. Inputs: path and excel sheet index
			eR = new StaticYieldCreator();
			eR.staticYieldCreation(inputYieldFilePath, 0);

			//serialise timestamp and yields
			doSerialize(serialisedTimeStampPath, tsStaticYieldFile);
			doSerialize(staticYieldSerialisationPath, eR);
		}

		
		
			
//		
//			System.out.println("The unique yield identifiers currently loaded are: ");
//					for (GrowthYield gY: eR. ) {
//						
//					}
				

	}


	//serializing static yields
	public static void doSerialize(String serPath, StaticYieldCreator toSerialise) {//serializing
		try { 
			FileOutputStream fileOut = new FileOutputStream(serPath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(toSerialise);
			out.close();
			fileOut.close();
			System.out.println("Serialized Yield data is saved in " + serPath);
		} catch (IOException i) {
			System.out.println("IOException caught for deserializing " + serPath);
		}	
	}	

	// serialising time stamp
	public static void doSerialize(String serialisedTimeStampPath, TimeStamp tsStaticYields) {
		try { 
			FileOutputStream fileOut = new FileOutputStream(serialisedTimeStampPath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tsStaticYields);
			out.close();
			fileOut.close();
			System.out.println("Serialized yield Time Stamp data is saved in " + serialisedTimeStampPath);
		} catch (IOException i) {
			System.out.println("IOException caught for deserializing " + serialisedTimeStampPath);
		}	
	}

	//deserializing
	//of yield structure
	public static StaticYieldCreator doDeSerialize(String serPath) { 
		StaticYieldCreator DeSerialisedSYC = null;
		try {
			FileInputStream fileIn = new FileInputStream(serPath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			DeSerialisedSYC = (StaticYieldCreator) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("IOException caught for deserializing " + serPath);
		} catch (ClassNotFoundException c) {
			System.out.println("ClassNotFoundException is caught");
		}  
		return DeSerialisedSYC;
	}

	//of time stamp
	public static TimeStamp doDeSerializeTS(String serPath) { 
		TimeStamp tS = null;
		try {
			FileInputStream fileIn = new FileInputStream(serPath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tS = (TimeStamp) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("IOException caught for deserializing " + serPath);
		} catch (ClassNotFoundException c) {
			System.out.println("ClassNotFoundException is caught");
		}  
		return tS;
	}

}      



