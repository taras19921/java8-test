package files_util;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import stream_api.data.Person;

public class Main
{
    public static void main(String[] args) {
	
//	IntStream.range(0, 9).parallel().forEach(System.out::print);
	long t0 = new Date().getTime();
	long elapsed = 0;
	IntStream.range(0, 100000).forEachOrdered(System.out::print);
	System.out.println();
	elapsed = new Date().getTime() - t0;
	System.out.printf("forEachOrdered: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	t0 = new Date().getTime();
	IntStream.range(0, 100000).parallel().forEachOrdered(System.out::print);
	System.out.println();
	elapsed = new Date().getTime() - t0;
	System.out.printf("forEachOrdered parallel: Elapsed time:\t %d ms", elapsed);
	
//	directories(new File("/home/user"));
//	
//	directoriesJava8(new File("/home/user"));
//	
//	csvFiles(new File("/home/user"));
//	
//	csvFilesLambda(new File("/home/user"));
	
    }
    
    public static File[] directories(File file) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	File[] directories = new File(".").listFiles(new FileFilter()
	{
	    
	    @Override
	    public boolean accept(File pathname) {
	      return pathname.isDirectory();
	    }
	});
	
	elapsed = new Date().getTime() - t0;
	System.out.printf("directories: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return directories;
	
    }

    public static File[] csvFiles(File file) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	File[] csvFiles = new File(".").listFiles(new FileFilter()
	{
	    
	    @Override
	    public boolean accept(File pathname) {
		return pathname.getAbsolutePath().endsWith("csv");
	    }
	});
	
	elapsed = new Date().getTime() - t0;
	System.out.printf("csvFiles: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return csvFiles;
	
    }

    public static File[] csvFilesLambda(File file) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	File[] csvFilesLambda = new File(".").listFiles(new FileFilter()
	{
	    
	    @Override
	    public boolean accept(File pathname) {
		return pathname.getAbsolutePath().endsWith("csv");
	    }
	});
	
	elapsed = new Date().getTime() - t0;
	System.out.printf("csvFilesLambda: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return csvFilesLambda;
	
    }
    
    public static File[] directoriesJava8(File file) {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	File[] directories = file.listFiles(File::isDirectory);
	
	elapsed = new Date().getTime() - t0;
	System.out.printf("directoriesJava8: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return directories;
	
    }

}