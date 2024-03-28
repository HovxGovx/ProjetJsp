<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher les Etudiants</title>
<style>
    body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
     display: flex;
     flex-direction: column;
    align-items: center;
    justify-content: center;
}
nav {
    background-color: #333;
    padding: 10px;
    border-radius: 20px;
}

nav a {
    color: #fff;
    text-decoration: none;
    margin: 0 10px;
    font-weight: bold;
}
/* === removing default button style ===*/
.button {
  margin: 0;
  height: auto;
  background: transparent;
  padding: 0;
  border: none;
  cursor: pointer;
  border-radius: 20px;
}

/* button styling */
.button {
  --border-right: 3px;
  --text-stroke-color: rgba(255,255,255,0.6);
  --animation-color: #007bff;
  --fs-size: 1.5em;
  letter-spacing: 1px;
  text-decoration: none;
  font-size: var(--fs-size);
  font-family: "Arial";
  position: relative;
  color: transparent;
  -webkit-text-stroke: 1px var(--text-stroke-color);
}
/* this is the text, when you hover on button */
.hover-text {
  position: absolute;
  box-sizing: border-box;
  content: attr(data-text);
  color: var(--animation-color);
  width: 0%;
  inset: 0;
  border-right: var(--border-right) solid var(--animation-color);
  overflow: hidden;
  transition: 0.5s;
  -webkit-text-stroke: 1px var(--animation-color);
}
/* hover */
.button:hover .hover-text {
  width: 100%;
  filter: drop-shadow(0 0 23px var(--animation-color))
}

    .container {
        background-color: #fff;
        border-radius: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px 20px 20px 20px;
        margin: 20px 20px 20px 20px;
    }

    h2 {
        color: #007bff;
    }

    #searchInput {
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #007bff;
        color: white;
    }

    tr:hover {
        background-color: #f5f5f5;
    }
 
 .textInputWrapper {
  position: relative;
  width: 180px;
  margin: 12px 5px;
  --accent-color: #0066ff;
}

.textInputWrapper:before {
  transition: border-bottom-color 200ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  border-bottom: 1px solid rgba(0, 0, 0, 0.42);
}

.textInputWrapper:before,
.textInputWrapper:after {
  content: "";
  left: 0;
  right: 0;
  position: absolute;
  pointer-events: none;
  bottom: -1px;
  z-index: 4;
  width: 100%;
}

.textInputWrapper:focus-within:before {
  border-bottom: 1px solid var(--accent-color);
}

.textInputWrapper:before {
  transition: border-bottom-color 200ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  border-bottom: 1px solid rgba(0, 0, 0, 0.42);
}

.textInputWrapper:focus-within:before {
  border-bottom: 1px solid var(--accent-color);
  transform: scaleX(1);
}

.textInputWrapper:focus-within:after {
  border-bottom: 4px solid var(--accent-color);
  transform: scaleX(1);
}

.textInputWrapper:after {
  content: "";
  transform: scaleX(0);
  transition: transform 250ms cubic-bezier(0, 0, 0.2, 1) 0ms;
  will-change: transform;
  border-bottom: 2px solid var(--accent-color);
  border-bottom-color: var(--accent-color);
}

.textInput::placeholder {
  transition: opacity 250ms cubic-bezier(0, 0, 0.2, 1) 0ms;
  opacity: 1;
  user-select: none;
  color: rgba(255, 255, 255, 0.582);
}

.textInputWrapper .textInput {
  border-radius: 5px 5px 0px 0px;
  box-shadow: 0px 2px 5px rgb(35 35 35 / 30%);
  max-height: 36px;
  background-color: #252525;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-duration: 200ms;
  transition-property: background-color;
  color: #e8e8e8;
  font-size: 14px;
  font-weight: 500;
  padding: 12px;
  width: 100%;
  border-left: none;
  border-bottom: none;
  border-right: none;
}

.textInputWrapper .textInput:focus,
.textInputWrapper .textInput:active {
  outline: none;
}

.textInputWrapper:focus-within .textInput,
.textInputWrapper .textInput:focus,
.textInputWrapper .textInput:active {
  background-color: #353535;
}

.textInputWrapper:focus-within .textInput::placeholder {
  opacity: 0;
}


