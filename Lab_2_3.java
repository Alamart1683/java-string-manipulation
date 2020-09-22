package Lab_2;

// Импорты классов
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Основной класс
public class Lab_2_3
{
    public static String string_numbers = ""; // Строка для чисел
    public static String number = ""; // Строка для числа
    public static String code = ""; // Строка для кода

    // Главный метод
    public static void main(String[] args)
    {
        int command = 0;

        Scanner input = new Scanner(System.in);
        // Вывод списка опций
        outputMenu();
        do
        {
            System.out.print("Введите вариант: ");
            // Проверка ввода переменной выбора
            while (!input.hasNextInt())
            {
                System.out.println("Неверно введена команда");
                System.out.println();
                System.out.print("Введите вариант: ");
                input.next();
            }
            command = input.nextInt();
            // Обработка выбора
            switch(command)
            {
                case 1:
                    // Ввод строки одного числа
                    input_number();
                    break;
                case 2:
                    // Определите, что строка представляет число: целое или дробное
                    number_kind();
                    break;
                case 3:
                    // Ввод строки чисел
                    input_numbers();
                    break;
                case 4:
                    // Преобразовать строку так, чтобы вместо этих целых чисел стояли их квадраты, а вместо дробных – цела часть числа
                    numbers_change();
                    break;
                case 5:
                    // В какие переменные кода осуществлялся ввод с клавиатуры (Задание 1")
                    input_values();
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

    //Метод вывода основного меню приложения
    public static void outputMenu()
    {
        System.out.println();
        System.out.println("Меню программы:");
        System.out.println("1. Ввод числа");
        System.out.println("2. Определите, что строка представляет число: целое или дробное, а также знак числа");
        System.out.println("3. Ввод строки чисел");
        System.out.println("4. Преобразовать строку чисел так, чтобы вместо этих целых чисел стояли их квадраты, а вместо дробных – цела часть числа");
        System.out.println("5. В какие переменные кода осуществлялся ввод с клавиатуры (Задание 1)");
        System.out.println("6. Повторный вывод меню");
        System.out.println("0. Завершение программы");
        System.out.println();
    }

    // Метод ввода числа
    public static void input_number()
    {
        System.out.println("Введите число:");
        Scanner input = new Scanner(System.in);
        number = input.next();    // Ввод числа
        System.out.println("Число введено");
        System.out.println();
    }

    // Метод ввода строки чисел
    public static void input_numbers()
    {
        System.out.println("Введите числа:");
        Scanner input = new Scanner(System.in);
        string_numbers = input.nextLine();    // Ввод чисел
        System.out.println("Числа введены");
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
    }

    // Метод определения типа числа
    public static void number_kind()
    {
        // Проверка введенности строки чисел
        if (number == "")
        {
            System.out.println("Ошибка: не было введено число");
            System.out.println();
        }
        else
        {
            // Опеределение шаблона любого числа
            Pattern pattern = Pattern.compile("^[-+]?(\\d+|\\d+[.,]\\d+)$");
            Matcher matcher = pattern.matcher(number);
            if (matcher.matches())
            {
                // Определение положительного целого числа
                pattern = Pattern.compile("^\\+?\\d+$");
                matcher = pattern.matcher(number);
                if (matcher.matches())
                {
                    System.out.println("Введенное число положительное и целое");
                    System.out.println();
                }
                // Определение отрицательного целого числа
                pattern = Pattern.compile("^-\\d+$");
                matcher = pattern.matcher(number);
                if (matcher.matches())
                {
                    System.out.println("Введенное число отрицательное и целое");
                    System.out.println();
                }
                // Опеределение положительного вещественного числа
                pattern = Pattern.compile("^[+]?(\\d+[.,]\\d+)$");
                matcher = pattern.matcher(number);
                if (matcher.matches())
                {
                    System.out.println("Введенное число положительное и вещественное");
                    System.out.println();
                }
                // Опеределение отрицательного вещественного числа
                pattern = Pattern.compile("^[-](\\d+[.,]\\d+)$");
                matcher = pattern.matcher(number);
                if (matcher.matches())
                {
                    System.out.println("Введенное число отрицательное и вещественное");
                    System.out.println();
                }
            }
            else
            {
                System.out.println("Введенный литерал не является числом подходящего вида");
                System.out.println();
            }
            // Числа всех типов: ^[-+]?(\\d+|\\d+[.,]\\d+)$
            // Вещественные числа: ^[-+]?(\\d+[.,]\\d+)$
            // Целые числа: ^[-+]?(\\d+)$
        }
    }

    // Метод возведения в квадрат и выделения целых частей
    public static void numbers_change()
    {
        // Проверка введенности строки чисел
        if (string_numbers == "")
        {
            System.out.println("Ошибка: не были введены числа");
            System.out.println();
        }
        else
        {
            String string_numbers_changed = ""; // Преобразованная строка
            Pattern pattern = Pattern.compile("[ !?;]"); // Паттерн разделителей
            Pattern pattern_integer = Pattern.compile("^[-+]?(\\d+)$"); // Паттерн целых чисел
            Pattern pattern_real = Pattern.compile("^[-+]?(\\d+[.,]\\d+)$"); // Паттерн вещественных чисел
            // Разбиение строки на массив строк содержащих числа
            String[] numbers = pattern.split(string_numbers);
            // Цикл возведения в квадрат целых чисел и выделения целой части у дробных
            for (int index = 0; index < numbers.length; index++)
            {
                // Определение, является ли элемент массива целым числом
                Matcher matcher = pattern_integer.matcher(numbers[index]);
                if (matcher.matches())
                {
                    // Возведение в квадрат целого числа
                    numbers[index] = Integer.toString(Integer.parseInt(numbers[index])*Integer.parseInt(numbers[index]));
                }
                // Определпние, является ли элемент массива вещественным числом
                matcher = pattern_real.matcher(numbers[index]);
                if (matcher.matches())
                {
                    // Выделене целой части из вещественного числа
                    numbers[index] = numbers[index].split("[.,]")[0];
                }
                // Преобразование строки к нужному виду
                string_numbers_changed += numbers[index] + " ";
            }
            string_numbers = string_numbers_changed;
            System.out.println("Строка чисел после изменения: ");
            System.out.println(string_numbers);
            System.out.println();
        }
    }

    // Метод составления списка вводимых с клавиатуры переменных (Задание 1)
    public static void input_values()
    {
        // Ввод кода
        input_code();
        String values_string = "Список вводимых переменных:"; // Строка с списком найденных переменных
        int start_length = values_string.length(); // Изначальная длина строки объектов
        // Определение паттерна вводимой переменной
        Pattern pattern = Pattern.compile("\\w+\\s*[=]\\s*\\w+(\\.next)(\\w+|[()])");
        Matcher matcher = pattern.matcher(code);
        // Перебор найденных совпадений и выделение имен переменных
        while (matcher.find())
        {
            values_string = values_string + " " + matcher.group().split("[ ]")[0] + ",";
        }
        // Редактирование конца строки
        if (values_string != "" && (values_string.length() > start_length))
        {
            values_string = values_string.substring(0, values_string.length() - 1);
            System.out.println(values_string);
        }
        else
        {
            System.out.println("В исходном коде вводимые переменные не были обнаружены");
            System.out.println();
        }
    }
}
