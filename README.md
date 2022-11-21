# Notre projet
La classe ATM reproduit le déroulement simplifié d'une opération de retrait de liquide à un distributeur:
1. Le client sélectionne le montant du retrait
2. On vérifie que le montant est disponible dans la machine
3. On réalise le paiement du montant avec la carte de l'utilisateur
4. On délivre les billets

La classe ATM s'appuie sur trois composants pour mener sa tâche à bien:
* AmountSelector: la méthode selectAmount() correspond à l'étape 1
* CashManager: les méthodes canDeliver(int) pour l'étape 2 et deliver(int) pour l'étape 4
* PaymentProcessor: la méthode pay(int) pour l'étape 3

Le code inclut une interface pour chacun de ces trois composants mais pas d'implémentation.

# Objectif de l'exercice
Alimenter la classe de test ATMTest avec tous les cas de test unitaire de la classe ATM que vous jugerez utiles.
Attention: un bug s'est glissé dans l'implémentation d'ATM. Saurez-vous le débusquer ?