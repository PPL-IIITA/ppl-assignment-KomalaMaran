import java.util.*;
import java.io.*;

public class Testingutility{
	public static void main(String args[]) throws IOException{

		String[] girls=new String[5];
		for(int i=0; i<5; i++){
			girls[i] = "G"+i;
		}		
		
		String[] boys =new String[10]; 
		for(int i=0; i<10; i++){
			boys[i] = "B"+i;
		}

		PrintWriter br = new PrintWriter("girlslog.csv", "UTF-8");
		for(int i=0; i<5; i++){
			br.println(girls[i]+","+((int)(Math.random()*1000)%10 +1)+","+ 
			((int)(Math.random()*1000)%100 +1)+","+ ((int)(Math.random()*1000)%5 +1));
		}
		br.close();
	
		br = new PrintWriter("boyslog.csv", "UTF-8");
                for(int i=0; i<10; i++){
                        br.println(boys[i]+","+((int)(Math.random()*1000)%10 +1) +","+
                        ((int)(Math.random()*1000)%100 +1)+","+ ((int)(Math.random()*1000)%5 +1)+","+((int)(Math.random()*1000)%10 +1) );
                }
                br.close();

	}
}
