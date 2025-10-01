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

### 📐 Diagramme de Couches


### 🔗 Classes Principales et leurs Relations

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

