//Wachirawit Peerapisarnpon 6213145
//Winn Ladawuthiphan 6213146



import java.io.*;
import java.util.*;

class Product implements Comparable<Product>
{
        private final int price;
        private final int weight;
        private final String name ;
        private int totalSaleInCash;
        private int  totalSaleInUnit;
                      
        public int getWeight()
	{
            return weight;
        }              
        public int getPrice()
        {
            return price;
        }
        public String getName()
	{
            return name;
        }        
	public void calculator(int unit)
	{
            totalSaleInCash += unit*price;
            totalSaleInUnit += unit;            
        }
	public void print()
	{
            System.out.printf("%-18s  total sales = %6d B, %3d Units\n",name,totalSaleInCash,totalSaleInUnit);
        }
	public Product(String n, int b, int c)
	{
            name = n ;
            price = b;
            weight = c;
        }

        @Override
        public int  compareTo(Product other)
	{          
            if(this.totalSaleInCash>other.totalSaleInCash) return -1;
            else if(this.totalSaleInCash<other.totalSaleInCash)return 1;
            if(this.totalSaleInUnit>other.totalSaleInUnit) return -1;
            else if(this.totalSaleInUnit<other.totalSaleInUnit)return 1;
            else  return this.name.compareToIgnoreCase(other.name);
        }        
}

class Postage
{
        private final double minWeight;
        private final double maxWeight;
        private final int rate ;
        private final String type ;

        public double getMaxWeight()
	{
            return maxWeight;
        }
        public int getRate()
	{
            return rate;
        }

	public Postage(String n, double min, double max, int rate)
	{
            type = n ;
            minWeight = min;
            maxWeight = max;
            this.rate = rate;
        }
}

class Customer implements Comparable<Customer>
{
        private int orders[] = new int[5] ;
        private final String name;
        private final String type;
        private int totalBill;
        private int totalWeight;
        private int postage;
               
        public int getTotalWeight()
	{
            return totalWeight;
        }
        public String getType()
	{
            return type;
        }
        public int getOrders(int i)
	{
            return orders[i];
        }
        public void setPostage(int i)
	{
            postage = i;
            totalBill += i; 
        }

        public void calculator(int price , int weight)
	{
            totalBill += price;
            totalWeight += weight;            
        }

        public void print(ArrayList<String> item)
	{
            
            System.out.printf("%s  >> %s (%2d)   %s (%2d)  %s (%2d)  %s (%2d)  %s (%2d)     \n",name,item.get(0),orders[0],item.get(1),orders[1],item.get(2),orders[2],item.get(3),orders[3],item.get(4),orders[4]);
            System.out.printf("          Product price  = %5d   total weight = %5d\n",totalBill-postage,totalWeight);
            System.out.printf("          postage (%s)    = %5d   total bill   = %5d\n",type,postage,totalBill);
                }

        public void printSortCustomer()
	{            
            System.out.printf("%s   bill = %5d\n",name,totalBill);
        }
	
	public Customer(String n, String t, int o[])
	{
            name = n;
            type = t ;
            orders = o;
        }

        @Override
        public int  compareTo(Customer other)
	{          
            if(this.totalBill>other.totalBill) return -1;
            else if(this.totalBill<other.totalBill)return 1;
            else  return this.name.compareToIgnoreCase(other.name);
        }
}

public class OnlineShop 
{
        public static void throwNumberError(int n)throws Exception
        {         
            if(n<0)
                throw new Exception("Number Error");                           
        }
        
        /*public static int[] positiveCutError(int[] n) {
            
           for(int i=0;i<n.length;i++){
             
                 if(n[i]<0){
                   n[i]=0;
                 }
           }
            return n;
        }*/
        
        public static void checkType(String s)throws Exception
        {
            if(!(s.equals("E")||s.equals("R")))
            {
                throw new Exception("Postage Error");
            }            
        }
        
        public static String changeType(String s)
        {
            if(s.equals("e")||s.equals("E"))
                return "E";
            else
                return "R";
        }
       
        public static String changeUI(String text)
        {            
            char []t = new char[text.length()];           
            text.getChars(0, text.length(), t, 0);
           
            for(int i=0;i<t.length;i++)
            {
                if(t[i]=='-')
                {
                    return "0";
                }
                switch (t[i])
                {
                    case 'S' :  t[i] = '5'; break;
                    case 's' :  t[i] = '5'; break;
                    case 'l' :  t[i] = '1'; break; 
                    case 'O' :  t[i] = '0'; break;
                    case 'o' :  t[i] = '0'; break;  
                } 
                if(!((int)t[i]>=(int)'0'&&(int)t[i]<=(int)'9'))
                {
                      t[i] = '0';
                }
            }
            
            text = new String(t);             
            return text ;            
        }
                
