package stream_api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsVSCollections
{

    public static void main(String[] args)
    {
	
	List<Integer> sourceList = new ArrayList<>();
	for (int i = 1; i < 10000000; i++)
	{
	    sourceList.add(i);
	}
	
	Integer sumOdd = sourceList.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
	System.out.println(sumOdd);

	// using lambda with Stream API, filter example

	

//	sumIterator(sourceList);
	
	
//	sumStream(sourceList);
	
//	streamsVSCollections(sourceList);
	
    }

    private static void sumStream(List<Integer> sourceList)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	sourceList.stream().parallel().filter(i -> i > 10).mapToInt(i -> i).sum();
	
	elapsed = new Date().getTime() - t0;
	System.out.printf("sumStream: Elapsed time:\t %d ms", elapsed);
	System.out.println();
    }

    private static void sumIterator(List<Integer> sourceList)
    {
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	Iterator<Integer> it = sourceList.iterator();
	int sum = 0;
	while (it.hasNext())
	{
	    int num = it.next();
	    if (num > 10)
	    {
		sum += num;
	    }
	}
	elapsed = new Date().getTime() - t0;
	System.out.printf("sumIterator: Elapsed time:\t %d ms", elapsed);
	System.out.println();
    }

    public static void streamsVSCollections(List<Integer> sourceList)
    {
	// Calculating square root of even numbers from 1 to N

	List<Double> result = new ArrayList<>();

	// Collections approach
	long t0 = System.nanoTime();
	long elapsed = 0;
	for (Integer i : sourceList)
	{
	    if (i % 2 == 0)
	    {
		result.add(Math.sqrt(i));
	    }
	}
	elapsed = System.nanoTime() - t0;
	System.out.printf("Collections: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));

	// Stream approach
	Stream<Integer> stream = sourceList.stream();
	t0 = System.nanoTime();
	result = stream.filter(i -> i % 2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
	elapsed = System.nanoTime() - t0;
	System.out.printf("Streams: Elapsed time:\t\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));

	// Parallel stream approach
	stream = sourceList.stream().parallel();
	t0 = System.nanoTime();
	result = stream.filter(i -> i % 2 == 0).map(i -> Math.sqrt(i)).collect(Collectors.toList());
	elapsed = System.nanoTime() - t0;
	System.out.printf("Parallel streams: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));
    }

}
