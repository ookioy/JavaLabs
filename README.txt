ЛР 1: Основи Java та модифікатори доступу

Мета: Навчитися створювати базові класи з використанням модифікаторів доступу та принципів ООП (інкапсуляція, спадкування, поліморфізм).

Завдання:

    Створити не менше трьох класів.
    Створити пакет ua.util із класами:
    ValidationHelper – package-private, методи перевірки даних;
    Utils – public, використовує ValidationHelper.


Використати модифікатори доступу:

    private – для полів;
    public – для getter/setter, конструкторів і основних методів;
    protected – для полів та методів базового класу (якщо потрібен базовий клас);
    package-private – для допоміжних класів та методів.


Реалізувати:

    конструктори (з використанням super() при необхідності);
    методи toString(), equals(), hashCode();
    статичні factory-методи для створення об’єктів;
    валідацію даних у сеттерах та конструкторах.


Створити демонстраційний клас Main, що показує:

    створення об’єктів різними способами;
    роботу валідації та форматування;
    доступ до protected та package-private членів;
    різні сценарії використання класів (успішні та неуспішні).

7. Hotel Management

    Room: roomNumber, type, capacity, price
    Guest: firstName, lastName, email, checkInDate
    Reservation: guest, room, startDate, endDate
    Service: name, price
    Invoice: reservation, totalAmount, issueDate