    	public static void main(String args[]) 
	{        
        	ArrayList<Customer> cus = new ArrayList();
        	ArrayList<Product> Pro = new ArrayList();
                
       		Postage []Pos = new Postage[10];
                
        	ArrayList<String> item = new ArrayList();
                
        	boolean allopen = false,open1 = false,open2 = false;
        	while(!allopen)
		{
            
            		try
			{
                		if(!open1)
				{
                			System.out.println("Enter file name for products");
                			Scanner fname = new Scanner(System.in);
                			String fn = fname.nextLine();
                			Scanner input1 = new Scanner(new File(fn));
                                        //Scanner input1 = new Scanner(new File("products.txt"));
                                       
                    
                			int i=0;
                			open1 = true;
                			while(input1.hasNext())
                   			{  
                        			String line  = input1.nextLine();
                       				String []buf = line.split(",");  
                        			String n     = buf[0].trim();
                        			int price    = Integer.parseInt(buf[1].trim());   
                        			int  w       = Integer.parseInt(buf[2].trim());
                        			Pro.add(new Product(n, price, w)) ;
                        			item.add(n);
                        			i++;
                    			}

                			input1.close();
                			System.out.println("");
                		}
                
                		if(!open2)
				{
                   		 	int i=0;
                    			System.out.println("Enter file name for rates");
                    			Scanner fname = new Scanner(System.in);
                    			String fn = fname.nextLine();                    
                    			Scanner input2 = new Scanner(new File(fn));
                                        //Scanner input2 = new Scanner(new File("postages.txt"));

                    			open2=true;
                    			while(input2.hasNext())
                       			{
                            			String line  = input2.nextLine();
                            			String []buf = line.split(",");  
                           			String n     = buf[0].trim();
                            			double min      = Double.parseDouble(buf[1].trim()); 
                           			double max;
		
                            			if("inf".equals(buf[2].trim()))
						{
                                			max = Double.POSITIVE_INFINITY;                               
                            			}
                            			else
                                			max      = Double.parseDouble(buf[2].trim());

                            			int rate     = Integer.parseInt(buf[3].trim());
                            			Pos[i]  = new Postage(n, min, max , rate);
                                                i++;
                    			}
                    			input2.close();
                   			System.out.println("");
                		}
                
                		
                		System.out.println("Enter file name for orders");
                		Scanner fname = new Scanner(System.in);
                		String fn = fname.nextLine();
                
                		Scanner input = new Scanner(new File(fn));
                               // Scanner input = new Scanner(new File("wrongcustomers.txt"));
                		allopen = true ;
                                
                 		while(input.hasNext())
                    		{
                                    String line  =  input.nextLine();                                    
                                    
                                    String []buf =  line.split(",");  
                                    int range = buf.length;
                                    
                                    String n    =  buf[0].trim();
                                    String t    =  buf[1].trim();                                    
                                        
                                    try
                                    {                                        

                                        if(range>7)//Check Out of Range Exception
                                        {
                                            range = 7;
                                            throwNumberError(-1);
                                        }  
                                        checkType(t);//Check Type Exception
                            		int o1       =  Integer.parseInt(buf[2].trim());//Catch if can't convert to int
                                        throwNumberError(o1);//Check accuracy of data
                		        int o2       =  Integer.parseInt(buf[3].trim()); 
                                        throwNumberError(o2);
                            		int o3       =  Integer.parseInt(buf[4].trim()); 
                                        throwNumberError(o3);
                            		int o4       =  Integer.parseInt(buf[5].trim());
                                        throwNumberError(o4);
                            		int o5       =  Integer.parseInt(buf[6].trim());//Catch if buf[6] have not exist
                                        throwNumberError(o5);
                                        int[]o = {o1,o2,o3,o4,o5} ;
                                                                                               
                            		Customer c   = new Customer(n, t, o);
                            		cus.add(c);//add to calculate                            			
                                    }
                                    catch(Exception e)
                                    {
                            		System.out.println("Input error   : "+ line);
                                        
                                        /*if(range>7)//Out of Range Exception
                                            range = 7;     */                                       
                                                                                                                 
                                        t = changeType(t);//Type Exception
                                        
                                        
                                        int[]o =new int[5];
                                        for(int k =2;k<range;k++)//Input wrong data  
                                        {
                                            String s = changeUI(buf[k].trim());                                                   
                                            o[k-2] = Integer.parseInt(s);                                                   
                                        }
                                        
                                        for(int i =range;i<5;i++)//lost data
                                        {
                                            o[i] = 0;
                                        }
                                                
                                        System.out.printf("Correct Input : %s, %s, %d, %d, %d, %d, %d \n", n , t , o[0] , o[1] , o[2] , o[3] , o[4]);
                                                
                                        Customer c   = new Customer(n, t, o);
                            		cus.add(c); //add to calculate
                                    }
                            	}
                 		input.close();
                 		System.out.println(""); 
            		}
            		catch(FileNotFoundException ex)
			{
                            System.out.println(ex);
                        }
        	}
        	System.out.println("----- Process order -----");
        	for(Customer c : cus)
		{
           		int unit; 
           		for(int i = 0; i<5;i++)
			{
                		unit  = c.getOrders(i); 
                		c.calculator(Pro.get(i).getPrice()*unit,Pro.get(i).getWeight()*unit);
                                Pro.get(i).calculator(unit);
           		}           
           		for(int i = 0;i<5;i++)
			{              
                		if("E".equals(c.getType()))
				{
                    			Postage p = Pos[i];
                   		 	if(p.getMaxWeight()>c.getTotalWeight())
					{
                        			c.setPostage(p.getRate());
                        			break;
                    			}
                		}
                    		else if("R".equals(c.getType())) 
				{
                    			Postage p = Pos[i+5];
                    			if(p.getMaxWeight()>c.getTotalWeight())
					{
                        			c.setPostage(p.getRate());
                        			break;
                    			}
               			} 
           		}           
           		c.print(item);
           
        	}
        	System.out.println("");

        	System.out.println("----- Sort customers by total bill -----");
        	Collections.sort(cus);
        	for(Customer c1 : cus)
		{
            		c1.printSortCustomer();        
        	}
        	System.out.println("");

        	System.out.println("----- Sort products by total sale in cash -----");
        	Collections.sort(Pro);
        	for(Product p1 : Pro)
		{
            		p1.print();        
        	}
        	System.out.println("");
    	}
}
//products.txt postages.txt customers.txt wrongcustomers.txt