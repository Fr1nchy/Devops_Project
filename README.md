<h2>Cycle de vie par defaut de Maven :</h2>

• validate : valide que le projet est correct et que toutes les infos
  necessaires sont disponibles</br>
• compile</br>
• test</br>
• package :  package les sources compilees dans un format
  distribuable (par ex JAR)</br>
• integration-test</br>
• verify :  Lance les tests pour verifier la qualité du package</br>
• install :  Installe le package dans le dépôt local</br>
• deploy : Copie le package final dans un dépôt distant pour le
  partager</br>
  
  
Pour executer :</br>
• execution jacoco :      mvn clean jacoco:prepare-agent install jacoco:report</br>
• execution test junit:   mvn test </br>
• execution site:         mvn clean jacoco:prepare-agent install jacoco:report ; mvn site</br>



Amélioration possible : </br>
• Utiliser des hashtables </br>
• Utiliser des types Comparables </br>
• Ajouter des d'autres opérations (count,cumsum,...) </br>

