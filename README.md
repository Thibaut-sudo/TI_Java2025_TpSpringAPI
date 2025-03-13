# 📦 Demo Spring API

## Description
Ce projet est une API REST développée en Spring Boot permettant la gestion de produits, stocks, entrepôts et utilisateurs. Il fournit des endpoints sécurisés pour effectuer des opérations CRUD et rechercher des produits en ligne.

Ce projet est réalisé dans le cadre d'un **travail de groupe** 

## 🚀 Technologies utilisées
- Java 17
- Spring Boot 3
- Spring Security
- Hibernate (JPA)
- Lombok
- OpenAPI (Swagger UI)
- PostgreSQL / MySQL (ou autre base de données compatible JPA)
- Maven
- Junit
- Mokito

## 📌 Prérequis
- **Java 17+** installé
- **Maven** installé
- Une base de données PostgreSQL/MySQL en place

## ⚙️ Installation
### 1️⃣ Cloner le projet
```sh
git clone https://github.com/ton-repo/demo-spring-api.git
cd demo-spring-api
```
### 2️⃣ Configurer la base de données
Modifier le fichier `application.properties` ou `application.yml` avec les informations de votre base de données.

Exemple pour PostgreSQL :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ma_base
spring.datasource.username=mon_user
spring.datasource.password=mon_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Construire et exécuter l'application
```sh
mvn clean install
mvn spring-boot:run
```

## 📡 Documentation API
Une fois l'application démarrée, la documentation Swagger est disponible à l'adresse :
```
http://localhost:8080/swagger-ui.html
```

## 🔑 Sécurité
- Certaines routes nécessitent une authentification.
- Spring Security est utilisé pour la gestion des rôles et des permissions.

## 🛠 Endpoints principaux
### Produits
- `GET /product` : Récupérer tous les produits
- `GET /product/{id}` : Récupérer un produit par ID
- `POST /product` : Ajouter un produit (authentification requise)
- `PUT /product/{id}` : Mettre à jour un produit (authentification requise)
- `DELETE /product/{id}` : Supprimer un produit (authentification requise)

### Stock
- `GET /stock` : Récupérer tous les stocks
- `POST /stock` : Ajouter un stock (authentification requise)
- `PUT /stock/{id}` : Modifier un stock (authentification requise)
- `DELETE /stock/{id}` : Supprimer un stock (rôle COMERCIAL requis)
- `GET /stock/low` : Récupérer les stocks faibles (rôle COMERCIAL requis)

### Utilisateurs
- `GET /user` : Récupérer tous les utilisateurs (paginé)

### Entrepôts
- `GET /warehouse` : Récupérer tous les entrepôts
- `POST /warehouse` : Ajouter un entrepôt (authentification requise)
- `DELETE /warehouse/{id}` : Supprimer un entrepôt (authentification requise)

## 📄 Licence
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser et de le modifier.

![image](https://github.com/user-attachments/assets/085e2494-2774-4cbe-b02b-5c0d76945e35)

