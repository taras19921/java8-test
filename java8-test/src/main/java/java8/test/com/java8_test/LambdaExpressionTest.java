package java8.test.com.java8_test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressionTest
{

    public static void main(String[] args)
    {
	String separator = ",";
	Arrays.asList( "a", "b", "d" ).forEach(
	e -> System.out.print( e + separator ) );
	
	//
	
//	Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
	
//	Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
//	    int result = e1.compareTo( e2 );
//	    System.out.println("result: " + result);
//	    return result;
//	} );
	
//	List<String> names = Arrays.asList("z", "c");
//
//	Collections.sort(names, new Comparator<String>() {
//	    @Override
//	    public int compare(String a, String b) {
//		System.out.println(b.compareTo(a));
//	        return b.compareTo(a);
//	    }
//	});
//	System.out.println(names);
//	
//	Collections.sort(names, (String a, String b) -> {
//	    return b.compareTo(a);
//	});

    }

}
