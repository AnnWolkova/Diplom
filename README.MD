Проект автоматизации тестирования веб-приложения, взаимодействующего с СУБД и API Банка.

Перед запуском автотестов необходимо убедиться, что на компьютере, где они будут запускаться, установлены Docker Desktop, IntelliJ IDEA и Java 11.

Как запустить автотесты:

1. Открыть в IntelliJ IDEA терминал(Terminal) и запустить контейнеры командой docker-compose up.

2. После того как контейнеры запустятся, в соседней вкладке терминала нужно запустить gate-simulator
командами: cd ./gate-simulator и npm start

3. Затем поочредно (выбираю один вариант SUT + прогонка тестов):
вариант 1 (с подключением к MySQL) :
java -jar ./aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass

вариант 2 (с подключением к PostgreSQL
Здесь стоит отменить что перед началом запуска прогонки тестов
на постгрес, необходимо раскомментировать ту часть файла  application.property, которая  относится к данной базе) :

java -jar ./aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:5432/app -P:jdbc.user=app -P:jdbc.password=pass

4. После того как приложение запустится, в следующей соседней вкладке терминала запустить тесты командой:

вариант 1 (с подключением к MySQL) gradle test

5. Оценить результат прогонки тестовых сцекнариев по ссылке: file:///C:/Users/a.volkova/Desktop/Diplom/Diplom/build/reports/tests/test/index.html
