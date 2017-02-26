public class Gifts{
	int type;
	int price;
	int value;
	int luxuaryrating;
	int luxuarydiffi;
	int utilityvalue;
	int utilityclass;

	public Gifts(int a, int b, int c, int d , int e){
                type = a;
                price =b;
                value = c;
		if(a==1){
                	luxuaryrating = d;
                	luxuarydiffi= e;
                	utilityclass= 0;
                	utilityvalue = 0;
		}
		else if(a==2){
                        luxuaryrating = 0;
                        luxuarydiffi= 0;
                        utilityvalue= d;
                        utilityclass= e;
                }
		else{
			luxuaryrating = 0;
                        luxuarydiffi= 0;
                        utilityvalue= 0;                        
                        utilityclass= 0;
		}

        }
	

}


