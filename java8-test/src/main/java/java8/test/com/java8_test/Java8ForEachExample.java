package java8.test.com.java8_test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.function.Consumer;
import java.lang.Integer;

public class Java8ForEachExample
{

    public static void main(String[] args)
    {

	// creating sample Collection
	List<Integer> myList = new ArrayList<Integer>();
	for (int i = 0; i < 100000; i++)
	    myList.add(i);

	// traversing using Iterator
	Iterator<Integer> it = myList.iterator();

	long startIteratorTime = new Date().getTime();

	while (it.hasNext())
	{
	    if (it.next() < 0)
		System.out.println("Iterator Value::"/* + i*/);
	}
	long stopIteratorTime = new Date().getTime();
	long iteratorTime = stopIteratorTime - startIteratorTime;
	System.out.println("traversing time using");
	System.out.println("Iterator::" + iteratorTime);

	// traversing through forEach method of Iterable with anonymous class
	long startIterableTime = new Date().getTime();
	myList.forEach(new Consumer<Integer>()
	{

	    public void accept(Integer t)
	    {
		if (myList.size() < 0)
		{
		    
		}
		// System.out.println("forEach anonymous class Value::" + t);
	    }

	});
	long stopIterableTime = new Date().getTime();
	long iterableTime = stopIterableTime - startIterableTime;
	System.out.println("method of Iterable with anonymous class::" + iterableTime);

	// traversing with Consumer interface implementation
	MyConsumer action = new MyConsumer();
	long startConsumerIntfTime = new Date().getTime();
	myList.forEach(action);
	long stopConsumerIntfTime = new Date().getTime();
	long consumerIntfTime = stopConsumerIntfTime - startConsumerIntfTime;
	System.out.println("Consumer interface implementation::" + consumerIntfTime);

    }

}

// Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer>
{

    public void accept(Integer t)
    {
	if (t < 0)
	    System.out.println("Consumer impl Value::" + t);
    }

}
