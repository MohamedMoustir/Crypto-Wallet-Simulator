

# README.md – Crypto Wallet Simulator

## 1️⃣ Description du projet

**Crypto Wallet Simulator** est une application console Java 8 qui simule un portefeuille crypto avec **mempool** et **optimisation des frais de transaction**.
Le but est de permettre à l’utilisateur de :

* Créer un wallet Bitcoin ou Ethereum
* Créer des transactions avec différents niveaux de frais (ÉCONOMIQUE, STANDARD, RAPIDE)
* Visualiser la position de sa transaction dans le mempool
* Comparer le compromis coût/rapidité des transactions


<img width="1108" height="383" alt="image" src="https://github.com/user-attachments/assets/b4c352a0-8275-416f-b1be-a5348efae1de" />

---

## 2️⃣ Structure en couches

* **Couche présentation (UI/Menu)** : Console interactive pour l’utilisateur
* **Couche métier** : Logique de transactions, calcul des fees, gestion du mempool
* **Couche données (Repository Pattern)** : Stockage et recherche rapide (ArrayList, HashMap)
* **Couche utilitaire** : Gestion des UUID, génération aléatoire, validation des adresses
* **Autres** : Classes Enum pour les types, priorités, statuts
<img width="441" height="476" alt="image" src="https://github.com/user-attachments/assets/193311f7-68d8-4bf6-bfea-848e32a67484" />

---

## 3️⃣ Classes principales et relations

* `Wallet` : Contient l’adresse crypto, type (BTC/ETH), solde, liste de transactions
* `Transaction` : ID unique, source/destination, montant, fees, priorité, statut
* `Mempool` : Liste des transactions en attente, calcul de la position selon fees
* `FeeCalculator` : Calcule les fees selon type crypto et niveau de priorité
* `Repository` : Gestion des wallets et transactions
* `Main` : Menu console et orchestration des fonctionnalités

### Diagramme ASCII simplifié

```
Wallet
 ├── id
 ├── type
 ├── address
 └── List<Transaction>

Transaction
 ├── UUID
 ├── fromAddress
 ├── toAddress
 ├── amount
 ├── fees
 ├── priority (ECONOMIQUE/ STANDARD/ RAPIDE)
 └── status (PENDING/ CONFIRMED/ REJECTED)

Mempool
 └── List<Transaction> pendingTransactions
```

---

## 4️⃣ Interface utilisateur console

* Menu principal :

  1. Créer mon wallet
  2. Créer une nouvelle transaction
  3. Voir ma position dans le mempool
  4. Comparer les 3 niveaux de frais
  5. Consulter l’état actuel du mempool
<img width="1199" height="337" alt="image" src="https://github.com/user-attachments/assets/3270a094-2418-46d9-aa05-ee6031d07c07" />

---

## 5️⃣ Spécifications techniques

* **Langage** : Java 8
* **Stockage** : ArrayList/HashMap
* **Enums** : Priorités, statuts, types de crypto
* **Dates** : Java Time API
* **Gestion des exceptions** : try-catch
* **Logging** : SLF4J / Logback (System.out.println uniquement pour menu)
* **Stream API** : map, filter, reduce, Optional
* **Base de données** : PostgreSQL via JDBC

---

## 6️⃣ Bonus et améliorations possibles

* Tests unitaires avec JUnit
* Export des transactions en CSV
* Containerisation avec Docker
* Interface graphique (future amélioration)

---

## 7️⃣ Instructions de lancement et prérequis

**Prérequis :**

* JDK 8 installé
* PostgreSQL pour persistance (si nécessaire)

**Lancer l’application :**

```bash
javac -d bin src/**/*.java
java -cp bin Main
```

---

## 8️⃣ Captures d’écran de l’application

<img width="551" height="328" alt="image" src="https://github.com/user-attachments/assets/f3a0bbd0-8dea-488a-a802-33b4a9924488" />
<img width="783" height="316" alt="image" src="https://github.com/user-attachments/assets/4f2bb850-055b-443a-b35a-9b8f2bc9f4fa" />

---

## 9️⃣ Critères de performance

* Fonctionnalité complète selon le README
* Respect des principes SOLID et des Design Patterns (Singleton, Repository)
* Code propre et commenté
* Logs appropriés
* Tests unitaires fonctionnels

---

## 10️⃣ Auteur

**Auteur** : Mohamed Moustir
**Email** : itsMoustir@gmail.com



