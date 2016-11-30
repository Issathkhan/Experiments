//$Id$
package com.issath.projection;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class PolynomialFitComputation {
//	static double[] coeff = { 12.9, -3.4, 2.1 }; // 12.9 - 3.4 x + 2.1 x^2 //new double[] { -1e-20, 3e15, -5e25 }
	private static int DEGREE = 2;//predicts upto degree 2

	public static ProjectedData curveFitting( double x[], double y[], double predictX) {
		if(x.length != y.length){
			throw new RuntimeException("x and y points length should be same");
		}
		WeightedObservedPoints obs = new WeightedObservedPoints();
		for (int i = 0; i < x.length; i++){
			obs.add(x[i], y[i]);
		}
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(DEGREE);//.withStartPoint(new double[] { -1e-20, 3e15, -5e25 });
		double[] fit = fitter.fit(obs.toList());
		PolynomialFunction resf = new PolynomialFunction(fit);
//		for(double coeff : fit){
//			System.out.println(coeff);
//		}
//		System.out.println(resf.value(forecast)+"--------");
		double predictY = resf.value(predictX);
		ProjectedData projectedData = new ProjectedData();
		projectedData.setX(x);;
		projectedData.setY(y);
		projectedData.setPredictX(predictX);
		projectedData.setPredictY(predictY);
		return projectedData;
	}
}
