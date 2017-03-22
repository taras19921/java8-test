package files_util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import stream_api.data.Person;

public class Main
{
    public static void main(String[] args) throws IOException
    {

	printLines(new File("/home/user/Desktop/commands"));

	// directories(new File("/home/user"));
	//
	// directoriesJava8(new File("/home/user"));
	//
	// csvFiles(new File("/home/user"));
	//
	// csvFilesLambda(new File("/home/user"));

    }

    public static File[] directories(File file)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	File[] directories = new File(".").listFiles(new FileFilter()
	{

	    @Override
	    public boolean accept(File pathname)
	    {
		return pathname.isDirectory();
	    }
	});

	elapsed = new Date().getTime() - t0;
	System.out.printf("directories: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return directories;

    }

    public static File[] csvFiles(File file)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	File[] csvFiles = new File(".").listFiles(new FileFilter()
	{

	    @Override
	    public boolean accept(File pathname)
	    {
		return pathname.getAbsolutePath().endsWith("csv");
	    }
	});

	elapsed = new Date().getTime() - t0;
	System.out.printf("csvFiles: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return csvFiles;

    }

    public static void printLines(File file) throws IOException
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	final Path path = file.toPath();
	try (
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8))
	{
	    lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
	}

	elapsed = new Date().getTime() - t0;
	System.out.printf("printLines: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void rangeForEachOrdered(File file) throws IOException
    {
	// IntStream.range(0, 9).parallel().forEach(System.out::print);
	long t0 = new Date().getTime();
	long elapsed = 0;
	IntStream.range(0, 10000000).forEachOrdered(r -> {
	    if (r < 0)
	    {
		System.out.println(r);
	    }
	});
	System.out.println();
	System.out.println();
	elapsed = new Date().getTime() - t0;
	System.out.printf("forEachOrdered: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	t0 = new Date().getTime();
	IntStream.range(0, 100000000).parallel().forEachOrdered(r -> {
	    if (r < 0)
	    {
		System.out.println(r);
	    }
	});
	System.out.println();
	elapsed = new Date().getTime() - t0;
	System.out.printf("forEachOrdered parallel: Elapsed time:\t %d ms", elapsed);

    }

    public static File[] csvFilesLambda(File file)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	File[] csvFilesLambda = new File(".").listFiles(new FileFilter()
	{

	    @Override
	    public boolean accept(File pathname)
	    {
		return pathname.getAbsolutePath().endsWith("csv");
	    }
	});

	elapsed = new Date().getTime() - t0;
	System.out.printf("csvFilesLambda: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return csvFilesLambda;

    }

    public static File[] directoriesJava8(File file)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	File[] directories = file.listFiles(File::isDirectory);

	elapsed = new Date().getTime() - t0;
	System.out.printf("directoriesJava8: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return directories;

    }

}
