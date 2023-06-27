Do stworzenia wielomodułowego jara wykorzystałem aplikacje z laboratorium 1.\
Projekt jest zrobiony w wersji modułowej aby Intelij nie zwracał błędu, że klasa jest zadeklarowana wiele razy.\
Wykorzystane wersje javy: java11, java15, java17

Do skompilowania klas skorzystałem z skryptu compile.sh
```bash
#!/bin/bash
for i in "$@"; do
javac --release "$i" java"$i"/src/main/java/*.java -d compiled_java"$i"
done
```
Wywołanie skryptu ./compile.sh 11 15 17 kompiluje wszystkie pliki źródłowe w odpowiednich wersjach tworząc katalogi ze skompilowanymi klasami compiled_java11, compiled_java15, compiled_java17.
Do utworzenia wielowydaniowego jar skorzystałem ze skryptu create_jar.sh\
```bash
#!/bin/bash
jar --create --file app.jar --main-class Main -C compiled_java11 . --release 15 -C compiled_java15 . --release 17 -C compiled_java17 .
```
Do ustawienia aktualnej wersji javy skorzystałem z polecenia:
```bash
sudo update-alternatives --config java
```
output polecenia\
```bash
Selection    Path                                         Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/jdk-15.0.2/bin/java              1500      auto mode
  1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java   1111      manual mode
* 2            /usr/lib/jvm/java-17-oracle/bin/java          1091      manual mode
  3            /usr/lib/jvm/jdk-15.0.2/bin/java              1500      manual mode
  
Press <enter> to keep the current choice[*], or type selection number: 
```
Musiałem też dodać jave15 do alternatives. Zrobiłem to poleceniem:
```bash
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-15.0.2/bin/java 1500
```
Utworzyłem instalator wykorzystując polecenie
```bash
jpackage --name myapp --input ./ --main-jar app.jar --main-class Main
```




