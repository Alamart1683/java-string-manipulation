package Lab_2;

// Импорты классов
import java.util.Scanner;
import java.lang.StringBuilder;

// Основной класс
public class Lab_2_2
{
    public static StringBuilder text = new StringBuilder(); // Текст
    public static StringBuilder code = new StringBuilder(); // Код

    // Главный метод
    public static void main(String[] args)
    {
        int command = 0;
        Scanner input = new Scanner(System.in);
        // Вывод списка опций
        outputMenu();
        do
        {
            System.out.print("Введите команду: ");
            // Проверка ввода переменной выбора
            while (!input.hasNextInt())
            {
                System.out.println("Неверно введена команда");
                System.out.println();
                System.out.print("Введите команду: ");
                input.next();
            }
            command = input.nextInt();
            // Обработка выбора
            switch (command)
            {
                case 1:
                    // Ввести текст
                    input_text();
                    break;
                case 2:
                    // Из первого и третьего предложений удалить последние слова
                    delete_last_words();
                    break;
                case 3:
                    // В текст добавить новое предложение из двух слов
                    add_new_sentence();
                    break;
                case 4:
                    // Вставить новое слово во второе предложение после первого слова
                    add_new_word();
                    break;
                case 5:
                    // В какие переменные кода осуществлялся ввод с клавиатуры (Задание 1)
                    objects_string();
                    break;
                case 6:
                    // Повторный вывод меню
                    outputMenu();
                    break;
                case 0:
                    System.out.println("Программа завершена");
                    break;
                default:
                    System.out.println("Неверно введена команда");
                    System.out.println();
                    break;
            }
        }
        while (command != 0);
    }

    // Метод вывода меню задания 2
    public static void outputMenu()
    {
        System.out.println();
        System.out.println("Меню программы:");
        System.out.println("1. Ввести текст");
        System.out.println("2. Из первого и третьего предложений удалить последние слова");
        System.out.println("3. В текст добавить новое предложение из двух слов");
        System.out.println("4. Вставить новое слово во второе предложение после первого слова");
        System.out.println("5. В какие переменные кода осуществлялся ввод с клавиатуры (Задание 1)");
        System.out.println("6. Повторный вывод меню");
        System.out.println("0. Завершение программы");
        System.out.println();
    }

    // Метод ввода текста
    public static void input_text()
    {
        System.out.println("Введите текст:");
        Scanner input = new Scanner(System.in);
        text = new StringBuilder(input.nextLine());   // Ввод текста
        System.out.println("Текст введен");
        System.out.println();
    }

    // Метод удаления из первого и третьего предложений последних слов
    public static void delete_last_words()
    {
        // Проверка введенности кода
        if (text.toString().equals(""))
        {
            System.out.println("Ошибка: не был введен текст");
            System.out.println();
        }
        else
        {
            int sentences_count = 0; // Счетчик предложений
            int current_position = 0; // Позиция конца текущего предложения в тексте
            int buffer_position = 0; // Позиция конца предложения для поиска слов
            String point = "."; // Строка содержащая точку
            // Поиск конца первого предлложения в тексте
            current_position = text.indexOf(point);
            // Цикл прохода по тексту
            while (current_position != - 1)
            {
                sentences_count++;
                buffer_position = current_position - 1;
                // Рассматриваются только первое и третье предложения
                if (sentences_count == 1 || sentences_count == 3)
                {
                    // Цикл поиска последнего слова в предложении
                    while (text.charAt(buffer_position) != ' ' && buffer_position > 0)
                    {
                        if (buffer_position > 0)
                        {
                            buffer_position--;
                        }
                    }
                    // Проверка на последние слова
                    if (buffer_position > 0 && text.charAt(buffer_position - 1) != '.')
                    {
                        //Удаление последнего слова в предложении
                        text = text.delete(buffer_position, current_position);
                    }
                }
                // Определение конца следующего предложения
                current_position = text.indexOf(point, current_position + point.length());
            }
            System.out.println("Текст после удаления последних слов из первого и третьего предложений: ");
            System.out.println(text);
            System.out.println();
        }
    }

    // Метод добавления в текст нового предложения из двух слов
    public static  void add_new_sentence()
    {
        // Проверка введенности кода
        if (text.toString().equals(""))
        {
            System.out.println("Ошибка: не был введен текст");
            System.out.println();
        }
        else
        {
            Scanner input = new Scanner(System.in);
            StringBuilder new_sentence = new StringBuilder(); // Добавляемое предложение
            System.out.println("Введите два слова нового предложения:");
            System.out.println("Первое слово: ");
            StringBuilder first_word = new StringBuilder(input.next()); // Первое слово
            System.out.println("Второе слово: ");
            StringBuilder second_word = new StringBuilder(input.next()); // Второе слово
            // Сборка нового предложения
            new_sentence.append(' ').append(first_word).append(' ').append(second_word).append('.');
            // Добавление предложения предложения из двух слов в текст
            text.append(new_sentence);
            System.out.println("Текст после добавления в конец предложения из двух слов: ");
            System.out.println(text);
            System.out.println();
        }
    }

