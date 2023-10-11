### 1. Перечень автоматизируемых сценариев

#### _**Позитивный сценарии:**_
 
Оплата тура дебетовой картой
   
1. **Оплата тура картой со статусом APPROVED**
   **Ожидаемый результат:**
При оплате тура картой со статусом APPROVED появляется сообщение: "Успешно! Операция одобрена банком". 

2. **Оплата тура картой со статусом DECLINED**
   **Ожидаемый результат:**
При оплате тура картой со статусом DECLINED появляется сообщение: "Ошибка! Банк отказал в проведении операции". 

 Оплата тура с помощью кредита

3. **Оплата тура картой со статусом APPROVED** 
   **Ожидаемый результат:**
При оплате тура картой со статусом APPROVED появляется сообщение: "Успешно! Операция одобрена банком".

4. **Оплата тура картой со статусом DECLINED** 
   **Ожидаемый результат:**
При оплате тура картой со статусом DECLINED появляется сообщение: "Ошибка! Банк отказал в проведении операции". 

  #### _**Негативные сценарии**_
 
5. **Проверить ввод невалидных данных в поле "Номер карты". В остальные поля ввести валидные данные.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Номер карты" под полем высвечивается ошибка: "Неверный номер карты" 

6. **Проверить ввод данных с истекшим сроком карты. Остальные поля заполнить валидными данными.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Год" под полем высвечивается ошибка: "Истёк срок действия карты"

7. **Проверить ввод невалидного числа месяца в поле "Месяц". В остальные поля ввести валидные данные.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Месяц" под полем высвечивается ошибка: "Некорректное значение"

8. **Проверить граничащего значения в поле "Год". В остальные поля ввести валидные данные.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Год" под полем высвечивается ошибка: "Некорректное значение"

9. **Проверить ввод имени владельца карты кириллицей. В остальные поля ввести валидные данные.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Владелец" под полем высвечивается ошибка: "Неверно указан владелец карты"

10. **Проверить ввод в имени владельца недопустимых символов: №;%:?. В остальные поля ввести валидные данные.** 
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Владелец" под полем высвечивается ошибка: "Неверно указан владелец карты"

11. **Проверить ввода цифр в поле "Владелец". В остальные поля ввести валидные данные.**
   **Ожидаемый результат:**
При вводе невалидных данных в поле "Владелец" под полем высвечивается ошибка: "Неверно указан владелец карты"

12. **Проверить ввода нижних граничащих значений в поле "Владелец". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе невалидных данных в поле "Владелец" под полем высвечивается ошибка: "Неверно указан владелец карты"

13. **Проверить ввода верхних граничащих значений в поле "Владелец". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе невалидных данных в поле "Владелец" под полем высвечивается ошибка: "Неверно указан владелец карты"

14. **Проверить отправку формы заявки с пустым значением в поле "Номер карты". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе пустых значений под полем высвечивается ошибка: "Поле обязательно для заполнения"

15. **Проверить отправки формы заявки с пустым значением в поле "Месяц". В остальные поля вводим валидные данные.**
    **Ожидаемый результат:**
При вводе пустых значений под полем высвечивается ошибка: "Поле обязательно для заполнения"

16. **Проверить отправку формы заявки с пустым значением в поле "Год". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе пустых значений под полем высвечивается ошибка: "Поле обязательно для заполнения"

17. **Проверить отправку формы заявки с пустым значением в поле "Владелец". В остальные поля вводим валидные данные.**
    **Ожидаемый результат:**
При вводе пустых значений под полем высвечивается ошибка: "Поле обязательно для заполнения"

18. **Проверить отправку формы заявки с пустым значением в поле "CVC/CVV". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе пустых значений под полем высвечивается ошибка: "Поле обязательно для заполнения"

19. **Проверить отправку формы заявки с пустыми значениями во всех полях**
    **Ожидаемый результат:**
При вводе пустых значений под каждым полем высвечивается ошибка: "Поле обязательно для заполнения"

20. **Проверить ввод буквенных символов в поле "Номер карты". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе текста в поле "Номер карты" высвечивается ошибка: "Некорректный ввод данных", или нельзя ввести недопустимые символы в это поле.

21. **Проверить ввод недопустимых символов в поле ввода "Месяц". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе символов в поле "Месяц" высвечивается ошибка: "Некорректный ввод данных", или нельзя ввести недопустимые символы в это поле.

22. **Проверить ввод недопустимых символов в поле ввода "Год". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе символов в поле "Год" высвечивается ошибка: "Некорректный ввод данных", или нельзя ввести недопустимые символы в это поле.

23. **Проверить ввод недопустимых символов в поле ввода "CVC/CVV". В остальные поля ввести валидные данные.**
    **Ожидаемый результат:**
При вводе символов в поле "CVC/CVV" высвечивается ошибка: "Некорректный ввод данных", или нельзя ввести недопустимые символы в это поле.

24. **Проверить отправку формы заявки с именем, содержащим дефис.**
    **Ожидаемый результат:**
При вводе в поле Владелец, двойного имени разделенного через дефис, форма отправляется

25. **Проверить ввод в поле Месяц нулевых значений 00.**
    **Ожидаемый результат:**
При вводе 00 в после Месяц высвечивается ошибка: "Неверный формат"

 ### 2. Перечень используемых инструментов с обоснованием выбора
1. IntelliJ IDEA для написания\проверки кода и запуска\прогона тестов.
2. Gradle -  для управления проектами\ компиляции и сборки исходного кода\ запуска тестов и вывода отчёта.
3. JUnit - фреймворк для модульного тестирования Java-приложений. 
5. Selenide - для автоматизации тестирования веб-приложений на языке Java.
6. Библиотека Facker, для генерации случайных данных, таких как имена, адреса, номера телефонов и т.д. Она полезна при написании автоматических тестов или заполнении тестовых данных.
7. Библиотека Lombok, для упрощения разработки на Java путем автоматической генерации кода.
8. Docker для обеспечения работы с каталогом gate-simulator и для работы с БД.
9. Git/GitHub для размещения проекта в общем доступе для проверки и корректировки.
10. Google Chrome браузер для просмотра веб страниц и отчета о тестах.
11. DevTools в браузере для поиска css-селекторов на страницах.
12. Monosnap для скриншотов и записи экрана.

### 3. Перечень и описание возможных рисков при автоматизации
1. Риски превысить срок выполнения задач.
2. Риски возникновения сложности с запуском и настройкой окружения, в частности с запуском эмулятора, т.к впервые запускаю приложение на Node.JS.
3. Риски возникновения сложностией при написании PageObjects и тестов, с использовнием этих PageObjects.
4. Риски с выводом отчётов тестирования.
5. Риски личного характера: будь то работа, близкие, здоровье.

### 4. Интервальная оценка с учётом рисков в часах

1. Подготовка к проведению тестирования (подготовка проекта, изучение технического задания, установка программного обеспечения, запуск SUT): 20 часов
2. Написание плана тестирования: 5 часов.
3. Написание автотестов: 40 часов.
4. Подготовка отчетов о проведении тестирования: 8 часов.

### 4. План сдачи работ: когда будут готовы автотесты, результаты их прогона
Автотесты будут готовы к 19.10.2023, результаты их прогона будут готовык 22.09.2023
