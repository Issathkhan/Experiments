//$Id$
package com.issath.projection;

import java.util.ArrayList;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

public class ForecastUtil {

	public static ProjectedData type1() {
		double[]  y = {6566000,7560000,9857000,9913000,10880000,13180000,15700000,17090000,19740000,20130000};
		double[] x = new double[y.length];
 		for(int ix = 0; ix<y.length;ix++){
			x[ix] = ix;
		}
 		return PolynomialFitComputation.curveFitting(x, y, y.length + 5);
	}
	
	public static ProjectedData type2() {
		double[] coeff = {5,1,18};
		PolynomialFunction fun = new PolynomialFunction(coeff);
		double[] x = new double[100];
		double[] y = new double[100];
		for(int i = 0 ; i < 100; i++){
			x[i] = i;
			y[i] = fun.value(x[i]);
		}
		double target = 110;
//		System.out.println(fun.value(target)+"--------");
		return PolynomialFitComputation.curveFitting(x, y, target);
	}
	
	   public static String join(ArrayList<?> arr, String del)  
	    {  

	        StringBuilder output = new StringBuilder();  

	        for (int i = 0; i < arr.size(); i++)  
	        {  

	            if (i > 0) output.append(del);  

	              // --- Quote strings, only, for JS syntax  
	              if (arr.get(i) instanceof String)
	            	  output.append("\"");  
	              output.append(arr.get(i));  
	              if (arr.get(i) instanceof String) 
	            	  output.append("\"");  
	        }  

	        return output.toString();  
	    }  

	
	public static void main(String[] args) {
		ProjectedData projData = ForecastUtil.type1();
		System.out.println(projData.getPredictY()+"=======");
	}

}
