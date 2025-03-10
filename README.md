#  Spring Boot Pokémon API Mathys Paillaugue

## Ce projet est une API Spring Boot sécurisée avec **Spring Security** et utilisant **Flyway** pour la gestion de la base de données.  
## Il permet :
-  **Faire rebondir** des requêtes vers une API distante (**Tyradex API**)
-  **Scraper** et stocker des Pokémon depuis **Tyradex API**
-  **Gérer un CRUD** sur les Pokémon en base de données

---

## Installation

## 1️⃣ Prérequis
Assurez-vous d'avoir installé :
- [MySQL](https://www.mysql.com/)
- [Postman](https://www.postman.com/) ou [Bruno](https://www.usebruno.com)

## 2️⃣ Cloner le projet
```bash
git clone https://github.com/Mathys-CoyDak/spring-pokemon-api.git
cd spring-pokemon-api
```
## 3️⃣ Configurer la base de données
Créez une base de données MySQL :

```sql
CREATE DATABASE [name_database];
```

Vérifiez que MySQL tourne bien sur localhost:3306 et mettez à jour src/main/resources/application.properties :
Changer les champs selon vos informations de votre database
## Database
```
spring.datasource.url=jdbc:mysql://localhost:3306/[name_database]
spring.datasource.username=[your_username]
spring.datasource.password=[your_password]
```

## Security
```
jwt.secret=[clé à changer]
```
## 4️⃣ Démarrer l'application

Démarrer WebsiteApplication par votre IDE ou si vous avez maven d'installer:
```bash
mvn spring-boot:run
```
L'API est accessible sur http://localhost:8085/api/pokemon.

## 5️⃣ Tester les différentes routes API

Importez le fichier Spring_boot_Bruno_Mathys.json dans Bruno et testez les différentes routes.

Si vous souhaitez tester des routes accessibles uniquement à certains rôles, vous devez d'abord vous connecter avec un utilisateur disposant du rôle approprié.