<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Configuration de la page -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestion Etudiant</title>

    <!-- Liens vers les feuilles de style -->
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">
    <link rel="stylesheet"  type="text/css" href="addStudent.css">
 <style>
 body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
     display: flex;
    align-items: center;
    justify-content: center;
}

.container1 {
    max-width: 800px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 20px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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

 </style>
</head>
<body class="container1">
        <div class="">
            <!-- Navigation -->
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
        <form class="form" action="AddStudent" method="post" >
            <p class="title">Ajout d'etudiant</p>
            <p class="message"></p>
            <label>
                <input class="input" type="text" placeholder="" name="nom" required>
                <span>Nom</span>
            </label>

            <label>
                <input class="input"  type="text" placeholder="" name="moyenne" required>
                <span>Moyenne</span>
            </label>
            <label>
                <input class="input"  type="text" placeholder="" name="numEt" required>
                <span>Numero Etudiant</span>
            </label>
            <button class="submit">Enregistrer</button>
        </form>
    </div>
</body>
</html>