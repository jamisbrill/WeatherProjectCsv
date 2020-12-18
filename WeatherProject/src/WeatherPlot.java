import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class WeatherPlot {


	WeatherGraph g;

	public static void plot(double year[],double month[], double tmax[], double tmin[], double af[], double rain[], double sun[])   {
	
		int index = 0;
		
		
		
		BufferedReader file = null;
		try {
			//alternative file location
		//	file = new BufferedReader(new FileReader("H:\\Eclipse_Oxygen\\Myworkspace\\WeatherProject\\shefield.csv"));
		file = new BufferedReader(new FileReader("A:\\Eclipse 2.0\\WeatherProject\\shefield.csv"));
			String line="";
			
		

			// parse the data into arrays using the splits

			while((line = file.readLine()) !=null) {

				String[] splits = line.split(",");
				year[index] = Double.parseDouble(splits[0]);
				month[index] = Double.parseDouble(splits[1]);
				tmax[index] = Double.parseDouble(splits[2]);
				tmin[index] = Double.parseDouble(splits[3]);
				af[index] = Double.parseDouble(splits[4]);
				rain[index] = Double.parseDouble(splits[5]);
				sun[index] = Double.parseDouble(splits[6]);
				
				
				index++;
				
			}
			System.out.println("index is " +index);
		}
			
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		
				}
		// check the tmax values to see if it is reading in the values correctly 
	//	for (int x5 = 0; x5 <67; x5++) {
	//		System.out.println("1tmax is "+tmax[x5]+" x5 is"+x5);
	//	}


	}	
	
		public static void tmaxMethod(double year[],double month[], double tmax[]) {

//	initialising some variables to be used for looping
		int index2;
		double tmaxAvg = 0;	
		double[] tmaxAvgAry = new double[67]; 
		int i4 = 0;
		double tmaxSum =0;
		int x=0;
		int loopcount = 67;
		index2 = 67;

		
//start of the tmax loop to get the average from the array that has been created		
	for(x=0; x<loopcount; x++) {
		tmaxSum=0;
		for(i4 = x*12; i4 <(x*12)+12  ; i4++) {
			tmaxSum = tmaxSum + tmax[i4];
			
		}
		
		tmaxAvg = tmaxSum / 12; 
		// debug code
		// System.out.println("Average is:"+ tmaxAvg);
		tmaxAvgAry[x] = tmaxAvg;

	}
	
	
	//Calculating the largest tmax value 
	double[] tmaxMaxAry = new double[index2];
	double tmaxMax = 0;
	int counter2=12;
	int ix=0;
	
		for(int counter=0; counter < 805; counter++) {
			
			if (tmax[counter] > tmaxMax) {
				tmaxMax = tmax[counter];			
				}
			
		if (counter >= counter2 ) {
			
			tmaxMaxAry[ix] = tmaxMax;
			ix++;
			counter2= counter2 +12;
			tmaxMax=0;
			counter--;
			}
		}

		
	//	for (int x5 = 0; x5 <67; x5++) {
			//debug to display the arrays output in the console 
	//		System.out.println("tmaxMaxarray is"+tmaxMaxAry[x5]);
	//	}
		
		
		//calculating tmax-min 
		double[] tmaxMinAry = new double[67];
		double tmaxMin = 100;
		counter2=11;
		
		ix=0;
		
			for(int counter=1; counter < 805; counter++) {
				
				if (tmax[counter] < tmaxMin) {
										
					tmaxMin = tmax[counter];
									

					}
				
			if (counter >= counter2 ) {
				tmaxMinAry[ix] = tmaxMin;
				ix++;
				counter2= counter2 +12;
				tmaxMin=100;

				}
				
			}
	
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("tmax Minarray is"+tmaxMinAry[x5]+"   x5 is"+x5);
		}
		
		
		
		
		
		
		//plotting the data 

			
			WeatherGraph g;
			g = new WeatherGraph("x", "y", "Weather data",3);
			for(int i = 0; i <66; i++) {
		
			//			System.out.println("tmax avg and tmax ary is "+ tmaxAvgAry[i]+tmaxMaxAry[i]);
				g.add(year[i*12], (tmaxAvgAry[i]), 1);
				g.add(year[i*12], tmaxMaxAry[i],2);
				g.add(year[i*12], (tmaxMinAry[i]),3);

				}
					
				

			
				g.showGraph();
			
			
			
			
			
			
			
			
		}












		//return values for the Gui 


	//public double[] getTmaxAvg(double[] tmaxAvgAry) {
	//return tmaxAvgAry;
