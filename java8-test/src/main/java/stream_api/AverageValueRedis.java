package stream_api;

import java.util.List;
import java.util.OptionalDouble;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class AverageValueRedis
{
    
    private Jedis jedis;
    private Gson gson;
    private static final int SIZE = 1000000;
    private static final String KEY = "intNumber";
    private static final String HOST = "localhost";
    private static final int PORT = 6379;
    

    public AverageValueRedis() {
	super();
	gson = new Gson();
	jedis = new Jedis(HOST, PORT);
	fillRedis();
    }



    public static void main(String[] args)
    {
	AverageValueRedis averageValueRedis = new AverageValueRedis();
	averageValueRedis.averageWithoutLambda();
	averageValueRedis.averageJava8Lambda();
	averageValueRedis.averageJava8LambdaParallel();

    }
    
    public double averageWithoutLambda()
    {
	long size = jedis.llen(KEY);
	long t0 = System.currentTimeMillis();
	long elapsed = 0;

	int sum = 0;
	
	List<String> list = jedis.lrange(KEY, 0, size);
	for (String val : list)
	{
	    sum += Integer.parseInt(val);
	}

	double average = (double)sum / size;
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageJava7: %f \t Elapsed time:\t %d ms \t Size of Redis:\t %d", average, elapsed, SIZE);
	System.out.println();
	return average;
    }
    
    public double averageJava8Lambda()
    {
	long size = jedis.llen(KEY);
	
	long t0 = System.currentTimeMillis();
	long elapsed = 0;
	
	List<String> list = jedis.lrange(KEY, 0, size);

	double average = list.stream().mapToDouble(a -> Integer.parseInt(a)).average().getAsDouble();;
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageJava8Lambda: %f \t Elapsed time:\t %d ms \t Size of Redis:\t %d", average, elapsed, SIZE);
	System.out.println();
	return average;
    }

    public double averageJava8LambdaParallel()
    {
	long size = jedis.llen(KEY);
	
	long t0 = System.currentTimeMillis();
	long elapsed = 0;
	
	List<String> list = jedis.lrange(KEY, 0, size);
	
	double average = list.stream().parallel().mapToDouble(a -> Integer.parseInt(a)).average().getAsDouble();
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageJava8LambdaParallel: %f \t Elapsed time:\t %d ms \t Size of Redis:\t %d", average, elapsed, SIZE);
	System.out.println();
	return average;
    }

    public double averageJava8LambdaParallelAndFilter()
    {
	long size = jedis.llen(KEY);
	
	long t0 = System.currentTimeMillis();
	long elapsed = 0;
	
	List<String> list = jedis.lrange(KEY, 0, size);
	
	Integer i = Integer.parseInt("3");
	
	double average = list.stream().parallel().filter(n ->  n != null).mapToDouble(a -> Long.parseLong(a)).average().getAsDouble();
	
	elapsed = System.currentTimeMillis() - t0;
	System.out.printf("averageJava8LambdaParallel: %f \t Elapsed time:\t %d ms \t Size of Redis:\t %d", average, elapsed, SIZE);
	System.out.println();
	return average;
    }
    
    public void fillRedis()
    {
	jedis.del(KEY);
	for (int i = 0; i < SIZE; i++)
	{
	    jedis.lpush("intNumber", gson.toJson(rnd(1, 100)));
	}
    }
    
    public static int rnd(int min, int max)
    {
	max -= min;
	return (int) (Math.random() * ++max) + min;
    }

}
