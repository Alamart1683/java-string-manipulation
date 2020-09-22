package Lab_2;

// Импорт класса
import java.util.Scanner;

// Основной класс
public class Lab_2_1
{
    // Поля
    public static String code = ""; // Код для обработки
    public static String new_string = " = new"; // Текст для поиска объектов
    public static String str_string = "String"; //Текст для поиска строк
    public static String final_string = " final "; // Строка, содержащая оператор объявления константы
    public static String sc_next_string = ".next"; // Строка, содержащая часть метода ввода

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
                        input_code();
                        break;
                    case 2:
                        // Определить, сколько раз в программе создавались объекты
                        new_count();
                        break;
                    case 3:
                        // Сформировать список объектов (строку), указав название класса этого объекта
                        objects_string();
                        break;
                    case 4:
                        // В какие переменные кода осуществлялся ввод с клавиатуры
                        input_values();
                        break;
                    case 5:
                        // Сколько в программе использовалось констант
                        final_count();
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

    // Метод вывода меню
    public static void outputMenu()
    {
        System.out.println();
        System.out.println("Меню программы:");
        System.out.println("1. Ввести код");
        System.out.println("2. Определить, сколько раз в программе создавались объекты");
        System.out.println("3. Сформировать список объектов (строку), указав название класса этого объекта");
        System.out.println("4. В какие переменные кода осуществлялся ввод с клавиатуры");
        System.out.println("5. Сколько в программе использовалось констант");
        System.out.println("6. Повторный вывод меню");
        System.out.println("0. Завершение программы");
        System.out.println();
    }

    // Метод ввода кода
    public static void input_code()
    {
        System.out.println("Введите код:");
        Scanner input = new Scanner(System.in);
        code = input.nextLine();    // Ввод кода
        code = " " + code + " ";
        System.out.println("Код введен");
        System.out.println();
    }

    // Метод подсчёта количества созданий объектов:
    public static void new_count()
    {
        // Проверка введенности кода
        if (code == "")
        {
            System.out.println("Ошибка: не был введен код");
            System.out.println();
        }
        else
        {
            int count_new = 0; // Счётчик созданных объектов
            int count_str = 0; // Счётчик созднных строк
            // Опеределение индекса первого вхождения указанной подстроки
            int position_new = code.indexOf(new_string);
            int position_str = code.indexOf(str_string);
            //Проверка на наличие хотябы одного вхождения
            if (position_new != -1 || position_str != -1)
            {
                // Цикл поиска вхождений new в строку
                while (position_new != -1)
                {
                    position_new = code.indexOf(new_string, position_new + new_string.length());
                    count_new++;
                }
                // Цикл поиска вхождений String в строку
                while( position_str != -1)
                {
                    // Проверка на пробел после объявления строки
                    if (code.charAt(position_str + str_string.length()) == ' ')
                    {
                        count_str++;
                    }
                    position_str = code.indexOf(str_string, position_str + str_string.length());
                }
                System.out.println("В исходном коде было создано " + (count_new + count_str) + " объектов");
                System.out.println();
            }
            else
            {
                System.out.println("В исходном коде не было встречено ни одного объекта");
                System.out.println();
            }
        }
    }

    // Метод подсчёта количества констант:
    public static void final_count()
    {
        // Проверка введенности текста
        if (code == "")
        {
            System.out.println("Ошибка: не был введен код");
            System.out.println();
        }
        else
        {
            int count_final = 0; // Счётчик созднных объектов
            // Опеределение индекса первого вхождения указанной подстроки
            int position_final = code.indexOf(final_string);
            //Проверка на наличие хотябы одного вхождения
            if (position_final != -1)
            {
                // Цикл поиска вхождений констант в строку
                while (position_final != -1)
                {
                    position_final = code.indexOf(final_string, position_final + final_string.length());
                    count_final++;
                }
                System.out.println("В исходном коде было создано " + (count_final) + " констант");
                System.out.println();
            }
            else
            {
                System.out.println("В исходном коде не было встречено ни одной константы");
                System.out.println();
            }
        }
    }

