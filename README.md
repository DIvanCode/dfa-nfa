# DFA-NFA
Основной файл - Main.kt

Находится по пути /src/main/kotlin/Main.kt

Все файлы со входными данными находятся в папке /input/

Примеры входных данных, а также запуска заданий имеются.

## Задание 1. Симулятор DFA / NFA
Функция task1DFA(dfaFile: String, wordFile: String) принимает пути до файлов с описанием DFA (dfaFile) и строкой (wordFile), которую нужно распознать, строит DFA и возвращает true/false - принимаем или не принимаем слово соответственно.

Функция task1NFA(nfaFile: String, wordFile: String) принимает пути до файлов с описанием NFA (nfaFile) и строкой (wordFile), которую нужно распознать, строит NFA и возвращает true/false - принимаем или не принимаем слово соответственно.

Символы строки записаны через пробел (т.к. m может быть больше 9, и тогда каждый символ будет длины больше 1).

## Задание 2. Преобразование NFA в DFA
Функция task2(fileName: String) принимает путь до файла с описанием NFA (fileName), строит DFA и дописывает в файл fileName описание получившегося DFA.

## Задание 3. Виртуальная машина регулярных выражений
Функция task3(regExpFile: String, wordFile: String) принимает пути до файлов с регулярным выражением (regExpFile) и строкой (wordFile), которую нужно распознать, строит виртуальную машину этого регулярного выражения и возвращает true/false - подходит ли данная строка данному регулярному выражению.

## Задание 4. Алгоритм Мура минимизации DFA
Функция task4(fileName: String) принимает путь до файла с описанием DFA (fileName), строит минимальный DFA с помощью алгоритма Мура и дописывает в файл fileName описание получившегося DFA.
