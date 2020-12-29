<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "tp.biblioteque.model.*, java.util.*, java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gestion des Livres</title>
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet"  type="text/css" href="./inc/css/style.css">
</head>
<body>
	<head>
		<ul class="nav justify-content-center head">
			 <li class="nav-item">
			    	<a class="nav-link active" href="accueil">Accueil</a>
			  </li>
			  <li class="nav-item">
			    	<a class="nav-link" href="abonne">Gestion des Adherents</a>
			  </li>
			  <li class="nav-item">
			    	<a class="nav-link" href="livre">Gestion des Livres</a>
			  </li>
		</ul>
	</head>
	
	<main class="container">
		<section class="row">
			<section class ="col-8">
				<div class="alert alert-success" > <h1>Liste des Livres </h1></div>
				<div class="container-lg">
					<table class="table table-dark">
						<thead>
						    <tr>
						      
						      <th scope="col">Titre</th>
						      <th scope="col">Auteur</th>
						      <th scope="col">Date de Parution</th>
						       <th scope="col">Theme</th>
						      <th scope="col">Operations</th>
						      <!-- <th scope="col">Operations</th>    -->
						    </tr>
					  	</thead>
					    <tbody>	        
							<c:forEach var="valeur" items="${requestScope.listeLivres}">
							<!--  declaration des variables pour l affichage des donnes du livre ${auteurs.getPrenomAuteur} && (valeur.getIdAuteur() != 0) -->
								<c:forEach var="val" items="${requestScope.ListeCatalogue}">
									<c:if test="${val.getCodCatalogue() == valeur.getCodCatalogue() }" >
										<c:set var="catalogue" value="${val.getLibelleCatalogue()}" scope="page" /><%-- il fait un erreur Litt?rature--%> 
									</c:if>
								</c:forEach>
								
								<c:choose>
									<c:when test="${valeur.getIdAuteur() != 0}" >	
											<c:forEach var="auteurs" items="${requestScope.listeAuteurs}">
												<c:if test="${auteurs.getIdAuteur() == valeur.getIdAuteur() }" >
													<c:set var="auteur" value="${auteurs.getNomAuteur()} ${auteurs.prenomAuteur}" scope="page" />
												</c:if>
											</c:forEach>
										 
									</c:when>	
									<c:otherwise>
										 	<c:set var="auteur" value="" scope="page" />									  
									</c:otherwise>
								</c:choose>
								<tr>
									
									<td scope="row" ><c:out value="${valeur.getTitre()}" /> </td>
									<td ><c:out value="${auteur}" /> </td>
									<td ><c:out value="${valeur.getDateParution()}" /> </td>
									<td ><c:out value="${catalogue}" /> </td>
									
									<%-- tomcat support pas les [] --%>
									<c:set var="mots" value="${fn:replace(valeur.getListeMotsCles(),'[','')}" scope="page" />
									<c:set var="motscles" value="${fn:replace(mots,']','')}" scope="page" />
									
									<td><a href="<c:url value = "livre?action=update&isbn=${valeur.getIsbn()}&titre=${valeur.getTitre()}&date=${valeur.getDateParution()}&codTheme=${valeur.getCodCatalogue()}&idAuteur=${valeur.getIdAuteur()}&listeMots=${motscles}"/>">Modifier</a> </td>
									<td><a href="<c:url value = "livre?action=delete&id=${valeur.getIsbn()}"/>">Effacer</a> </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
			<section class ="col-4">
				<div class="alert alert-success"><h1>Ajouter un Livre</h1></div>
				<div class="container-lg alert-success">
					<form action="livre" method ="post" accept-charset="UTF-8" >
						 <div class="form-group">
							    <label for="">ISBN : </label>
							    <input type="text" class="form-control" id="" placeholder="" maxlength="10" name="isbn" value="${requestScope.modIsbn}">
						  </div>
						  <div class="form-group">
							    <label for="">Titre : </label>
							    <input type="text" class="form-control" id="" placeholder=""  name="titre" value="${requestScope.modTitre}">
						  </div>						 
						  <div class="form-group">
							    <label for="">Date de Parution : </label>
							    <input type="date" class="form-control" id="" placeholder=""  name="dateParution" value="${requestScope.modDateParution}">
						  </div>	
						  
						  	<%--  <c:if test="${requestScope.modCatalogue == 0 }" > selected  </c:if> --%>						  
						  <div class="form-group">
							    <label for="">Catalogue</label>
							    <select class="form-control" name="codCatalogue" id="">
					    	
							    	<option value= "0" >Choisir</option>
							    	<c:forEach var="valeur" items="${requestScope.ListeCatalogue}">
							    				
											<option value= "${valeur.getCodCatalogue() }" <c:if test="${valeur.getCodCatalogue() == requestScope.modCodCatalogue }" > selected  </c:if> >
													${valeur.getLibelleCatalogue()}
											</option>
										
									</c:forEach>
							    	  
								      
							    </select>						    
						   </div>
						   
						   <div class="form-group">
							    <label for="">Auteur</label>
							    
							    <c:set var="modAuteur" value="${auteurs.getNomAuteur()} ${auteurs.prenomAuteur}" scope="page" />
							    
							    <select class="form-control" name="idAuteur" id="">
							    	
							    	<option value= "0"   >Choisir</option>
							    	<c:forEach var="valores" items="${requestScope.listeAuteurs}">
							    	
							    				
										<option value= "${valores.getIdAuteur() }" <c:if test="${valores.getIdAuteur() == requestScope.modIdAuteur}" > selected  </c:if> >
												${valores.getNomAuteur()}  ${valores.getPrenomAuteur()}
										</option>
										
									</c:forEach>
							    	  							      
							    </select>
						    </div>
						    
						     <div class="form-group">
							    <label for="">Mots Cles Associes</label>
							    
							    <div>
							    <!-- il faut changer, il s affiche tous les mots cles -->
							     	<c:forEach var="valeur" items="${requestScope.listeMotsCles}">
							    			<div class="form-check form-check-inline">
							    			
							    					<c:set var="bandera" value="false" />
												    
												    <c:if test="${not empty requestScope.modlisteMots}">   
											    			<c:forEach var="valor" items="${requestScope.modlisteMots}">
											    					
											    					<c:set var="sans" value="${fn:replace(valor,' ', '')}" />	
											    					<fmt:parseNumber var = "codMot" type = "number" value = "${sans}" />
											    					
																    <c:if test="${valeur.getIdMotCle() == codMot }">  
																        <input id="" value="${codMot}" type="checkbox" name="motscles" checked > &nbsp&nbsp<label for="">${valeur.getLibelleMotCle()}</label>
																    	<c:set var="bandera" value="true" />
																    </c:if>	
																	
															</c:forEach>		
													</c:if>
													
													<%--<c:out value="${bandera}" /> --%>
													
													<c:if test="${bandera == false }" > 
														
														<input id="" value ="${valeur.getIdMotCle()}" name="motscles" type="checkbox" >&nbsp&nbsp<label for="checkbox3">${valeur.getLibelleMotCle()}  
													</c:if>
																						
							    				
											  </div>
											    
											   <%-- 
							    				<a class="btn btn-outline-primary " href="#" role="button">${valeur.getLibelleMotCle()}</a> 
												--%>   
									</c:forEach>									
							    </div>
							    					   
						   </div> 
						
						<input type="text" name="id" style="display:none" value="${requestScope.modIsbn}"/><!--  je l'utilise pour la modification -->
						<input class="btn btn-primary" type="submit"  <c:choose> <c:when test="${requestScope.modIsbn != null }"> value="Modifier"</c:when><c:otherwise> value="Créer"</c:otherwise></c:choose>   />
						
					</form>
				</div>
			</section>
		</section>
	</main>
	
	
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    
    <script src="./inc/js/main.js"></script>
</body>
</html>