form {
    margin-top: 20px;
}

.title {
    font-size: 24px;
    margin-bottom: 10px;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 350px;
  padding: 20px;
  border-radius: 20px;
  position: relative;
  background-color: #fff;
  color: #333;
  border: 1px solid #fff;
}

.title {
  font-size: 28px;
  font-weight: 600;
  letter-spacing: -1px;
  position: relative;
  display: flex;
  align-items: center;
  padding-left: 30px;
  color: #00bfff;
}

.title::before {
  width: 18px;
  height: 18px;
}

.title::after {
  width: 18px;
  height: 18px;
  animation: pulse 1s linear infinite;
}

.title::before,
.title::after {
  position: absolute;
  content: "";
  height: 16px;
  width: 16px;
  border-radius: 50%;
  left: 0px;
  background-color: #00bfff;
}

.message, 
.signin {
  font-size: 14.5px;
  color: rgba(255, 255, 255, 0.7);
}

.signin {
  text-align: center;
}

.signin a:hover {
  text-decoration: underline royalblue;
}

.signin a {
  color: #00bfff;
}

.flex {
  display: flex;
  width: 100%;
  gap: 6px;
}

.form label {
  position: relative;
}

.form label .input {
  background-color: #333;
  color: #fff;
  width: 100%;
  padding: 20px 05px 05px 10px;
  outline: 0;
  border: 1px solid rgba(105, 105, 105, 0.397);
  border-radius: 10px;
}

.form label .input + span {
  color: rgba(255, 255, 255, 0.5);
  position: absolute;
  left: 10px;
  top: 0px;
  font-size: 0.9em;
  cursor: text;
  transition: 0.3s ease;
}

.form label .input:placeholder-shown + span {
  top: 12.5px;
  font-size: 0.9em;
}

.form label .input:focus + span,
.form label .input:valid + span {
  color: #00bfff;
  top: 0px;
  font-size: 0.7em;
  font-weight: 600;
}

.input {
  font-size: medium;
}

.submit {
  border: none;
  outline: none;
  padding: 10px;
  border-radius: 10px;
  color: #fff;
  font-size: 16px;
  transform: .3s ease;
  background-color: #00bfff;
}

.submit:hover {
  background-color: #00bfff96;
}

@keyframes pulse {
  from {
    transform: scale(0.9);
    opacity: 1;
  }

  to {
    transform: scale(1.8);
    opacity: 0;
  }
}

.cont{
  max-width: 1700px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  align-content: stretch;
}
.contTab{
  max-height: 300px; /*  hauteur maximale */
    overflow-y: auto;
}
.cont2{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
  align-content: space-evenly;
}

#modal{
  position: absolute;
}
#classStatistics{
        background-color: #fff;
        border-radius: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px 20px 20px 20px;
}
</style>
</head>
<body>   
  <!-- Navigation -->
  <div class="connav ">
    <nav class="">
      <a class="" aria-current="page" href="index.jsp">
          	<button class="button" data-text="Awesome">	    	
			    	<span class="actual-text">&nbsp;Ajout&nbsp;</span>
			    	<span aria-hidden="true" class="hover-text">&nbsp;Ajout&nbsp;</span> 
			   </button>
			</a>
			<a class="" aria-current="page" href="afficherEtudiants.jsp">
          		<button class="button" data-text="Awesome">	    	
			   	  <span class="actual-text">&nbsp;Resultat&nbsp;</span>
			    	<span aria-hidden="true" class="hover-text">&nbsp;Resultat&nbsp;</span> 
			    </button>
			</a>
			<a class="" aria-current="page" href="Histogramme.jsp">
          		<button class="button" data-text="Awesome">	    	
			   	  <span class="actual-text">&nbsp;Histogramme&nbsp;</span>
			    	<span aria-hidden="true" class="hover-text">&nbsp;Histogramme&nbsp;</span> 
			    </button>
			</a>
		</nav>
  </div>         
