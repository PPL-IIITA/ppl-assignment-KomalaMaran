import java.util.*;
import java.io.*;
import java.sql.Timestamp;

public class Coupler{
	public static void main(String args[]) throws IOException{
		Boys boys[] = new Boys[10];
		Girls girls[] = new Girls[5];
		BufferedReader wbr = new BufferedReader(new FileReader("boyslog.csv"));
		String line;
		int x=0,y=0;
		int q;
		System.out.println("Enter the value of k");
		Scanner scan = new Scanner(System.in);
		q =  scan.nextInt();
		while ((line = wbr.readLine()) !=null){
			String[] array = line.split(",");
			boys[x] = new Boys(array[0],Integer.parseInt(array[1]),Integer.parseInt(array[2]),
			Integer.parseInt(array[3]),Integer.parseInt(array[4]));
			x++;
		}
		wbr.close();  
		wbr = new BufferedReader(new FileReader("girlslog.csv"));
                x=0;
                while ((line = wbr.readLine()) !=null){
                        String[] array = line.split(",");
                        girls[x] = new Girls(array[0],Integer.parseInt(array[1]),Integer.parseInt(array[2]),
                        Integer.parseInt(array[3]));
                        x++;
                }
                wbr.close();
		PrintWriter csvwrite = new PrintWriter("loggs.csv","UTF-8");
		
		int k=0;
		Couples couples[] = new Couples[15];
		for(x=0; x<5; x++){
			for(y=0; y<10; y++){
				Date date = new Date();
        			csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
				csvwrite.println(girls[x].name+" is checking out "+boys[y].name);
				if(boys[y].status == "single" && girls[x].budget <= boys[y].budget && 
				girls[x].attractive >= boys[y].min_attraction ){
					couples[k]= new Couples(boys[y],girls[x]);
					k++;
					boys[y].status = "InRelationship";
					girls[x].bf = boys[y].name;
					date = new Date();
                			csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
					csvwrite.println(girls[x].name+" checked out successfully and now is in relation with "+boys[y].name);
					break;
				}
			}
		}
		csvwrite.close();

//Compatibility printing and sorting issue
		
		SortCouple sortcouple= new SortCouple(couples,k);
		if(q>k)
			q=k;
		for(x=0; x<q; x++){
			if(x==0)
				System.out.println("\nCouples by Compatibility:\n");
                        System.out.println(couples[x].boy.name + " in relation with " + couples[x].girl.name + " with compatibility: " 
			+ couples[x].compatibility);
                }


//Gifting printing and sorting issue

                Gifts gifts[] = new Gifts[100];
                for(x=0; x<100; x++){
                        gifts[x] = new Gifts((int)(Math.random()*3),(int)(Math.random()*10 + 1),
                        (int)(Math.random()*10 + 1),(int)(Math.random()*100 + 1),(int)(Math.random()*100 + 1));
                }
		for(x=0; x<100; x++){
                        for(y=0; y<99; y++){
                                if(gifts[y].price > gifts[y+1].price){
                                        Gifts temp;
                                        temp = gifts[y];
                                       	gifts[y]=gifts[y+1];
                                        gifts[y+1]=temp;
                                }
                        }
                }

		csvwrite = new PrintWriter("logs_2.csv","UTF-8");
		Getname getname = new Getname();
		for(x=0; x<k; x++){
			if(couples[x].boy.type==0){
				int l=0;
				while(couples[x].boy.spent <= couples[x].girl.budget){
					if(couples[x].boy.spent + gifts[l].price > couples[x].boy.budget)
						break;
					couples[x].gifts[l]= gifts[l];
					couples[x].boy.spent += gifts[l].price;
					Date date = new Date();
        				csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
					csvwrite.println(couples[x].boy.name + " gifted " + getname.get(gifts[l].type) + " to " + 
					 couples[x].girl.name);
					l++;
					couples[x].no_gifts = l;
				}
			}
			else if(couples[x].boy.type == 1){
				int l=0;
				while(couples[x].boy.spent + gifts[l].price <= couples[x].boy.budget){
					couples[x].gifts[l]= gifts[l];
                                        couples[x].boy.spent += gifts[l].price;
					Date date = new Date();
        				csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
					csvwrite.println(couples[x].boy.name + " gifted " + getname.get(gifts[l].type) + " to " + 
					 couples[x].girl.name);
                                        l++;
					couples[x].no_gifts = l;
				}
			}
			else{
				int l=0;
                                while(couples[x].boy.spent <= couples[x].girl.budget){
                                        if(couples[x].boy.spent + gifts[l].price > couples[x].boy.budget)
                                                break;
                                        couples[x].gifts[l]= gifts[l];
                                        couples[x].boy.spent += gifts[l].price;
					Date date = new Date();
        				csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
					csvwrite.println(couples[x].boy.name + " gifted " + getname.get(gifts[l].type) + " to " + 
					 couples[x].girl.name);
                                        l++;
					couples[x].no_gifts = l;
                                }
				int p=l;
				while(l<= gifts.length){
					if(couples[x].boy.spent + gifts[l].price > couples[x].boy.budget)
						break;
					if(gifts[l].type==1){
						couples[x].gifts[p]= gifts[l];
						couples[x].no_gifts = p+1;
                                        	couples[x].boy.spent += gifts[l].price;
						Date date = new Date();
						csvwrite.print("Timestamp = " + new Timestamp(date.getTime())+ " : ");
						csvwrite.println(couples[x].boy.name + " gifted " + getname.get(gifts[l].type) + " to " + 
						 couples[x].girl.name);
						csvwrite.println("luxury gift given budget allowed");
						break;
					}
					l++;
				}
			}
		}
		csvwrite.close();

//Happiness calculation
		for(x=0; x<k ; x++){
			if(couples[x].boy.type == 0){
				couples[x].happiness =  couples[x].boy.budget - couples[x].boy.spent;
				
				if(couples[x].girl.type == 0){
					couples[x].happiness += (int)Math.log(couples[x].boy.spent);
					
					for(int l=0; l<couples[x].no_gifts; l++){
						if(couples[x].gifts[l].type == 1)
							couples[x].happiness += couples[x].gifts[l].price;
					}
				}
				else if(couples[x].girl.type == 1){
                                        couples[x].happiness += couples[x].boy.spent;
					
                                        for(int l=0; l<couples[x].no_gifts; l++)
                                        	couples[x].happiness += couples[x].gifts[l].value;
                                }
				else
					couples[x].happiness += (int)Math.exp(couples[x].boy.spent);
	
			}
			else if(couples[x].boy.type == 1){
                                if(couples[x].girl.type == 0){
                                        couples[x].happiness += (int)Math.log(couples[x].boy.spent);
                                        for(int l=0; l<couples[x].no_gifts; l++){
                                                if(couples[x].gifts[l].type == 1)
                                                        couples[x].happiness += couples[x].gifts[l].price;
                                        }
                                }
                                else if(couples[x].girl.type == 1){
                                        couples[x].happiness += couples[x].boy.spent;
                                        for(int l=0; l<couples[x].no_gifts; l++)
                                                couples[x].happiness += couples[x].gifts[l].value;
                                }
                                else
                                        couples[x].happiness += (int)Math.exp(couples[x].boy.spent);
				couples[x].happiness = 2*couples[x].happiness;
                	}
			else{
                                couples[x].happiness =  couples[x].girl.intelligence;
                                if(couples[x].girl.type == 0){
                                        couples[x].happiness += (int)Math.log(couples[x].boy.spent);
                                        for(int l=0; l<couples[x].no_gifts; l++){
                                                if(couples[x].gifts[l].type == 1)
                                                        couples[x].happiness += couples[x].gifts[l].price;
                                        }
                                }
                                else if(couples[x].girl.type == 1){
                                        couples[x].happiness += couples[x].boy.spent;
					for(int l=0; l<couples[x].no_gifts; l++)
                                                couples[x].happiness += couples[x].gifts[l].value;
                                }
                                else
                                        couples[x].happiness += (int)Math.exp(couples[x].boy.spent);
                        }

		}
		for(x=0; x<k; x++){
                        for(y=0; y<k-1; y++){
                                if(couples[y].happiness < couples[y+1].happiness){
                                        Couples temp;
                                        temp = couples[y];      
                                        couples[y]=couples[y+1];
                                        couples[y+1]=temp;
                                }
                        }
                }

		for(x=0; x<q; x++){
			if(x==0)
				System.out.println("\nCouples by Happiness:\n");
                        System.out.println(couples[x].boy.name + " " + couples[x].girl.name + 
			" happiness = " + couples[x].happiness);
                }

	}
}
