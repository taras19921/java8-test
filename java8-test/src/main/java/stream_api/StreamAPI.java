package stream_api;

import java.util.ArrayList;
import java.util.Optional;

public class StreamAPI
{

    public static void main(String[] args)
    {
	ArrayList<Integer> testValues = new ArrayList();
	testValues.add(0, 15);
	testValues.add(1, 1);
	testValues.add(2, 2);
	testValues.add(3, 100);
	testValues.add(4, 50);

	Optional<Integer> maxValue = testValues.stream().max(Integer::compareTo);
	System.out.println("MaxValue=" + maxValue);
	Optional<Integer> minValue = testValues.stream().min(Integer::compareTo);
	System.out.println("MinValue=" + minValue);

    }

}
