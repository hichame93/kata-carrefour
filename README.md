# 📦 Delivery Tracking MVP

Ce projet est un MVP (Minimum Viable Product) de suivi de livraison, développé avec **Java 21**, **Spring Boot 3**, **Spring WebFlux** et une **architecture hexagonale (ports/adapters)**.

## 🚀 Fonctionnalités

- Récupérer les informations d'une livraison par ID
- Mettre à jour l’adresse de livraison *(si l’état est différent de READY)*
- Mettre à jour le créneau horaire *(si l’état est différent de READY)*
- Architecture propre et testable
- Réactivité avec WebFlux (Mono)
- Tests unitaires avec JUnit5, Mockito et Reactor Test


## 🛠️ Prérequis

- Java 21
- Maven
- (optionnel) Docker si tu veux utiliser une base de données Postgres ou autre

## ▶️ Lancer l'application

```bash

mvn spring-boot:run


