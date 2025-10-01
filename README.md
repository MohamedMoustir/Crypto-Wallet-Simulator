# 💰 Crypto Wallet Simulator

> Une application console Java 8 pour simuler un portefeuille crypto avec mempool et optimisation des frais de transaction

[![Java Version](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Production-success.svg)](https://github.com)

---

## 📋 Table des Matières

- [Description du Projet](#-description-du-projet)
- [Captures d'Écran](#-captures-décran)
- [Technologies Utilisées](#-technologies-utilisées)
- [Architecture du Projet](#-architecture-du-projet)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Guide d'Utilisation](#-guide-dutilisation)
- [Fonctionnalités](#-fonctionnalités)
- [Diagramme de Classe UML](#-diagramme-de-classe-uml)
- [Critères de Performance](#-critères-de-performance)
- [Bonus Implémentés](#-bonus-implémentés)
- [Auteur](#-auteur)

---

## 🎯 Description du Projet

**Crypto Wallet Simulator** est une application console qui simule un portefeuille de cryptomonnaies avec un système de mempool et de calcul de frais de transaction. L'application permet aux utilisateurs de comprendre comment fonctionnent les transactions blockchain, la priorisation des transactions selon les frais payés, et l'optimisation des coûts.

### 💡 Idée Générale

Dans l'écosystème des cryptomonnaies, chaque transaction nécessite des frais pour être validée par le réseau. Ces frais varient selon :
- La **congestion du réseau** (nombre de transactions en attente)
- L'**urgence souhaitée** par l'utilisateur
- Le **type de cryptomonnaie** (Bitcoin ou Ethereum)

L'application simule ce processus en créant un mempool virtuel avec des transactions aléatoires, puis calcule la position de votre transaction selon les frais que vous êtes prêt à payer.

### 🔑 Concepts Blockchain Implémentés

| Concept | Description |
|---------|-------------|
| **Transaction** | Transfert de cryptomonnaie d'une adresse à une autre |
| **Transaction Fees** | Coût payé aux mineurs pour traiter la transaction |
| **Mempool** | File d'attente des transactions en attente de validation |
| **Wallet** | Portefeuille qui gère vos adresses et transactions |
| **Priorité** | ECONOMIQUE (lent), STANDARD (moyen), RAPIDE (rapide) |

---

## 📸 Captures d'Écran

### Menu Principal
```
╔═══════════════════════════════════════════════════╗
║       CRYPTO WALLET SIMULATOR v1.0               ║
║       Simulateur de Portefeuille Crypto          ║
╚═══════════════════════════════════════════════════╝

[1] 💼 Créer mon wallet
[2] 💸 Créer une nouvelle transaction
[3] 📊 Voir ma position dans le mempool
[4] ⚖️  Comparer les 3 niveaux de frais
[5] 🔍 Consulter l'état du mempool
[0] ❌ Quitter

Votre choix : _
```

### Comparaison des Niveaux de Frais
```
╔══════════════════════════════════════════════════════════════╗
║           COMPARAISON DES NIVEAUX DE FRAIS                  ║
╚══════════════════════════════════════════════════════════════╝

┌─────────────┬──────────────┬──────────────┬─────────────────┐
│ Priorité    │ Frais (USD)  │ Position     │ Temps Estimé    │
├─────────────┼──────────────┼──────────────┼─────────────────┤
│ RAPIDE      │ 8.50$        │ 3/25         │ ~30 minutes     │
│ STANDARD    │ 4.20$        │ 12/25        │ ~2 heures       │
│ ECONOMIQUE  │ 1.80$        │ 21/25        │ ~3.5 heures     │
└─────────────┴──────────────┴──────────────┴─────────────────┘
```

### État du Mempool
```
═══════════════════════════════════════════════════════════
                  ÉTAT DU MEMPOOL
═══════════════════════════════════════════════════════════
Transactions en attente : 18

┌────────────────────────────────────┬─────────────┐
│ Transaction                         │ Frais (USD) │
├────────────────────────────────────┼─────────────┤
│ 0x742d35cc... (anonyme)            │ 8.50$       │
│ 0x8a5f92bd... (anonyme)            │ 5.20$       │
│ 0x1c4e67fa... (anonyme)            │ 2.80$       │
│ >>> VOTRE TX: 0x9b2a14ef...        │ 2.00$       │
│ 0x3f8d91ab... (anonyme)            │ 1.50$       │
└────────────────────────────────────┴─────────────┘
```

---

## 🛠 Technologies Utilisées

| Technologie | Version | Usage |
|-------------|---------|-------|
| **Java** | 8 | Langage principal |
| **JDBC** | PostgreSQL Driver | Persistance des données |
| **PostgreSQL** | 12+ | Base de données |
| **SLF4J/Logback** | 1.7+ | Système de logging |
| **JUnit** | 4.12 | Tests unitaires (bonus) |
| **Git** | 2.0+ | Gestion de version |
| **JIRA** | - | Gestion de projet |
| **Docker** | 20+ | Containerisation (bonus) |

### 📦 Dépendances Externes

```
- postgresql-42.x.x.jar (JDBC Driver)
- slf4j-api-1.7.x.jar (Logging)
- logback-classic-1.2.x.jar (Logging Implementation)
- junit-4.12.jar (Tests - Bonus)
```

---

## 🏗 Architecture du Projet

### Structure en Couches

```
src/
├── main/
│   ├── java/
│   │   ├── com.cryptowallet/
│   │   │   ├── presentation/        # Couche Présentation (UI/Menu)
│   │   │   │   ├── ConsoleUI.java
│   │   │   │   └── MenuHandler.java
│   │   │   │
│   │   │   ├── domain/              # Couche Métier (Business Logic)
│   │   │   │   ├── model/
│   │   │   │   │   ├── Wallet.java
│   │   │   │   │   ├── Transaction.java
│   │   │   │   │   ├── BitcoinWallet.java
│   │   │   │   │   └── EthereumWallet.java
│   │   │   │   ├── enums/
│   │   │   │   │   ├── CryptoType.java
│   │   │   │   │   ├── FeePriority.java
│   │   │   │   │   └── TransactionStatus.java
│   │   │   │   └── service/
│   │   │   │       ├── WalletService.java
│   │   │   │       ├── TransactionService.java
│   │   │   │       ├── MempoolService.java
│   │   │   │       └── FeeCalculator.java (interface)
│   │   │   │           ├── BitcoinFeeCalculator.java
│   │   │   │           └── EthereumFeeCalculator.java
│   │   │   │
│   │   │   ├── repository/          # Couche Données (Repository Pattern)
│   │   │   │   ├── interfaces/
│   │   │   │   │   ├── WalletRepository.java
│   │   │   │   │   ├── TransactionRepository.java
│   │   │   │   │   └── MempoolRepository.java
│   │   │   │   └── impl/
│   │   │   │       ├── WalletRepositoryImpl.java
│   │   │   │       ├── TransactionRepositoryImpl.java
│   │   │   │       └── MempoolRepositoryImpl.java
│   │   │   │
│   │   │   ├── config/              # Configuration
│   │   │   │   ├── DatabaseConfig.java (Singleton)
│   │   │   │   └── AppConfig.java
│   │   │   │
│   │   │   └── util/                # Couche Utilitaire
│   │   │       ├── AddressGenerator.java
│   │   │       ├── ValidationUtil.java
│   │   │       ├── DateTimeUtil.java
│   │   │       ├── LoggerUtil.java
│   │   │       └── CSVExporter.java (bonus)
│   │   │
│   │   └── Main.java                # Point d'entrée
│   │
│   └── resources/
│       ├── logback.xml
│       └── database.properties
│
└── test/
    └── java/                        # Tests Unitaires (Bonus)
        └── com.cryptowallet/
            ├── service/
            │   ├── FeeCalculatorTest.java
            │   └── MempoolServiceTest.java
            └── util/
                └── ValidationUtilTest.java
```

### 📐 Diagramme de Couches

```
┌──────────────────────────────────────────────────┐
│           PRESENTATION LAYER                     │
│   (ConsoleUI, MenuHandler)                      │
└────────────────┬─────────────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────────────┐
│           BUSINESS LAYER                         │
│   (Services, Models, Enums)                     │
└────────────────┬─────────────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────────────┐
│           DATA LAYER                             │
│   (Repositories, JDBC)                          │
└────────────────┬─────────────────────────────────┘
                 │
                 ▼
┌──────────────────────────────────────────────────┐
│           DATABASE (PostgreSQL)                  │
└──────────────────────────────────────────────────┘
```

### 🔗 Classes Principales et leurs Relations

#### **Wallet** (Classe Abstraite)
- **Responsabilité** : Gérer un portefeuille crypto
- **Attributs** : `id`, `address`, `cryptoType`, `balance`, `transactions`
- **Méthodes** : `createTransaction()`, `getBalance()`, `addTransaction()`
- **Relations** : 
  - Héritage : `BitcoinWallet` et `EthereumWallet` héritent de `Wallet`
  - Composition : Contient une liste de `Transaction`

#### **Transaction**
- **Responsabilité** : Représenter une transaction blockchain
- **Attributs** : `id`, `fromAddress`, `toAddress`, `amount`, `fees`, `priority`, `status`, `timestamp`
- **Méthodes** : `calculateFees()`, `getEstimatedTime()`, `displayDetails()`
- **Relations** : Associée à un `Wallet` et une `FeePriority`

#### **MempoolService** (Singleton)
- **Responsabilité** : Gérer la file d'attente des transactions
- **Méthodes** : `addTransaction()`, `calculatePosition()`, `generateRandomTransactions()`, `displayMempool()`
- **Algorithme** : Trie les transactions par frais décroissants pour déterminer la position

#### **FeeCalculator** (Interface)
- **Responsabilité** : Calculer les frais selon le type de crypto
- **Implémentations** :
  - `BitcoinFeeCalculator` : taille (bytes) × tarif (satoshi/byte)
  - `EthereumFeeCalculator` : gasLimit × gasPrice

---

## 📋 Prérequis

Avant de commencer, assurez-vous d'avoir installé :

### Obligatoire
- ☑️ **JDK 8** (exactement la version 8, pas de version supérieure)
- ☑️ **PostgreSQL** (version 12 ou supérieure)
- ☑️ **Git** (pour cloner le repository)

### Recommandé
- ☑️ **IDE** : IntelliJ IDEA, Eclipse, ou VSCode
- ☑️ **Plugin SonarLint** (analyse de qualité de code)
- ☑️ **Plugin UML Generator** (visualisation des classes)

### Vérification des Versions

```bash
# Vérifier Java
java -version
# Doit afficher : java version "1.8.0_xxx"

# Vérifier PostgreSQL
psql --version
# Doit afficher : psql (PostgreSQL) 12.x ou supérieur

# Vérifier Git
git --version
```

---

## 🚀 Installation

### 1️⃣ Cloner le Repository

```bash
git clone https://github.com/votre-username/crypto-wallet-simulator.git
cd crypto-wallet-simulator
```

### 2️⃣ Configuration de la Base de Données

#### Créer la base de données PostgreSQL

```bash
# Se connecter à PostgreSQL
psql -U postgres

# Créer la base de données
CREATE DATABASE crypto_wallet_db;

# Se connecter à la base
\c crypto_wallet_db

# Exécuter le script SQL
\i sql/schema.sql
```

#### Ou via script SQL

```bash
psql -U postgres -d crypto_wallet_db -f sql/schema.sql
```

### 3️⃣ Configurer les Credentials

Modifier le fichier `src/main/resources/database.properties` :

```properties
db.url=jdbc:postgresql://localhost:5432/crypto_wallet_db
db.username=votre_username
db.password=votre_password
db.driver=org.postgresql.Driver
```

### 4️⃣ Télécharger les Dépendances

Téléchargez et placez les JAR suivants dans le dossier `lib/` :

- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/)
- [SLF4J API](https://www.slf4j.org/download.html)
- [Logback Classic](https://logback.qos.ch/download.html)

Structure du dossier `lib/` :
```
lib/
├── postgresql-42.6.0.jar
├── slf4j-api-1.7.36.jar
└── logback-classic-1.2.11.jar
```

### 5️⃣ Compiler le Projet

```bash
# Compiler avec javac
javac -cp "lib/*" -d bin -sourcepath src/main/java src/main/java/com/cryptowallet/Main.java

# Ou utiliser le script fourni
./compile.sh   # Linux/Mac
compile.bat    # Windows
```

### 6️⃣ Créer le JAR Exécutable

```bash
# Créer le JAR
jar cvfe CryptoWalletSimulator.jar com.cryptowallet.Main -C bin .

# Copier les dépendances
cp lib/*.jar .
```

---

## 📖 Guide d'Utilisation

### Lancer l'Application

#### Option 1 : Via JAR Exécutable
```bash
java -cp "CryptoWalletSimulator.jar:lib/*" com.cryptowallet.Main
```

#### Option 2 : Via Classes Compilées
```bash
java -cp "bin:lib/*" com.cryptowallet.Main
```

#### Option 3 : Via Script
```bash
./run.sh   # Linux/Mac
run.bat    # Windows
```

### Navigation dans le Menu

```
[1] Créer mon wallet
    → Choisir Bitcoin ou Ethereum
    → Adresse générée automatiquement
    → Solde initialisé à 0

[2] Créer une transaction
    → Saisir adresse source et destination
    → Entrer le montant
    → Choisir la priorité (ECONOMIQUE/STANDARD/RAPIDE)
    → Transaction créée et ajoutée au mempool

[3] Voir ma position
    → Affiche la position actuelle dans la file d'attente
    → Calcule le temps d'attente estimé

[4] Comparer les frais
    → Tableau comparatif des 3 niveaux
    → Affiche position et temps pour chaque niveau

[5] État du mempool
    → Liste toutes les transactions en attente
    → Met en évidence votre transaction
    → Affiche les frais de chaque transaction
```

### Exemples d'Utilisation

#### Créer un Wallet Bitcoin
```
Choisissez le type de cryptomonnaie :
1. Bitcoin
2. Ethereum
Votre choix : 1

✓ Wallet Bitcoin créé avec succès !
Adresse : 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa
Type : BITCOIN
Solde : 0.00 BTC
```

#### Créer une Transaction Ethereum
```
Adresse source : 0x742d35cc8c...
Adresse destination : 0x8a5f92bd4e...
Montant : 0.5
Priorité (1=ECONOMIQUE, 2=STANDARD, 3=RAPIDE) : 2

✓ Transaction créée !
ID : 550e8400-e29b-41d4-a716-446655440000
Frais calculés : 4.20 USD
Statut : PENDING
Position estimée dans mempool : 12/25
```

---

## ⚡ Fonctionnalités

### Fonctionnalités Principales

| Feature | Description | Status |
|---------|-------------|--------|
| 💼 **Création de Wallet** | Support Bitcoin et Ethereum avec génération d'adresse | ✅ |
| 💸 **Gestion des Transactions** | Création, validation, et calcul des frais | ✅ |
| 📊 **Position Mempool** | Calcul de position selon les frais payés | ✅ |
| ⚖️ **Comparaison de Frais** | Tableau comparatif des 3 niveaux de priorité | ✅ |
| 🔍 **État du Mempool** | Visualisation de toutes les transactions en attente | ✅ |
| 💾 **Persistance PostgreSQL** | Sauvegarde en base de données via JDBC | ✅ |
| 📝 **Logging Avancé** | SLF4J/Logback pour traçabilité | ✅ |
| ✅ **Validations** | Format d'adresse, montants, priorités | ✅ |

### Algorithme de Mempool

```java
// Algorithme de calcul de position
1. Générer 10-20 transactions aléatoires
2. Ajouter votre transaction au pool
3. Trier par frais décroissants (Stream API)
4. Trouver la position de votre transaction
5. Calculer temps estimé : position × 10 minutes
```

### Calcul des Frais

#### Bitcoin
```
Frais = Taille Transaction (bytes) × Tarif (satoshi/byte)
- ECONOMIQUE : 10 sat/byte
- STANDARD : 20 sat/byte
- RAPIDE : 50 sat/byte
```

#### Ethereum
```
Frais = Gas Limit × Gas Price
- ECONOMIQUE : 20 Gwei
- STANDARD : 50 Gwei
- RAPIDE : 100 Gwei
```

---

## 🎨 Diagramme de Classe UML

```
                    ┌─────────────────┐
                    │   <<abstract>>  │
                    │     Wallet      │
                    ├─────────────────┤
                    │ - id: String    │
                    │ - address: String│
                    │ - cryptoType    │
                    │ - balance       │
                    │ - transactions  │
                    ├─────────────────┤
                    │ + createTx()    │
                    │ + getBalance()  │
                    └────────┬────────┘
                             │
                ┌────────────┴────────────┐
                │                         │
        ┌───────▼────────┐        ┌──────▼────────┐
        │ BitcoinWallet  │        │EthereumWallet │
        ├────────────────┤        ├───────────────┤
        │ + createTx()   │        │ + createTx()  │
        └────────────────┘        └───────────────┘

┌──────────────────────────────────────────────────────────┐
│                    Transaction                           │
├──────────────────────────────────────────────────────────┤
│ - id: UUID                                               │
│ - fromAddress: String                                    │
│ - toAddress: String                                      │
│ - amount: double                                         │
│ - fees: double                                           │
│ - priority: FeePriority                                  │
│ - status: TransactionStatus                              │
│ - timestamp: LocalDateTime                               │
├──────────────────────────────────────────────────────────┤
│ + calculateFees(): double                                │
│ + getEstimatedTime(): Duration                           │
│ + displayDetails(): void                                 │
└──────────────────────────────────────────────────────────┘

┌──────────────────┐         ┌──────────────────┐
│  <<enumeration>> │         │  <<enumeration>> │
│   CryptoType     │         │   FeePriority    │
├──────────────────┤         ├──────────────────┤
│ BITCOIN          │         │ ECONOMIQUE       │
│ ETHEREUM         │         │ STANDARD         │
└──────────────────┘         │ RAPIDE           │
                             └──────────────────┘

            ┌───────────────────────┐
            │    <<interface>>      │
            │    FeeCalculator      │
            ├───────────────────────┤
            │ + calculate(): double │
            └───────────┬───────────┘
                        │
        ┌───────────────┴────────────────┐
        │                                │
┌───────▼──────────┐          ┌─────────▼────────┐
│BitcoinFeeCalc    │          │EthereumFeeCalc   │
├──────────────────┤          ├──────────────────┤
│ + calculate()    │          │ + calculate()    │
└──────────────────┘          └──────────────────┘

┌──────────────────────────────────────────────────┐
│         MempoolService (Singleton)               │
├──────────────────────────────────────────────────┤
│ - instance: MempoolService                       │
│ - transactions: List<Transaction>                │
├──────────────────────────────────────────────────┤
│ + getInstance(): MempoolService                  │
│ + addTransaction(tx): void                       │
│ + calculatePosition(tx): int                     │
│ + generateRandomTransactions(): void             │
│ + displayMempool(): void                         │
└──────────────────────────────────────────────────┘

            ┌────────────────────┐
            │   <<interface>>    │
            │    Repository      │
            └──────────┬─────────┘
                       │
        ┌──────────────┼──────────────┐
        │              │              │
┌───────▼────┐  ┌──────▼──────┐  ┌───▼──────┐
│WalletRepo  │  │TransactionRepo│ │MempoolRepo│
└────────────┘  └───────────────┘ └──────────┘
```

---

## ✨ Critères de Performance

### 📊 Métriques de Qualité du Code

| Critère | Objectif | Status |
|---------|----------|--------|
| **Architecture en Couches** | Séparation claire des responsabilités | ✅ |
| **Principes SOLID** | Respect des 5 principes | ✅ |
| **Design Patterns** | Singleton, Repository Pattern | ✅ |
| **Enums** | Utilisation pour toutes les constantes | ✅ |
| **Encapsulation** | Getters/Setters, pas d'attributs publics | ✅ |
| **Interfaces** | Abstractions pour extensibilité | ✅ |
| **Héritage** | Hiérarchie logique (Wallet, FeeCalculator) | ✅ |
| **Stream API** | map, filter, reduce, Optional | ✅ |
| **Exception Handling** | Try-catch appropriés | ✅ |
| **Logging** | SLF4J pour erreurs, System.out pour UI | ✅ |
| **Validation** | Montants, adresses, formats | ✅ |
| **Code Propre** | Commentaires, conventions Java | ✅ |

### 🎯 Fonctionnalités Implémentées

- ✅ Création de wallets (Bitcoin/Ethereum)
- ✅ Génération d'adresses au bon format
- ✅ Création de transactions avec validation
- ✅ Calcul des frais selon le type et la priorité
- ✅ Simulation du mempool avec transactions aléatoires
- ✅ Calcul de position dans la file d'attente
- ✅ Estimation du temps de confirmation
- ✅ Comparaison des 3 niveaux de frais
- ✅ Affichage de l'état du mempool
- ✅ Persistance PostgreSQL via JDBC
- ✅ Menu interactif console

### 🚫 Anti-Patterns Évités

- ✅ Pas de God Class
- ✅ Séparation logique métier / présentation
- ✅ Couplage faible grâce aux interfaces
- ✅ Encapsulation respectée
- ✅ Pas de code dupliqué (DRY)
- ✅ Constantes au lieu de magic numbers
- ✅ Enums au lieu de String/int

### 📈 Performances Techniques

```
- Temps de réponse menu : < 100ms
- Génération transactions aléatoires : < 500ms
- Calcul position mempool : O(n log n)
- Requêtes SQL optimisées avec index
- Gestion mémoire efficace (Stream API)
```

---

## 🎁 Bonus Implémentés

### ✅ Tests Unitaires (JUnit)

```bash
# Structure des tests
src/test/java/
├── service/
│   ├── FeeCalculatorTest.java       # Tests calcul frais BTC/ETH
│   └── MempoolServiceTest.java      # Tests position/temps
└── util/
    └── ValidationUtilTest.java      # Tests validations

# Lancer les tests
javac -cp "bin:lib/*:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar" \
      -d bin src/test/java/com/cryptowallet/**/*.java

java -cp "bin:lib/*:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar" \
     org.junit.runner.JUnitCore com.cryptowallet.service.FeeCalculatorTest
```

**Couverture minimale : 2 tests unitaires** ✅

### ✅ Export CSV

Fonctionnalité d'export des transactions au format CSV :

```java
// Depuis le menu
[6] 📄 Exporter les transactions en CSV

// Fichier généré
transactions_export_2025-09-30_14-30-45.csv
```

Format du CSV :
```csv
ID,FromAddress,ToAddress,Amount,Fees,Priority,Status,Timestamp
550e8400-e29b-41d4-a716-446655440000,0x742d35...,0x8a5f92...,0.5,4.20,STANDARD,PENDING,2025-09-30T14:30:45
```

### ✅ Dockerisation

```dockerfile
# Dockerfile fourni
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY CryptoWalletSimulator.jar .
COPY lib/ ./lib/
CMD ["java", "-cp", "CryptoWalletSimulator.jar:lib/*", "com.cryptowallet.Main"]
```

```bash
# Build de l'image
docker build -t crypto-wallet-simulator .

# Lancer le container
docker run -it --rm --network host crypto-wallet-simulator
```

**Docker Compose** pour l'app + PostgreSQL :
```yaml
version: '3.8'
services:
  db:
    image: postgres:14
    environment:
      POSTGRES_DB: crypto_wallet_db
      POSTGRES_USER: cryptouser
      POSTGRES_PASSWORD: securepass
    ports:
      - "5432:5432"
  
  app:
    build: .
    depends_on:
      - db
    environment:
      DB_HOST: db
      DB_PORT: 5432
```

---

## 🔧 Gestion de Projet

### Git & Branches

```bash
# Stratégie de branches utilisée
main                  # Production stable
├── develop          # Développement principal
    ├── feature/wallet-creation
    ├── feature/transaction-management
    ├── feature/mempool-simulation
    ├── feature/database-integration
    └── feature/ui-improvements
```

### JIRA

Planning et suivi via JIRA :
- 📋