//	}

	public double[] getTmaxMax(double[] tmaxMaxAry) {
	return tmaxMaxAry;
	}

	public double[] getTmaxMin(double[] tmaxMinAry) {
		return tmaxMinAry;
	}






	public static void tminMethod(double year[], double month[], double tmin[]) {
			
	// calculating average of tmin 
	
	double tminAvg = 0;
	double tminSum = 0;
	int counter2=12;
	double[] tminAvgAry = new double[67];
	int x=0;
	int ix=0;
	int i4 = 0;
	int loopcount2 = tmin.length/12;

	for(x=0; x<loopcount2; x++) {
		tminSum=0;
		
	for(i4 = x*12; i4 <(x*12)+12  ; i4++) {
			tminSum = tminSum + tmin[i4];

		}
		tminAvg = tminSum /12;
		tminAvgAry[x] = tminAvg;
	}
	

	
	
	// calculating tmin max 
	
	x=0;
	double[] tminMaxAry = new double[67];
	double tminmax = 0;
	counter2=12;
	ix=0;

	
	
		for(int counter=0; counter < 805; counter++) {
			
			if (tmin[counter] > tminmax) {
				
				
				tminmax = tmin[counter];
								

				}
			
		if (counter >= counter2 ) {
			
			tminMaxAry[ix] = tminmax;
			ix++;
			counter2= counter2 +12;
			tminmax=0;
			counter--;
			
			

			}
			
			
		}

		
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("tmin maxary is"+tminMaxAry[x5]);
		}
		
	
	// calculating tmin min 
		x=0;
		i4 = 0;

		double[] tminMinAry = new double[67];
		double tminMin = 100;
		counter2=11;
		
		ix=0;

		
			for(int counter=1; counter < 805; counter++) {
				
				if (tmin[counter] < tminMin) {
										
					tminMin = tmin[counter];
									

					}
				
			if (counter >= counter2 ) {
				tminMinAry[ix] = tminMin;
				ix++;
				counter2= counter2 +12;
				tminMin=100;
				
				

				}
				
				
			}



	
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("tmin min is"+tminMinAry[x5]+"   x5 is"+x5);
		}

	//plotting the tmin values
		WeatherGraph g;
		
		g = new WeatherGraph("x", "y", "Weather data",3);
		for(int i = 0; i <66; i++) {
	
		//			System.out.println("tmax avg and tmax ary is "+ tmaxAvgAry[i]+tmaxMaxAry[i]);
			g.add(year[i*12], (tminAvgAry[i]), 1);
			g.add(year[i*12], tminMaxAry[i],2);
			g.add(year[i*12], (tminMinAry[i]),3);

			}
				
			

		
			g.showGraph();

		
		
		
		
	}
	//return values for the Gui 

	public double[] getTminAvg(double[] tminAvgAry) {
		return tminAvgAry;
		}

		public double[] getTminMax(double[] tminMaxAry) {
		return tminMaxAry;
		}

		public double[] getTminMin(double[] tminMinAry) {
			return tminMinAry;
		}
		
	
	
	
	public static void afMethod (double year[],double month[], double af[]) {
	
	//Air frost data manipulation 
	
	
	
	double afAvg = 0;
	double afSum = 0;
	
	double[] afAvgAry = new double[67];
	int x=0;
	int	i4 = 0;
	int loopcount3 = af.length/12;

	for(x=0; x<loopcount3; x++) {
		afSum=0;
		
	for(i4 = x*12; i4 <(x*12)+12  ; i4++) {
			afSum = afSum + af[i4];

		}
		afAvg = afSum /12;
		afAvgAry[x] = afAvg;
	}
	
	
	 
	
	// calculating air frost  max 

	x=0;

	double[] afMaxAry = new double[67];
	double afMax = 0;
	int counter2=12;
	int ix=0;

	
	
		for(int counter=0; counter < 805; counter++) {
			
			if (af[counter] > afMax) {
				
								
				afMax = af[counter];
								

				}
			
		if (counter >= counter2 ) {
			
			afMaxAry[ix] = afMax;
			ix++;
			counter2= counter2 +12;
			afMax=0;
			counter--;
			
			

			}
			
			
		}
		
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("afMaxarray is"+afMaxAry[x5]);
		}
	
	
	
	
	// calculating air frost minimum 
		

	
		x=0;
		i4 = 0;

		double[] afMinAry = new double[700];
		double afMin = 100;
		counter2=11;
		
		ix=0;

		
			for(int counter=1; counter < 805; counter++) {

				if (af[counter] < afMin) {
					
					afMin = af[counter];
									

					}
				
			if (counter >= counter2 ) {
				afMinAry[ix] = afMin;
				ix++;
				counter2= counter2 +12;
				afMin=100;
				
				

				}
				
				
			}
			
			for (int x5 = 0; x5 <67; x5++) {
				System.out.println("af  min is"+afMinAry[x5]+"   x5 is"+x5);
	}
			

			//plotting the data 


			WeatherGraph g;
			g = new WeatherGraph("x", "y", "Weather data",3);
			for(int i = 0; i <66; i++) {
		
			//			System.out.println("tmax avg and tmax ary is "+ tmaxAvgAry[i]+tmaxMaxAry[i]);
				g.add(year[i*12], (afAvgAry[i]), 1);
				g.add(year[i*12], afMaxAry[i],2);
				g.add(year[i*12], (afMinAry[i]),3);

				}
					
				

			
				g.showGraph();

	
		}
	
	//return values for the Gui 

	
	public double[] getafAvg(double[] afAvgAry) {
		return afAvgAry;
		}

		public double[] getafMax(double[] afMaxAry) {
		return afMaxAry;
		}

		public double[] getafMin(double[] afMinAry) {
			return afMinAry;
		}
	
	
	
	
	
	public static void rainMethod(double year[],double month[],double rain[]) {
	

	// rain data manipulation
	


	double rainAvg = 0;
	double rainSum = 0;
	
	double[] rainAvgAry = new double[67];
	int x=0;
	int i4 = 0;
	int loopcount4 = rain.length/12;

	for(x=0; x<loopcount4; x++) {
		rainSum=0;
		
	for(i4 = x*12; i4 <(x*12)+12  ; i4++) {
			rainSum = rainSum + rain[i4];
		//	System.out.println(x+"rainSum is :"+rainSum);

		}
		rainAvg = rainSum /12;
		rainAvgAry[x] = rainAvg;
	}
	
	//calculating rain max 

	double[] rainMaxAry = new double[67];
	double rainMax = 0;
	int counter2=12;
	int ix=0;

	
	
		for(int counter=0; counter < 805; counter++) {
			
			if (rain[counter] > rainMax) {
				
								
				rainMax = rain[counter];
								

				}
			
		if (counter >= counter2 ) {
			
			rainMaxAry[ix] = rainMax;
			ix++;
			counter2= counter2 +12;
			rainMax=0;
			counter--;
			
			

			}
			
			
		}

		
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("rainMaxarray is"+rainMaxAry[x5]);
		}
	
	
	
	// calculating rain minimum 
		x=0;
		i4 = 0;

		double[] rainMinAry = new double[67];
		double rainMin = 100;
		counter2=11;
		
		ix=0;

		
		System.out.println("***************THE rain MIN HAS STATED ****************");
			for(int counter=1; counter < 805; counter++) {
				
				if (rain[counter] < rainMin) {
										
					rainMin = rain[counter];
									

					}
				
			if (counter >= counter2 ) {
				rainMinAry[ix] = rainMin;
				ix++;
				counter2= counter2 +12;
				rainMin=100;
			//	counter--;
				
				

				}
				
				
			}



	
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("rain min is"+rainMinAry[x5]+"   x5 is"+x5);
		}
		
		//plotting the data 

		WeatherGraph g;
		g = new WeatherGraph("x", "y", "Weather data",3);
		for(int i = 0; i <66; i++) {
	
		//			System.out.println("tmax avg and tmax ary is "+ tmaxAvgAry[i]+tmaxMaxAry[i]);
			g.add(year[i*12], (rainAvgAry[i]), 1);
			g.add(year[i*12], rainMaxAry[i],2);
			g.add(year[i*12], (rainMinAry[i]),3);

			}
				
			

		
			g.showGraph();

	
	}
	
	
	
	
	
	//return values for the Gui 

	
	public double[] getrainAvg(double[] rainAvgAry) {
		return rainAvgAry;
		}

		public double[] getrainMax(double[] rainMaxAry) {
		return rainMaxAry;
		}

		public double[] getrainMin(double[] rainMinAry) {
			return rainMinAry;
		}
	
	
	
	
	
	
	
	
	
	
	public static void sunMethod(double year[],double month[],double sun[]) {
	//Manipulation of data for sun
	
	double sunAvg = 0;
	double sunSum = 0;
	
	double[] sunAvgAry = new double[67];
	int x=0;
	int i4 = 0;
	int loopcount5 = sun.length/12;

	for(x=0; x<loopcount5; x++) {
		sunSum=0;
		
	for(i4 = x*12; i4 <(x*12)+12  ; i4++) {
			sunSum = sunSum + sun[i4];
			//System.out.println(x+"sunSum is :"+sunSum);

		}
		sunAvg = sunSum /12;
		sunAvgAry[x] = sunAvg;
	}
	
	// calculating the min for sun 
	x=0;
	i4 = 0;

	double[] sunMinAry = new double[67];
	double sunMin = 100;
	int counter2=11;
	int ix=0;

	
		for(int counter=1; counter < 805; counter++) {
			
			if (sun[counter] < sunMin) {
									
				sunMin = sun[counter];
								

				}
			
		if (counter >= counter2 ) {
			sunMinAry[ix] = sunMin;
			ix++;
			counter2= counter2 +12;
			sunMin=100;
		//	counter--;
			
			

			}
			
			
		}




	for (int x5 = 0; x5 <67; x5++) {
		System.out.println("sun min is"+sunMinAry[x5]+"   x5 is"+x5);
	}

	// calculating sun max 

	double[] sunMaxAry = new double[67];
	double sunMax = 0;
	counter2=12;
	
	ix=0;

	
	
		for(int counter=0; counter < 805; counter++) {
			
			if (sun[counter] > sunMax) {
				
								
				sunMax = sun[counter];
								

				}
			
		if (counter >= counter2 ) {
			
			sunMaxAry[ix] = sunMax;
			ix++;
			counter2= counter2 +12;
			sunMax=0;
			counter--;
			
			

			}
			
			
		}
		//plotting the data 
		
		for (int x5 = 0; x5 <67; x5++) {
			System.out.println("sunMaxarray is"+sunMaxAry[x5]);
		}
		WeatherGraph g;
		g = new WeatherGraph("x", "y", "Weather data",3);
		for(int i = 0; i <66; i++) {
	
			g.add(year[i*12], (sunAvgAry[i]), 1);
			g.add(year[i*12], sunMaxAry[i],2);
			g.add(year[i*12], (sunMinAry[i]),3);

			}
				
			

		
			g.showGraph();

	

	
	}
	//return values for the Gui 

	
	public double[] getsunAvg(double[] sunAvgAry) {
		return sunAvgAry;
		}

		public double[] getsunMax(double[] sunMaxAry) {
		return sunMaxAry;
		}

		public double[] getsunMin(double[] sunMinAry) {
			return sunMinAry;
		}




	
	}