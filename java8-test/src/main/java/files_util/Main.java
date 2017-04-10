package files_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
    public static void main(String[] args) throws Exception
    {

	List<File> files = Arrays.asList(new File("/home/user/Desktop/cache-example (copy).xml"),
		new File("/home/user/Desktop/cache-example (copy).xml"),
		new File("/home/user/Desktop/cache-example (copy).xml"),
		new File("/home/user/Desktop/cache-example (copy).xml"),
		new File("/home/user/Desktop/cache-example (copy).xml"));

	bufferedReader(files);
	System.out.println("******************************************************************");
	System.out.println("******************************************************************");
	commonsIO(files);
	System.out.println("******************************************************************");
	System.out.println("******************************************************************");
	printLinesFromFilesJava8(files);
	System.out.println("******************************************************************");
	System.out.println("******************************************************************");
	printLinesJava8InParallel(files);

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

    public static void bufferedReader(List<File> files) throws Exception
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	BufferedReader reader = null;
	for (File file : files)
	{
	    try
	    {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
		String line;
		while ((line = reader.readLine()) != null)
		{
		    line.length();
		    // System.out.println(line);
		}
	    } catch (IOException e)
	    {
		// log error
	    } finally
	    {
		if (reader != null)
		{
		    try
		    {
			reader.close();
		    } catch (IOException e)
		    {
			// log warning
		    }
		}
	    }
	}

	elapsed = new Date().getTime() - t0;
	System.out.printf("bufferedReader: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void commonsIO(List<File> files) throws Exception
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	BufferedReader reader = null;
	for (File file : files)
	{
	    try
	    {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
		String line;
		while ((line = reader.readLine()) != null)
		{
		    // System.out.println(line);
		    line.length();
		}
	    } catch (IOException e)
	    {
		// log error
	    } finally
	    {
		if (reader != null)
		{
		    try
		    {
			reader.close();
		    } catch (IOException e)
		    {
			// log warning
		    }
		}
	    }
	}

	elapsed = new Date().getTime() - t0;
	System.out.printf("commonsIO: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void printLinesFromFilesJava8(List<File> files) throws Exception
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	files.stream().forEach(f -> {
	    try (
		    Stream<String> lines = Files.lines(f.toPath(), StandardCharsets.UTF_8))
	    {
		lines.onClose(() -> System.out.println("Done!"))
			.forEach(l -> l.length()/* System.out::println */);
	    } catch (IOException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	});

	elapsed = new Date().getTime() - t0;
	System.out.printf("printLinesJava8: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void printLinesJava8(File file) throws Exception
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	final Path path = file.toPath();
	try (
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8))
	{
	    lines.onClose(() -> System.out.println("Done!"))
		    .forEach(l -> l.length()/* System.out::println */);
	}

	elapsed = new Date().getTime() - t0;
	System.out.printf("printLinesJava8: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void printLinesJava8InParallel(List<File> files) throws IOException
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	// final Path path = files.toPath();
	files.stream().parallel().forEach(f -> {
	    try (
			Stream<String> lines = Files.lines(f.toPath(), StandardCharsets.UTF_8))
		{
		    lines.parallel().onClose(() -> System.out.println("Done!"))
			    .forEach(l -> l.length()/* System.out::println */);
		} catch (IOException e)
		{
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	});
	

	elapsed = new Date().getTime() - t0;
	System.out.printf("printLinesJava8InParallel: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void printLinesJava8InParallel(File file) throws IOException
    {
	long t0 = new Date().getTime();
	long elapsed = 0;

	final Path path = file.toPath();
	try (
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8))
	{
	    lines.parallel().onClose(() -> System.out.println("Done!"))
		    .forEach(l -> l.length()/* System.out::println */);
	}

	elapsed = new Date().getTime() - t0;
	System.out.printf("printLinesJava8InParallel: Elapsed time:\t %d ms", elapsed);
	System.out.println();

    }

    public static void rangeForEachOrdered(File file) throws Exception
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
