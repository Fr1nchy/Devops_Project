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

Fonctionnalités implémentés: </br>
• Constructueur prenant paramètre un ArrayList</br>
• Constructueur prenant paramètre un nom de fichier csv</br>
• Affichage d'un dataframe en entier, les premières lignes ou les dernières lignes</br>
• Sélection d'un dataframe par ligne ou par colonne</br>
• Opérations sur les colonnes : somme, minimun maximun et moyenne</br>
• Fonction group by avec opérations : somme, minimun maximun et moyenne</br>
• Interface utilisateur</br>

Taux de couverture de code total 81% : </br>
• Pandas aucun test réalisé comme étant l'interface d'execution </br>
• CalculatorArray pas 100% de couverture de code comme ne disposant pas de constructeur </br>

Amélioration possible : </br>
• Utiliser des hashtables </br>
• Utiliser des types Comparables </br>
• Ajouter des d'autres opérations (count,cumsum,...) </br>

