import java.util.*;
import java.io.*;
import java.sql.Timestamp;

public class Driver{
	public static void main(String args[]) throws IOException{
		String line;
		int x=0,y=0;
		Boys boys[] = new Boys[10];
		Girls girls[] = new Girls[5];
		BufferedReader bb = new BufferedReader(new FileReader("boyslog.csv"));
		
		while ((line = bb.readLine()) !=null){
			String[] array = line.split(",");
			boys[x] = new Boys(array[0],Integer.parseInt(array[1]),Integer.parseInt(array[2]),
			Integer.parseInt(array[3]),Integer.parseInt(array[4]));/*
			System.out.println(array[0]+Integer.parseInt(array[1])+Integer.parseInt(array[2])+
                        Integer.parseInt(array[3])+Integer.parseInt(array[4]));*/
			x++;
		}
		bb.close();  
		bb = new BufferedReader(new FileReader("girlslog.csv"));
                x=0;
                while ((line = bb.readLine()) !=null){
                        String[] array = line.split(",");
                        girls[x] = new Girls(array[0],Integer.parseInt(array[1]),Integer.parseInt(array[2]),
                        Integer.parseInt(array[3]));
                        x++;
                }
                bb.close();

		PrintWriter br = new PrintWriter("logs.csv","UTF-8");
		
		
		for(x=0; x<5; x++){
			for(y=0; y<10; y++){
				Date date = new Date();
        			br.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
				br.println(girls[x].name+" is trying for "+boys[y].name);
				if(girls[x].attractive >= boys[y].min_attraction && boys[y].status == "single" && girls[x].budget <= boys[y].budget){
					date = new Date();
                			br.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
					br.println(girls[x].name+" matched successfully and now coupled with "+boys[y].name);
					boys[y].status = "Coupled";
					girls[x].bf = boys[y].name;
					break;
				}
			}
		}
		br.close();
	}
}