    // Метод добавления второго слова во второе предложение текста
    public static void add_new_word()
    {
        // Проверка введенности кода
        if (text.toString().equals(""))
        {
            System.out.println("Ошибка: не был введен текст");
            System.out.println();
        }
        else
        {
            int current_position = 0; // Позиция конца текущего предложения в тексте
            String point = "."; // Строка содержащая точку
            String space = " "; // Строка содержащая пробел
            Scanner input = new Scanner(System.in);
            String word = ""; // Добавляемое слово
            System.out.println("Введите слово: ");
            word = input.next();
            // Поиск конца первого предложения в тексте
            current_position = text.indexOf(point);
            // Переход к новому предложению
            current_position = current_position + point.length();
            // Позиция начала первого слова во втором предложении
            current_position = text.indexOf(space, current_position);
            // Позиция начала второго слова во втором предложении:
            current_position = text.indexOf(space, current_position + space.length());
            // Вставка нового слова
            text.insert(current_position, space + word);
            System.out.println("Текст после добавления нового слова на позицию второго во втором предложении: ");
            System.out.println(text);
            System.out.println();
        }
    }

    // Метод ввода кода
    public static void input_code()
    {
        System.out.println("Введите код:");
        Scanner input = new Scanner(System.in);
        code = new StringBuilder(" " + input.nextLine() + " ");   // Ввод кода
        System.out.println("Код введен");
    }

    // Метод составления списка(строки) используемых объектов (Задание 1):
    public static void objects_string()
    {
        // Ввод кода
        input_code();
        StringBuilder objects_string = new StringBuilder("Список объектов:"); // Строка(список) объектов
        int start_length = objects_string.length();
        int current_position = 0; // Текущая позиция искомого элемента
        int buffer_position = 0; // Буферная позиция
        StringBuilder current_object = new StringBuilder(); // Текущее имя объекта
        StringBuilder current_object_type = new StringBuilder(); // Текущий тип объекта
        StringBuilder new_string = new StringBuilder(" = new"); // Строка паттерна объявления объекта
        // Поиск строк
        current_object_type.append("String");
        // Первый цикл прохода по коду
        while (current_position != - 1)
        {
            // Текущая позиция первого объекта Cтрока
            current_position = code.indexOf(current_object_type.toString(), current_position);
            if (current_position != - 1)
            {
                // Перенос текущей позиции на начало имени строки
                current_position += current_object_type.length() + 1;
                if (code.charAt(current_position - 1) == ' ')
                {
                    // Опеределение конца имени строки
                    buffer_position = code.indexOf(" ", current_position);
                    // Запись имени строки
                    current_object.append(code.substring(current_position, buffer_position));
                    // Очистка имени от лишних символов
                    if (current_object.charAt(current_object.length() - 1) == ';' || current_object.charAt(current_object.length() - 1) == ')')
                    {
                        current_object.deleteCharAt(current_object.length() - 1);
                    }
                    // Добавление в строку объектов с проверкой на уникальность, пустоты и символы
                    if (current_object.indexOf("(") == - 1 && objects_string.indexOf(current_object.toString()) == - 1 && current_object.indexOf("\n") == -1 && current_object.length() > 0)
                    {
                        objects_string.append(" ").append(current_object_type).append(" ").append(current_object).append(",");
                    }
                    current_object.setLength(0);
                }
            }
        }
        current_object_type.setLength(0);
        // Поиск объектов общего вида
        current_position = 0;
        // Текущая позиция первого вхождения new
        current_position = code.indexOf(new_string.toString(), current_position);
        // Второй цикл прохода по коду
        while (current_position != - 1)
        {
            buffer_position = current_position - 1;
            // Определение начала имени объекта
            while (code.charAt(buffer_position) != ' ')
            {
                buffer_position--;
            }
            // Запись имени объекта
            current_object.append(code.substring(buffer_position + 1, current_position));
            // Переход на начало типа объекта
            current_position = current_position + new_string.length() + 1;
            // Переход на конец типа объекта
            buffer_position = code.indexOf(" ", current_position);
            // Запись типа объекта с проверкой на лишние символы
            current_object_type.append(code.substring(current_position, buffer_position));
            // Редактирование типа объекта
            if (current_object_type.charAt(current_object_type.length() - 1) == ';' || current_object_type.charAt(current_object_type.length() - 1) == '"')
            {
                current_object_type.deleteCharAt(current_object_type.length() - 1);
            }
            // Случай массива
            if (current_object_type.indexOf("[") != -1)
            {
                buffer_position = current_object_type.indexOf("[");
                StringBuilder buffer_type = new StringBuilder(current_object_type.substring(0, buffer_position)).append("[]");
                current_object_type = buffer_type;
            }
            // Общий случай:
            if (current_object_type.indexOf("(") != -1)
            {
                buffer_position = current_object_type.indexOf("(");
                StringBuilder buffer_type = new StringBuilder(current_object_type.substring(0, buffer_position));
                current_object_type = buffer_type;
            }
            // Случай STL контейнера
            if (current_object_type.indexOf("<") != -1)
            {
                buffer_position = current_object_type.indexOf("<");
                StringBuilder buffer_type = new StringBuilder(current_object_type.substring(0, buffer_position));
                current_object_type = buffer_type;
            }
            // Запись объекта с проверкой на уникальность и символы
            if (objects_string.indexOf(current_object.toString()) == - 1 && current_object_type.length() > 0)
            {
                objects_string.append(" ").append(current_object_type).append(" ").append(current_object).append(",");
            }
            // Переход к новому вхождению new
            current_position = code.indexOf(new_string.toString(), current_position + new_string.length());
            current_object.setLength(0);
            current_object_type.setLength(0);
        }
        // Редактирование конца строки
        if (objects_string.length() > start_length)
        {
            objects_string.deleteCharAt(objects_string.length() - 1);
            System.out.println(objects_string);
            System.out.println();
        }
        else
        {
            System.out.println("В исходном коде объекты не были обнаружены");
            System.out.println();
        }
    }
}