<div class="container">
  
  <div class="cont">
    <div class="cont2">
      <div class="contTab container">
        <p class="title" style="margin-top: 10px;"> Liste des Étudiants</p>
        <div class="textInputWrapper">
          <input placeholder="Nom Etudiant" type="text" class="textInput" id="searchInput">
        </div>
        <table class="table table-striped" id="etudiantTable">
            <thead>
                <tr>
                    <th>Numéro Étudiant</th>
                    <th>Nom</th>
                    <th>Moyenne</th>
                    <th>Observation</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- chargées  avec JavaScript -->
            </tbody>
        </table>
      </div>
      <div id="modal" class="contmod container" style="display: none;">
            <form class="form"  method="post" action="ModifierEtudiantServlet">
                  <p class="title"  style="margin-top: 10px;" >Modify Student</p>
                  <p class="message"></p>
                  <label>
                      <input class="input" type="text" placeholder=""  id="nom" name="nom" required>
                      <span>Nom</span>
                  </label>
    
                  <label>
                      <input class="input"  type="text" placeholder="" id="moyenne" name="moyenne" required>
                      <span>Moyenne</span>
                  </label>
                   <label>
                      <input class="input"  type="text" placeholder="" id="numet" name="numet" required>
                      <span>Numero Etudiant</span>
                  </label>
                  <input class="submit" type="submit">
                </form>
        
      </div>
    </div>
    <div class="cont2">
      <div id="classStatistics" class="">
        <!--  affiche les statistiques de classe -->
      </div>
    <div class="container conthisto">
   		<p class="title" style="margin-top: 10px;">Histogramme</p>
    	<p class="message"></p>
    	<img id="classChart"  alt="Graphique des moyennes">
    </div>
    </div>
  </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function () {
    // Variables pour la pagination
    var currentPage = 1;
    var studentsPerPage = 100;

    // Fonction pour charger les données au chargement de la page
    loadEtudiantData(currentPage, studentsPerPage);
	//Fonction pour afficher l'histogramme
    loadClassChart();
    // Fonction pour filtrer le tableau en fonction de la saisie de l'utilisateur
    document.getElementById('searchInput').addEventListener('input', function () {
        filterTable();
    });
    })
function loadEtudiantData(page, perPage) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var etudiantTableBody = document.getElementById('etudiantTable').getElementsByTagName('tbody')[0];
                etudiantTableBody.innerHTML = xhr.responseText;
                loadClassStatistics();
            } else {
                console.error('Erreur lors du chargement des etudiants (javascript.)');
            }
        }
     }

    // Envoyer la requête avec les paramètres de pagination
    xhr.open('GET', 'ChargerDonneesServlet?page=' + page + '&perPage=' + perPage, true);
    xhr.send();
}

function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById('searchInput');
    filter = input.value.toUpperCase();
    table = document.getElementById('etudiantTable');
    tr = table.getElementsByTagName('tr');
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName('td')[1]; 
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = '';
            } else {
                tr[i].style.display = 'none';
            }
        }
    }
}
function loadClassStatistics() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var classStatisticsDiv = document.getElementById('classStatistics');
                classStatisticsDiv.innerHTML = xhr.responseText;
            } else {
                console.error('Erreur lors du chargement des statistiques de classe en JavaScript.');
            }
        }
    }
    // Envoyer la requête pour charger les statistiques de classe
    xhr.open('GET', 'StatisticsServlet', true);
    xhr.send();
}

function supprimerEtudiant(numEt) {
	
    if (confirm("Êtes-vous sûr de vouloir supprimer cet étudiant?")) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // Mettez à jour la page ou effectuez d'autres actions nécessaires
                    location.reload();
                } else {
                    console.error("Erreur lors de la suppression de l'étudiant:", xhr.statusText);
                }
            }
        };

        xhr.open("POST", "SupprimerEtudiantServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("numEt=" + numEt);
    }
}
    function modifierEtudiant(numEtudiant, nom, moyenne) {
    	console.log(numEtudiant);
    	console.log(nom);
    	console.log(moyenne);
        document.getElementById('nom').value = nom;
        document.getElementById('moyenne').value = moyenne;
        document.getElementById('numet').value = numEtudiant;
        document.getElementById('modal').style.display = 'block'; 
        }
    function loadClassChart() {
        var chartImage = document.getElementById('classChart');
        chartImage.src = 'ChartServlet';
    }
</script>

</body>
</html>