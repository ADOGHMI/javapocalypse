TD Java
=======

**Équipe *Javapocalypse***

Responsable : DOUÉZAN-GRARD Guillaume

Codeurs :

- BALAFREJ Salma
- BENKIRANE Mohamed
- EL MOUMNI Mohammed
- DOGHMI Amine

_Note : Ce PDF a été généré automatiquement par [Pandoc](http://johnmacfarlane.net/pandoc/)
a partir d'un fichier source en Markdown. Si vous préférez une version LaTeX
avec un habillage particulier, nous pouvons aussi exporter vers du LaTeX et
compiler par la suite avec les sources de l'habillage._

TD 0
----

La version de départ de la simulation d'un autobus et de ses passagers stocke
une référence vers les passagers entrées dans le bus dans un tableau. On ne
peut pas dire ce qu'il se passe lorsqu'un passager descend. En effet, tout ce
qui est effectué dans cette version est de diminuer le nombre de passagers dans
le bus, et donc le pointeur sur la case courante du tableau. Il en résulte que
si l'on fait sortir un passager qui n'est pas le dernier entré, le dernier
pointeur dans le tableau sera remplacé par celui du prochain passager qui
rentrera.  Évidemment, celui qui est déjà sortit est toujours dans le tableau.
Cette représentation est inconsistante.

Une solution serait d'implémenter ce stockage à l'aide d'une liste chaînée.
Lorsque l'on supprime un passager, on l'enlève tout simplement de la liste
chaînée (en se basant sur le pointeur vers le passager).

L'encapsulation proposée pour la version équivalente en Java correspond pour la classe `Autobus` à la réalisation contenue
dans `autobus.c`, et pour `PassagerStandard` à celle contenue dans
`ps_standard.c`.

TD 1
----

La dernière version stable de Java est la version 1.8 (ou abrégée 8).

### Le Kit de Développement de Java

La compilation vers du bytecode fait apparaitre des fichiers _.class_ associés
au fichiers _.java_ compilés.

L'exécution avec l'extension de fichier _.class_ amène l'interpréteur à
rechercher la classe `MaClasse.class` au lieu de `MaClasse` comme on l'aurait
souhaité. On ne doit donc pas ajouter le suffixe _.class_, l'interpréteur
d'ailleurs ne le propose pas en auto-complétion.

Pour déterminer la version de la _JVM_ installée, on peut exécuter la commande
`java -version`. On utilise la même option pour déterminer la version du
compilateur.

Le _Java Runtime Environment_ (ou _JRE_) signifie environnement d'exécution, par
opposition au _Java Developement Environement_ (ou _JDE_) qui signifie environnement
de développement. Dans le _JRE_, on ne peut pas utiliser `javac`, pour la
compilation d'un programme Java.

### La classe String

La classe `String` appartient à la hiérarchie `java.lang`. Pour connaitre le
nombre de caractères d'une chaine, on utiliserait la méthode `length()`. Pour
obtenir une sous-chaîne, on utiliserait la méthode (surchargée) `substring(int
beginIndex, [int endIndex])`.

La méthode `toString()` est un exemple de méthode héritée de la classe `java.lang.Object`.

L'objectif de l'interface `Comparable<String>` est de pouvoir comparer des
chaînes de caractères. Le fait que la classe `String` implémente cette
interface signifie que sont définies dans la classe `String` l'ensemble des
méthodes de l'interface `Comparable<String>`, c'est à dire la méthode `int
compareTo(String s)`.

Ce n'est pas tant la notation `<>` que la notation `<T>` qui est intéressante,
qui va substituer à l'exécution le type `T` par le type `String` pour
l'interface générique `Comparable<T>`. On en déduit sans surprise qu'est
implémentée dans la classe `String` la méthode `int compareTo(T t)` en tant que
la méthode explicité ci-dessus.

La classe `java.lang.StringBuffer` permet de manipuler des chaines de caractères
mutables. On pourrait se demander pourquoi la classe `String`
n'implémente-t-elle pas elle aussi les avantages de mutabilité comme la classe
`StringBuffer`. Et bien il s'agit ici d'un problème rencontré en *C*. Lorsque
l'on utilise une chaîne de caractère en *C* en les mettant directement dans le
code, elles sont placées dans une zone mémoire spéciale correspondante aux
*segment de données*, et vont rester immutables. Si l'on utilise plusieurs fois
la même chaîne de caractère en la "recopiant" dans le code, le pointeur vers
celle-ci sera identique. La classe `String` se comporte de la même façon. Ceci
permet principalement de gagner en complexité sur quelques opérations :
complexité mémoire à l'initialisation (on ne crée qu'une référence, l'objet
n'est pas dupliqué), et complexité en temps pour l'opération de hashage : la
chaine et immutable, par conséquent sont *hashcode* est identique et est gardé
en cache pour utilisation future.

Il n'y a pas de lien "est-un" avec l'interface `Comparable`. La raison évoquée
dans l'API est qu'il est conseillé que `a.compareTo(b) == a.equals(b)`. Comme
l'on peut créer plusieurs objets `StringBuffer` avec le même contenu, cette
condition n'est pas vérifiée. Cela provoquerait des problèmes dans les sets
ordonnés (deux objets différents avec le même contenu ne pourraient pas être
ajoutés).

Ce n'est pas si grave, on peut convertir une `String` en `BufferString` et
inversement avec l'exemple ci-dessous :

```java
String string_cons   = "Hello World!";
BufferString bstring = new BufferString(string);
String string_cons2  = bstring.toString();
```

Le mot clé `final` pour une variable signifie qu'elle ne peut pas être
réassignée. Il s'agit donc d'imposer que la référence reste constante, vers un
objet de type `StringBuffer` par exemple (l'objet en lui-même peut changer).

`chaîne` et `EXCLAMATION` représentent des instances de la classe `String`, `System`
est une classe, `out` est un attribut de la classe `System` correspondant à un flux
de sortie (un objet de type `PrintStream`) et `println()` une méthode. Dans
l’expression `System.out.println(EXCLAMATION)`, l’envoi du message `println()` se
fait vers une instance de la classe `PrintStream`. Dans la documentation
de `System`, plusieurs définitions de la méthode `println()` sont données. Elle
sont différenciées par les paramètres de la méthode (types et nombres).

D’après la documentation de la classe `String`, l’opérateur `+` permet la
concaténation des instances de cette classe. L’implémentation de cet opérateur
est faite via la méthode `void append(String s)` de la classe `StringBuffer` (modulo des
conversions éventuelles des opérandes vers le type `String`). Il peut cependant
y avoir des problèmes de performances : les chaînes intermédiaires peuvent être
calculée et stockée comme nouvelle instance.

Le code suivant permet de vérifier les propriétés des `String` avec l'initialisation
par tableau de `char` :

``` java
char valeur[] = {'K', 'a', 'l', 'k', 'i'};
String s1 = new String(valeur);
String s2 = new String(valeur);
System.out.println(s1.intern() == s2.intern());
System.out.println(s1.intern() == "Kalki");
```

Dans cet exemple, il y a création de trois instances distinctes : une pour la `String “Kalki”`, et
deux autres pour `s1` et `s2`, ce qui du point de vue de l’utilisation mémoire,
n’est pas optimal pour représenter une seule et même chaîne de caractères.

On constate que dans le code source  de la classe `String` d'OpenJDK, les
commentaires de la réalisation sont découpés en deux parties : des
commentaires destinés à la documentation de la classe et ceux relatifs à
l’implémentation. Les commentaires à destination de _Javadoc_ sont rédigés au
format html et intégrés comme suit :

``` java
/** Ce commentaire est
 * est destiné à Javadoc
 */
```

avant la déclaration d’une classe, d’une méthode ou d’un attribut.
Ceci permet à _Javadoc_ de générer une
documentation automatiquement. Les commentaires de programmation sont
disséminés dans le code et sont soit sur une ligne :

``` java
// Mon commentaire sur une ligne
```

soit sur plusieurs lignes :

``` java
/* Ceci est
 * mon commentaire.
 */
```

On remarque que dans cette implémentation de la classe `String`, chaînes sont
stockées dans un tableau de `char` constant et privé, déclaré
donc comme suit : `private final char value[]`.

La méthode `int equals(String s)` compare les chaînes caractère par caractère, tandis que
l’opérateur `==` compare des références vers des chaines de caratères.

Le mot clé native dans la déclaration de la méthode `intern()` signifie que son
code a été implémenté en utilisant la _Java Native Interface_, c’est à dire en
utilisant du code natif, en C par exemple.

### Un coup d'œil sur le monde Java

Java a été conçu par _Sun Microsystems_. Le nom interne de _Java SE 8_ est
_Spider_.  Le nom donné à la JVM est _HotSpot_.

_Java EE (Java Entreprise Edition)_ sert à développer des applications
multi-niveaux dans des entreprises.

_Java FX_ est l’outil de création d’interface graphique officiel du langage
Java.

_Java ME (Java Micro Edition)_ fournit un environnement robuste et
flexible pour les applications qui s'exécutent sur des périphériques intégrés
et mobiles : téléphones mobiles, décodeurs, lecteurs Blu-ray, appareils
multimédia numériques, modules M2M, imprimantes, etc.

La machine virtuelle d’Android s’appelle _Dalvik_, développée par _Dan Bornstein_
(responsable de l’équipe de développement).

### Commentaires

EL MOUMNI Mohammed : Ce TD a été pour moi l'occasion de
découvrir l’approche orienté objet à travers le langage Java. J’ai pu découvrir
des outils de développement comme _JDK_, _JRE_ (ne sert pas au développement) et
_JVM_, et me familiariser avec la documentation de l’API Java.

BENKIRANE Mohamed :  Ce TD est le premier contact avec le monde Java, j’ai
compris en général l’approche objet, par contre il faudrait que je me
familiarise un peu plus avec la terminologie.  Ce qui me motive c’est que Java
est un langage facile a déboguer, c’est un bon signe, et c'est un langage très
utilisé.


BALAFREJ Salma : Ce TD m’a permis de me familiariser avec le Java. Tout d’abord
en comprenant les différences avec la programmation impérative, puis en
découvrant les principaux outils de développement nécessaires à la
programmation orientée objet en Java.

DOUÉZAN-GRARD Guillaume : Ce TD m'a permis d'encadrer mon équipe et de les aider
dans leur découverte du Java. J'attends quant à moi de commencer à programmer.

DOGHMI Amine : Ce TD m’a permis d’apprehender les nouveaux outils nécessaires
pour programmer en java. Mais c’était surtout l’occasion pour moi de
restructurer les idées préalables que j’avais de la programmation objets et de
commencer par une base plus fondée.
