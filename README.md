Jest to prosta aplikacja wyświetlająca wiadomości regionalne.
Składa się z serwera usług REST pośredniczącego w wywołaniach do zdalnego api (newsapi.org) oraz prostej aplikacji Web reprezentującej wyniki.

Cechy aplikacji:
- zbudowanie i deployment aplikacji sprowadza sie do jednego polecenia
- warstwa widoku stworzona zgodnie z koncepcją RWD z użyciem flexboxa, przyjęte breakpointy dla szerokości 768px i 1200px
- filtrowanie po państwach i po technologiach, wyszukiwanie po tytułach
- stronnicowanie wyników
- użyta biblioteka komponentów PrimeNG do prezentacji newsów


Technologie:
Backend: Java 8, Spring 5 (rest, webflux)
Frontend: angular 8, PrimeNG, typescript, SASS


## Build

```bash
mvn clean install
```

## Testy

```bash
mvn test
```

## Uruchomienie
 
```bash
cd server
mvn spring-boot:run
```
lub
```bash
java -jar server/target/server-1.0-SNAPSHOT.jar
```

## Docker
 
```bash
docker build -t newsapireader .
docker run -p 8080:8080 newsapireader
```

## JavaDoc

```bash
cd server
mvn javadoc:javadoc
```

dokumentacja zostanie wygenereowana w: /server/target/site/apidocs

