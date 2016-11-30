<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.issath.projection.ProjectedData"%>
<%@page import="com.issath.projection.ForecastUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  

<%!  
    // --- String Join Function converts from Java array to javascript string.  
 %>  

<!DOCTYPE html>  
<html>  
<head>  
    <title>Simple JSP Demo</title>  
    <script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>  
</head>  
<body>  

    <script>  
        <%  
           // --- Create two Java Arrays  
            /*  
        	Integer[] arr = {6566000,7560000,9857000,9913000,10880000,13180000,15700000,17090000,19740000,20130000};
        	ArrayList<Integer> users = new ArrayList<Integer>();
			for(double data : arr){
				users.add(data);
			} */
			String label = "Forecast Data";
			String type = request.getParameter("type"); 
			ProjectedData projectedData = null;
			if(type != null && type.equals("1")){
				projectedData = ForecastUtil.type1();
				label = "Forecast Revenue";
			}else{
				projectedData = ForecastUtil.type2();
			}
			double[] x = projectedData.getX();
			double[] y = projectedData.getY();
			double px = projectedData.getPredictX();
			double py = projectedData.getPredictY();

			
			ArrayList<String> days = new ArrayList<String>(); 
        	ArrayList<Double> yaxis = new ArrayList<Double>();

            for(int ix = 0; ix < x.length ; ix++)  
            {  
            	days.add("Day " + (ix+1));  
                yaxis.add(y[ix]);  
            }  
            days.add("Day " + px); 
            yaxis.add(py);
        %>  

       // --- add a comma after each value in the array and convert to javascript string representing an array  
        var xData = [<%= ForecastUtil.join(days, ",") %>];  
        var yData = [<%= ForecastUtil.join(yaxis, ",") %>];  
         
    </script> 
    
    <script>  
window.onload = function() {  
  zingchart.render({
    id: "myChart",
    width: "100%",
    height: 400,
    data: {
      "type": "area",
      "title": {
        "text": "Forecast Data"
      },
      "scale-x": {
        "labels": xData
      },
      "plot": {
        "line-width": 1
      },
      "series": [{
        "values": yData
      }]
    }
  });
};
</script>

  
  <div id="myChart"></div>  
  <br/><br/><br/><br/>
 <h1>Projected Value Day <%=px %> :
 <%NumberFormat formatter = new DecimalFormat("#0.00000");
	String outp = formatter.format(py);
 %>
 <%= outp %></h1>
</body>  
</html>  