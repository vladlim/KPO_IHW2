# Описание проекта

Проект представляет собой систему управления рестораном, которая включает в себя различные классы для работы с меню, заказами, клиентами и обратной связью.

## Классы

### 1. Main
Основной класс приложения, содержащий метод `main`, который запускает программу. Отсюда вызываются методы для авторизации пользователей и сохранения меню в CSV файл.

### 2. Dish
Класс, представляющий блюдо в меню ресторана. Содержит информацию о названии, цене и времени приготовления блюда.

### 3. Order
Класс, описывающий заказ клиента. Содержит список блюд, их статус (принят, готовится, готов) и методы для добавления блюд, отмены заказа и оплаты.

### 4. Orders
Синглтон, представляющий список заказов. Позволяет добавлять и удалять заказы, а также отображать список текущих заказов.

### 5. RestaurantClient
Класс, представляющий клиента ресторана. Содержит методы для создания заказа, добавления блюд в заказ, оплаты заказа и ожидания его готовности.

### 6. Menu
Синглтон, представляющий меню ресторана. Включает в себя список блюд, а также методы для редактирования меню и сохранения его в CSV файл.

### 7. Feedback
Класс, представляющий обратную связь от клиента. Содержит оценку заказа и комментарий к нему.

### 8. UserMenu
Класс, управляющий пользовательским интерфейсом. Предоставляет методы для авторизации администратора или клиента, а также для взаимодействия с пользователем.

## Взаимодействие классов

- Классы `Main`, `UserMenu` и `RestaurantClient` обеспечивают взаимодействие с пользователем.
- Классы `Order`, `Orders` и `Menu` управляют заказами, списком заказов и меню соответственно.
- Классы `Feedback` и `RestaurantClient` обеспечивают возможность клиенту оставить обратную связь о заказе.
- Классы `Dish`, `Order` и `Menu` работают вместе для представления и управления меню и блюдами.

## Запуск приложения

Для запуска приложения необходимо выполнить метод `main` класса `Main`.

## Инструкции по использованию

1. При запуске программы пользователю предлагается авторизоваться как администратор или клиент.
2. В зависимости от выбранной роли доступны различные функциональности, такие как просмотр заказов, редактирование меню, создание заказов и т.д.
3. После завершения заказа клиент может оценить его и оставить комментарий.
4. Данные о заказах и обратной связи сохраняются и могут быть просмотрены администратором.
5. При повторной авторизации клиенту нужно будет ввести 'y', если он зарегистрирован, затем присвоенный ему номер.
6. При авторизации нового клиента, ему нужно ввести 'n', затем ему будет присвоен его номер.
7. На каждом этапе пользователю выводится меню функций, нужно ввести номер пункта для перехода к его исполнению.
8. При некорректном вводе пользователя попросят ввести корректные данные.
9. При  завершении программы пользователю предоставят итоговую выручку ресторана за сеанс.


## Использованные паттерны

1. Синглтон для меню и списка заказов, так как они единственные для всех потоков и классов, в которых используются.
2. Итератор для блюд, чтобы по ним можно было итерироваться.

## Автор

Лим Владислав БПИ225
