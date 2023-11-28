Проект автоматизации тестирования веб-приложения, взаимодействующего с СУБД и API Банка.

Перед запуском автотестов необходимо убедиться, что на компьютере, где они будут запускаться, установлены Docker Desktop, IntelliJ IDEA и Java 11.

Как запустить автотесты:

1. Открыть в IntelliJ IDEA терминал(Terminal) и запустить контейнеры командой docker-compose up.

2. Затем поочредно (выбираю один вариант SUT + прогонка тестов):
вариант 1 (с подключением к MySQL) :
   
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar

вариант 2 (с подключением к PostgreSQL
Здесь стоит отменить что перед началом запуска прогонки тестов
на постгрес, 

java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar


3. После того как приложение запустится, в следующей соседней вкладке терминала запустить тесты командой:

gradle test


4. Дальше формируем отчёт allure командой: allureServe и оценить результат прогонки тестовых сценариев по отчёту.
