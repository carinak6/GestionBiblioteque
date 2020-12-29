<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "tp.biblioteque.model.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
		<meta charset="ISO-8859-1">
		<title>Biblioteque</title>
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
	<div class="alert alert-success" > <h1>Liste des Abonnes </h1></div>
	<div class="container-lg">
		<table class="table table-dark">
			
		    <tbody>	        
				<c:forEach var="valeur" items="${requestScope.listeAbonne}">			
					<tr>
						<td scope="col" ><c:out value="${valeur.getNom()} ${valeur.getPrenom()}  " /> </td>
						<td scope="col"><a href="<c:url value = "abonne?action=update&id=${valeur.getCodMatricule()}&nom=${valeur.getNom()}&prenom=${valeur.getPrenom()}&adresse=${valeur.getAdresse()}&datenaissance=${valeur.getDateNaissance()}&dateadhesion=${valeur.getDateAdhesion()}&categorieprof=${valeur.getLibelleCategProf()}"/>">Modifier</a> </td>
						<td scope="col"><a href="<c:url value = "abonne?action=delete&id=${valeur.getCodMatricule()}"/>">Effacer</a> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="alert alert-success"><h1>Formulaire d'Inscription</h1></div>
	<div class="container-lg alert-success">
			<form action="abonne" method ="post">
				 <div class="form-group">
					    <label for="">Nom : </label>
					    <input type="text" class="form-control" id="" placeholder=""  name="nom" value="${requestScope.modNom}">
				  </div>
				  <div class="form-group">
					    <label for="">Prenom : </label>
					    <input type="text" class="form-control" id="" placeholder=""  name="prenom" value="${requestScope.modPrenom}">
				  </div>
				  <div class="form-group">
					    <label for="">Adresse : </label>
					    <textarea class="form-control" row=3 name="adresse" value="">${requestScope.modAdresse}</textarea>
				  </div>
				  <div class="form-group">
					    <label for="">Date de Naissance : </label>
					    <input type="date" class="form-control" id="" placeholder=""  name="datenaissance" value="${requestScope.modDateNaissance}">
				  </div>
				  <div class="form-group">
					    <label for="">Date d'Adhesion : </label>
					    <input type="date" class="form-control" id="" placeholder=""  name="dateadhesion" value="${requestScope.modDateAdhesion}">
				  </div>
				 <div class="form-group">
				    <label for="">Catégorie Professionnelle</label>
				    <select class="form-control" name="codCategorieProf" id="">
					      <option value= "1"  <c:if test="${requestScope.modCodCategorieProf == 'Etudiant' }" > selected  </c:if> >Etudiant</option>
					      <option value= "2" <c:if test="${requestScope.modCodCategorieProf == 'Enfant' }" > selected </c:if> >Enfant</option>
					      <option value= "3"  <c:if test="${requestScope.modCodCategorieProf == 'Informaticien' }" > selected </c:if> >Informaticien</option>
					      
				    </select>
				    <!-- <label for="">${requestScope.modCodCategorieProf}</label> -->
				   </div>
				
				<input type="text" name="id" style="display:none" value="${requestScope.modCodM}"/><!--  je l'utilise pour la modification -->
				<input class="btn btn-primary" type="submit"  <c:choose> <c:when test="${requestScope.modCodM != null }"> value="Modifier"</c:when><c:otherwise> value="Inscrire"</c:otherwise></c:choose>   />																        
			</form>
	</div>
	
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>