    // Метод составления списка(строки) используемых объектов:
    public static void objects_string()
    {
        // Проверка введенности кода
        if (code == "")
        {
            System.out.println("Ошибка: не был введен код");
            System.out.println();
        }
        else
        {
            int current_position = 0; // Текущая позиция искомого элемента
            int buffer_position = 0; // Буферная позиция
            String current_object = ""; // Текущее имя объекта
            String current_object_type = ""; // Текущий тип объекта
            String objects_string = "Список объектов:"; // Строка с списком найденных объектов
            int start_length = objects_string.length(); // Изначальная длина строки объектов
            // Цикл поиска объектов класса Строка
            while (current_position != - 1)
            {
                // Текущая позиция первого объекта Cтрока
                current_position = code.indexOf(str_string, current_position);
                if (current_position != - 1 )
                {
                    current_position = current_position + str_string.length() + 1;
                    // Проверка на пробел после объявления строки
                    if (code.charAt(current_position - 1) == ' ')
                    {
                        // Запись имени объекта Строки
                        while (code.charAt(current_position) != ' ')
                        {
                            //Запоминаем текущее имя объекта
                            if (code.charAt(current_position) != ';' && code.charAt(current_position) != ')')
                            {
                                current_object = current_object + code.charAt(current_position);
                            }
                            current_position++;
                        }
                        // Добавление в строку объектов с проверкой на уникальность и символы
                        if (current_object.indexOf('(') == - 1 && objects_string.indexOf(current_object) == - 1 && current_object.indexOf('\n') == - 1)
                        {
                            objects_string = objects_string + " " + str_string + " " + current_object + ",";
                        }
                    }
                }
                current_object = "";
                current_object_type = "";
            }
            // Возвращение текущей позиции в начало строки
            current_position = 0;
            // Текущая позиция первого вхождения new
            current_position = code.indexOf(new_string, current_position);
            // Цикл поиска объектов общего вида
            while (current_position != - 1)
            {
                buffer_position = current_position - 1;
                // Запись имени объекта
                while (code.charAt(buffer_position) != ' ')
                {
                    current_object = current_object + code.charAt(buffer_position);
                    buffer_position--;
                }
                current_object = reverse(current_object);
                // Переход на начало типа объекта
                buffer_position = current_position + new_string.length() + 1;
                // Запись типа объекта с проверкой на лишние символы
                while (code.charAt(buffer_position) != ' ' && code.charAt(buffer_position) != ';' && code.charAt(buffer_position) != '"')
                {
                    current_object_type = current_object_type + code.charAt(buffer_position);
                    buffer_position++;
                }
                // Редактирование типа объекта
                // Случай массива
                if (current_object_type.indexOf('[') != - 1)
                {
                    int border = current_object_type.indexOf('[');
                    current_object_type = current_object_type.substring(0, border);
                    current_object_type = current_object_type + "[]";
                }
                // Случай STL контейнера
                if (current_object_type.indexOf('<') != - 1)
                {
                    int border = current_object_type.indexOf('<');
                    current_object_type = current_object_type.substring(0, border);
                }
                // Общий случай
                if (current_object_type.indexOf('(') != - 1)
                {
                    int border = current_object_type.indexOf('(');
                    current_object_type = current_object_type.substring(0, border);
                }
                // Добавление в строку объектов с проверкой на уникальность и пустоту
                if ( objects_string.indexOf(current_object) == - 1 && current_object_type != "")
                {
                    objects_string = objects_string + " " + current_object_type + " " + current_object + ",";
                }
                // Переход к новому вхождению new
                current_position = code.indexOf(new_string, current_position + new_string.length());
                current_object = "";
                current_object_type = "";
            }
            // Редактирование конца строки
            if (objects_string != "" && (objects_string.length() > start_length))
            {
                objects_string = objects_string.substring(0, objects_string.length() - 1);
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

    // Метод инвертирования строки
    public static String reverse(String string)
    {
        String reversed_string = ""; // Инвертированная строка
        char[] array = string.toCharArray(); // Массив символов полученной строки
        int last_index = array.length - 1; // Индекс последнего элемента массива
        for (int index = last_index; index >= 0; index--)
        {
            reversed_string += array[index];
        }
        return reversed_string;
    }

    // Метод составления списка вводимых с клавиатуры переменных
    public static void input_values()
    {
        // Проверка введенности кода
        if (code == "")
        {
            System.out.println("Ошибка: не был введен код");
            System.out.println();
        }
        else
        {
            int current_position = 0; // Текущая позиция искомой переменной
            int buffer_position = 0; // Буферная позиция
            String current_value = ""; // Текущее имя переменной
            String values_string = "Список вводимых переменных:"; // Строка с списком найденных переменных
            int start_length = values_string.length(); // Изначальная длина строки объектов
            // Поиск первого вхождения введенной переменной
            current_position = code.indexOf(sc_next_string);
            // Цикл поиска вводиых переменных
            while (current_position != -1)
            {
                buffer_position = current_position - 1;
                // Перевод текущей позиции на конец функции ввода переменной
                while (code.charAt(buffer_position) != ' ' && code.charAt(buffer_position) != '=')
                {
                    buffer_position--;
                }
                // Перевод текушей позиции на первый символ имени вводимой переменной
                while (code.charAt(buffer_position) == '=' || code.charAt(buffer_position) == ' ')
                {
                    buffer_position--;
                }
                // Цикл записи имени переменной
                while (code.charAt(buffer_position) != ' ')
                {
                    current_value = current_value + code.charAt(buffer_position);
                    buffer_position--;
                }
                current_value = reverse(current_value);
                // Добавление в строку переменных с проверкой на уникальность и пустоту
                if ( values_string.indexOf(current_value) == -1 && current_value.indexOf("") != -1 && current_value.indexOf('"') == -1)
                {
                    values_string = values_string + " " + current_value + ",";
                }
                // Переход к новому вхождению подстроки
                current_position = code.indexOf(sc_next_string, current_position + sc_next_string.length());
                current_value = "";
            }
            // Редактирование конца строки
            if (values_string != "" && (values_string.length() > start_length))
            {
                values_string = values_string.substring(0, values_string.length() - 1);
                System.out.println(values_string);
                System.out.println();
            }
            else
            {
                System.out.println("В исходном коде вводимые переменные не были обнаружены");
                System.out.println();
            }
        }
    }
}