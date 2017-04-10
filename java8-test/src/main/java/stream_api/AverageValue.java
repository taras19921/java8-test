package stream_api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

public class AverageValue
{

    private List<Integer> list = new ArrayList<>();

    public AverageValue() {
	super();
	fillIntList(1000000000);
    }

    public static void main(String[] args)
    {
	AverageValue averageValue = new AverageValue();
//	System.out.println("averageValue: " + averageValue.averageWithoutLambda());
//	System.out.println("averageValue with Lamda: " + averageValue.averageJava8Lambda());
	averageValue.averageOfEvenNumbersJava8Lambda();
	System.out.println("averageOfEvenNumbersWithoutLambda: " + averageValue.averageOfEvenNumbersWithoutLambda());

    }

    public double averageJava8Lambda()
    {
	System.out.println("list size: " + list.size());

	long t0 = System.currentTimeMillis();
	long elapsed = 0;

	double average = list.stream().mapToDouble(a -> a).average().getAsDouble();
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageJava8Lambda:%d Elapsed time:\t %d ms", average, elapsed);
	System.out.println();
	return average;
    }

    
    public double averageOfEvenNumbersJava8Lambda()
    {
	System.out.println("list size: " + list.size());
	
	long t0 = System.currentTimeMillis();
	long elapsed = 0;
	
	OptionalDouble average = list.parallelStream().filter(n -> n % 2 == 0).mapToDouble(a -> a).average();
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageOfEvenNumbersJava8Lambda: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return average.isPresent() ? average.getAsDouble() : 0;
    }

    public double averageWithoutLambda()
    {
	System.out.println("list size: " + list.size());
	long t0 = System.currentTimeMillis();
	long elapsed = 0;

	int sum = 0;
	for (int vals : list)
	{
	    sum += vals;
	}

	double average = sum / list.size();
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageWithoutLambda: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return average;
    }

    public double averageOfEvenNumbersWithoutLambda()
    {
	System.out.println("list size: " + list.size());
	long t0 = System.currentTimeMillis();
	long elapsed = 0;
	
	int sum = 0;
	int count = 0;
	for (int vals : list)
	{
	    if (vals % 2 == 0)
	    {
		sum += vals;
		count++;
	    }
	    
	}
	
	double average = (double)sum / count;
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageOfEvenNumbersWithoutLambda: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	return average;
    }

    public void fillIntList(int size)
    {

	for (int i = 0; i < size; i++)
	{
	    list.add(rnd(1, 1000));
	}
    }

    public static int rnd(int min, int max)
    {
	max -= min;
	return (int) (Math.random() * ++max) + min;
    }

    public List<Integer> getList()
    {
	return list;
    }

    public void setList(List<Integer> list)
    {
	this.list = list;
    }

}
