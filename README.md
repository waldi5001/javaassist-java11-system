# javaassist-java11-system

```
java -version
openjdk version "11.0.9" 2020-10-20
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9+11)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9+11, mixed mode)

git clone git@github.com:waldi5001/javaassist-java11-system.git

mvn clean install

java -Djdk.attach.allowAttachSelf=true -cp target/classes:target/javassist-3.28.0-GA.jar de.fk.javaassist.Main
```