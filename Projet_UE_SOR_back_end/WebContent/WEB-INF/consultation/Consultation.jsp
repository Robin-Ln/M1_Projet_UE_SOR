<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br>
<form method="post" action="ServletConsultation">
	<div class="row">
		<div class="col s6">
			<select name="mois">
				<option value="0">Janvier</option>
				<option value="1">fevrier</option>
				<option value="2">mars</option>
				<option value="3">avril</option>
				<option value="4">mai</option>
				<option value="5">juin</option>
				<option value="6">juillet</option>
				<option value="7">aout</option>
				<option value="8">septembre</option>
				<option value="9">octobre</option>
				<option value="10">Novembre</option>
				<option value="11">Decembre</option>
			</select> <label>Filtre par mois</label>
		</div>
		<button class="btn waves-effect waves-light" type="submit"
			name="filtrerMois">
			Filtrer <i class="material-icons right">send</i>
		</button>
	</div>
</form>
<form class="col s6" method="post" action="ServletConsultation">
	<div class="row">
		<div class="col s6">
			<select name="jour">
				<c:forEach var="i" begin="1" end="31" step="1">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select> <label>Filtre par années</label>
		</div>
		<button class="btn waves-effect waves-light" type="submit"
			name="filtrerJour">
			Filtrer <i class="material-icons right">send</i>
		</button>
	</div>
</form>

<form class="col s6" method="post" action="ServletConsultation">
	<input type="hidden" value="${ listId }" name="listId">
	<div class="row">
		<button class="btn waves-effect waves-light" type="submit"
			name="genererPdf">
			Générer un Pdf <i class="material-icons right">send</i>
		</button>
	</div>
</form>


<ul class="collection">
	<c:forEach var="entry" items="${mapMeteo}">
		<li class="collection-item avatar">
			<div class="row">
				<div class="col s4">
					<c:if test="${entry.value.isEmpty()}">
						<i class="material-icons circle green">insert_chart</i>
					</c:if>
					<c:if test="${! entry.value.isEmpty()}">
						<div>
							<c:forEach var="image" items="${entry.value}">
								<img src="data:image/png;base64,${ image }" height="100px" />
							</c:forEach>
						</div>
					</c:if>

				</div>
				<div class="col s4">
					<p>
						<span class="title">${ entry.key.lieu }</span> <br>${ entry.key.date }<br>
						${ entry.key.temps }
					</p>
				</div>
				<div class="col s2">
					<p>
						${ entry.key.min }<br> ${ entry.key.max }<br> ${ entry.key.moy }
					</p>
				</div>
				<c:if test="${ manager.identifier }">
					<div class="col s1">
						<form action="ServletEdition" method="post">
							<input type="hidden" value="${entry.key.idMeteo}" name="idMeteo">
							<button type="submit" name="edition">
								<i class="material-icons">edit</i>
							</button>
						</form>
					</div>
					<div class="col s1">
						<form action="ServletConsultation" method="post">
							<input type="hidden" value="${entry.key.idMeteo}" name="idMeteo">
							<button type="submit" name="supprimer">
								<i class="material-icons">delete</i>
							</button>
						</form>
					</div>
				</c:if>
			</div>

		</li>
	</c:forEach>
</ul>





