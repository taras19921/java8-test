import java.io.File;
import java.io.FileFilter;
import java.util.Date;

public class Main
{

    public static void main(String[] args)
    {
	File[] directories1 = new File(".").listFiles(new FileFilter()
	{
	    
	    @Override
	    public boolean accept(File pathname) {
	      return pathname.isDirectory();
	    }
	});
	
	long t0 = new Date().getTime();
	long elapsed = 0;
	
	
	File[] directories = new File("/home/user").listFiles(File::isDirectory);
	elapsed = new Date().getTime() - t0;
	System.out.printf("directories: Elapsed time:\t %d ms", elapsed);
	System.out.println();
	System.out.println(directories.length);

    }

}
