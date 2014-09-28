<!--
Repository : https://github.com/SilverWyrda/javapocalypse

How to contribute : 
    * Create a Github account
    * Send your username at gouezangrard@enseirb-matmeca.fr
    * You will be added as a contributor and allowed to push
-->

TD2 Java
========

**Équipe *Javapocalypse***

- *Coordinatrice :* BALAFREJ Salma
- *Tandem 1 :* `JaugeNaturel`
    - BENKIRANE Mohamed
    - DOUÉZAN-GRARD Guillaume
- *Tandem 2 :* `EtatPassager`
    - EL MOUMNI Mohammed
    - DOGHMI Amine

_Note : Ce PDF a été généré automatiquement par [Pandoc](http://johnmacfarlane.net/pandoc/) a partir d'un fichier source en Markdown. Notre dépôt git est accessible à l'adresse suivante : [Javapocalypse Github](https://github.com/SilverWyrda/javapocalypse)._

#### Introduction

Après la première partie du projet qui s'intéressait aux différents outils de bases pour développer en Java, la deuxième partie s'occupe de l'implémentation de différents tests visant à détecter les bogues et vérifier la conformité de l'implémentation des classes  `EtatPassager` et `JaugeNaturel` avec le cahier des charges.

`EtatPassager` représente l'encapsulation de `PassagerStandard`, elle présente le passager sous trois états : en dehors de l'`Autobus` et à l'intérieur : assis ou debout.

`JaugeNaturel` représente quant à elle l'encapsulation de `Autobus`. Trois cas sont alors distingués :  `estBleu()`, `estRouge()` et `estVert()` qui représentent respectivement si le nombre de personnes est en dehors ou à l'intérieur de l'intervalle $[\text{personneMin}, \text{personneMax}-1]$.

Ce rapport présentera tout d'abord certains tests unitaires, nous parlerons par la suite du système de paquetage Java et enfin, nous finirons avec des modifications de la classe `JaugeNaturel`.


Les tests unitaires du développeur
-------------------------------

Le mécanisme des `asserts` représente l'un des fondements des tests unitaires. En effet, l'exécution s'interrompt automatiquement lorsque un des tests n'est pas validé tout en renvoyant la pile d'appel au moment de l'exception. C'est donc ce mécanisme qui sera utilisée dans l'implémentation de nos tests. L'utilisation des assertions nécessite l'exécution avec l'option `-ea` permettant la prise en compte des asserts. Si nous exécutons le programme sans l'option `-ea`, les assertions ne produisent plus d'exceptions. Ceci est utile pour un utilisateur dans le cas où certains tests bien identifiés par l'équipe de développement ne sont pas validés mais sans incidence sur l'utilisation.

### Ecriture du premier test

De façon générale, nos classes de test vont contenir un attribut de type correspondant à la classe à tester. Nous avons choisis d'utiliser une méthode pour modifier cet attribut en le remplaçant par une nouvelle instance. Ainsi, l'instanciation de la classe `Classe` dans une méthode de la classe de test correspondante se fait par :
    
``` java
this.instanceDeClasse = new Classe(String str...);
```

Elle se retrouve ainsi centralisée, et on pourra répondre facilement à un changement de nom par exemple. On aurait pu faire le choix d'avoir une variable locale à chaque test qui instancie la classe à tester, mais cette approche est moins générique.

Nous avons aussi centralisé nos assertions, de telle sorte que l'appel au méthodes `estBleu()`, etc, se retrouve dans une seule méthode. Un exemple d'appel est le suivant :
    
``` java
this.jauge.estBleu();
```

Concernant enfin l'attribut lui-même, nous avons choisi de le mettre en `private` puisque c'est à la classe de test seule de gérer son état pour enchainer les différents tests. Ainsi, le fait que la classe ait le contrôle exclusif sur cet attribut assure sa propre validité.

La question maintenant est de savoir comment récupérer à partir d'un fichier compilé `.class` le fichier source `.java` qui l'a généré. C'est tout à fait possible, mais dans notre cas nous allons nous restreindre à l'utilisation de `javap`, et plus particulièrement aux options `-p`, qui permet de lister tous les membres d'une classe (incluant les membres privés), et `-c`, qui permet de désassembler le _bytecode_.

#### Exécuter ce test

Nous souhaitons travailler sur un objet, par conséquent, la première étape est d'instancier. La seconde étape est d'appeler comme précédemment la méthode voulue sur l'objet créé. Par exemple :

``` java
    ClasseTest test = new ClasseTest(String str...);
    test.lancer();
```

### Boutez vos Neuronnes

#### Classe `LancerTests`

Le code pour détecter la présence ou non de l'option `-ea` fait un peu _hakish_, mais il se base sur deux choses : d'une part le fait que si l'option n'est pas présente, les assertions ne seront pas excutées et donc la variaable `estMisAssertion` restera `false`, et d'autre part, sur le fait que l'affectation `estMisAssertion = true` s'évalue à `true`, et donc le `assert` n'est pas bloquant avec l'utilisation de l'option.

Ce type de code s'il est mal utilisé peut tout simplement bloquer l'exécution d'un programme ou compliquer les opérations de tests, c'est donc à utiliser avec parcimonie.

#### Classe `EtatPassager`

Les attributs de la classe `EtatPassager` sont déclarés privés, et aucune méthode de cette classe ne permet de modifier l'état d'un passager. Ainsi les instances de cette classes sont des objets constants.

Le choix qui a été fait si l'on se réfère au diagramme de classes est qu'un `PassagerStandard` possède un `EtatPassager`. Quand l'on considère un passager, on va suivre un seul et même passager et par conséquent, on va suivre son instance. En revanche, concernant l'état d'un passager, on a affaire à des états **différents**, par conséquent il est plus juste de travailler sur des objets eux aussi différents. En ayant un objet fixé à l'instanciation, on est forcé de travailler ainsi.

A chaque changement d'état, un `PassagerStandard` voit son objet `EtatPassager` remplacé par une nouvelle référence.

Et c'est justement parce-qu'une référence est bien plus légère qu'un objet entier que l'on préfère avoir des objets immutables. On ne fait ainsi que copier des références, au lieu de copier l'objet entier. Mais attention, ceci fonctionne bien ici, mais ce ne serait plus valable si l'on autorisait par exemple un description de l'état `ASSIS` par exemple comme "En train de dormir.". Il faut donc s'adapter au cas rencontré pour décider de l'utilisation ou non de l'immutabilité.

Un paquetage `tec`
------------------

### Arborescence du projet

Dans l'organisation séparant fichiers sources dans `src` et fichiers compilés dans `build`, l'arborescence du projet doit être respectée pour les fichiers compilés. `tec.test.ClassTest` est respecté par l'arborescence suivante :

```
└── build
    └── tec
        ├── test
        │   ├── ClasseTest.class
        └── lyoko
            └── Classe.class
```

### Inclure des classes dans le paquetage

Au début de chaque fichier source doit figurer l'instruction `package arbo.resc.ense;`. La seule classe accessible en dehors du package est `tec.Autobus`. Les autres classes sont des utilitaires d'`Autobus` et n'ont pas de sens sans ce dernier.

### Compilation

L'option `-d` de `javac` permet de spécifier l'emplacement où vont être générés les `.class`.

### Exécution des tests

`java -ea -cp build/ tec.LancerTests` permet de lancer l'exécution des tests unitaires depuis le _root_ du projet. En particulier, l'option `-cp <path>` permet de spécifier un ou plusieurs répertoires de recherche des classes.

### Boutez vos neuronnes

**<!> DONNER LE MESSAGE D'ERREUR !**

L'ambiguité provient du fait que `tec.LancerTests` est interprété comme un arborescence, dans laquelle se trouve la classe `class`. Bien évidemment, ce n'est pas le cas.

L'encapsulation Jauge Naturel
-----------------------------

### Changement de la réalisation de JaugeNaturel

L'attribut de type `int` `valeur` est replacé par un attribut de type `BigDecimal`, qui contient donc une référence vers un objet de type `BigDecimal`. Un lien "a-un" est créé.

### Influence de la réalisation sur le code client

L'attribut `valeur` ayant changé de type, nous devons aussi modifier le type de retour du _getter_ `int valeur()`. Cette modification doit être prise en compte dans le test de dépassement, puisqu'au lieu de travailler avec des `int`, on travaille maintenant avec de très gros nombres de type différent.

La comparaison entre objets de types différents ou de type 'BigDecimal' avec les outils ordinaires (!=, == ...) est impossible. On utilise alors la méthode compareTo(BidDecimal) qui nous offre la possibilité de convertir deux BigDecimal.

Sans la méthode `valeur` on aurait rien changé dans le code car la méthode `testDeplacement()` ne fait qu'appeler les méthodes de `JaugeNaturel` pour vérifier les assertions.

**SANS LA METHODE VALEUR ?**

#### Conclusion

#### Commentaires

EL MOUMNI Mohammed : Ce TD a été pour moi l'occasion de découvrir le mécanisme des assert très utile pour la réalisation des tests unitaires. J'ai aussi pu me familiariser avec la réalisation et la compilation de paquetages.
