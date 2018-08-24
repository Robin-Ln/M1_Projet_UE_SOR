<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<div class="section">
		<div id="chartjs" class="section">
			<h4 class="header">Line Chart</h4>
			<div class="row">
				<div class="col s12">				
					<form class="col s12" method="post" action="ServletStatistique" id="stat">
						<div class="row">
							<div class="input-field col s6">
								<select name="annee">
									<option value="" selected></option>
									<c:forEach var="a" items="${annees}">
										<option value="${a.toString()}">${a.toString()}</option>
									</c:forEach>															
								</select> 
								<label>Année</label>
								<button class="btn waves-effect waves-light" type="submit"	name="actualiserStat">
									ok <i class="material-icons right">send</i>
								</button>
									
							</div>
								
						</div>
					</form>
					
				</div>
				<div>
					
				</div>
				<div class="col s8 ">
					<div class="sample-chart-wrapper">
						<canvas id="min" height="740" width="1854"></canvas>
					</div>
				</div>
				<div class="col s8 ">
					<div class="sample-chart-wrapper">
						<canvas id="max" height="740" width="1854"></canvas>
					</div>
				</div>
				<div class="col s8 ">
					<div class="sample-chart-wrapper">
						<canvas id="moy" height="740" width="1854"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>    
	
	<script>		
		var ctx1 = document.getElementById("min").getContext('2d');
		var min="${min}";
		var minimum = new Chart(ctx1, {
			type : 'line',
			data : {
				labels : [ "Janvier", "Fevrier", "Mars", "Avril", "Mai",
						"Juin", "Juillet", "Aout", "Septembre", "Octobre",
						"Novembre", "Decembre" ],
				datasets : [ {
					label : 'Temperature minimum',
					data : [${min}],
					backgroundColor : [ 'rgba(0, 51, 204, 0.2)'],
					borderColor : [ 'rgba(0, 102, 255,1)'],
					borderWidth : 1
				} ]				
			}
		});
		
		var ctx2 = document.getElementById("max").getContext('2d');
		var max="${max}";
		var maximum = new Chart(ctx2, {
			type : 'line',
			data : {
				labels : [ "Janvier", "Fevrier", "Mars", "Avril", "Mai",
						"Juin", "Juillet", "Aout", "Septembre", "Octobre",
						"Novembre", "Decembre" ],
				datasets : [ {
					label : 'Temperature maximum',
					data : [${max}],
					backgroundColor : [ 'rgba(167, 0, 0, 0.2)'],
					borderColor : [ 'rgba(143, 0, 0,1)'],
					borderWidth : 1
				} ]				
			}
		});
		
		var ctx3 = document.getElementById("moy").getContext('2d');
		var moy="${moy}";
		var moyenne = new Chart(ctx3, {
			type : 'line',
			data : {
				labels : [ "Janvier", "Fevrier", "Mars", "Avril", "Mai",
						"Juin", "Juillet", "Aout", "Septembre", "Octobre",
						"Novembre", "Decembre" ],
				datasets : [ {
					label : 'temperature moyenne',
					data : [${moy}],
					backgroundColor : [ 'rgba(102, 255, 102, 0.2)'],
					borderColor : [ 'rgba(0, 153, 0,1)'],
					borderWidth : 1
				} ]				
			}
		});
		
	</script>