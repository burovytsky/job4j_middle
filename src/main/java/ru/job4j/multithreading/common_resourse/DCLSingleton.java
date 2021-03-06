package ru.job4j.multithreading.common_resourse;

public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}

/*
Вот что удалось найти и понять по данному вопросу :

Ошибка может возникнуть, к примеру, если события в многопоточной программе идут так
1. Поток А замечает, что переменная не инициализирована, затем получает блокировку и начинает инициализацию.
2. потоку А может присвоить разделяемой переменной ссылку на объект, который находится в процессе инициализации
3. Поток Б замечает, что переменная инициализирована (по крайней мере, ему так кажется)!, и возвращает значение переменной
без получения блокировки. Если поток Б теперь будет использовать переменную до того момента, когда поток А закончит
инициализацию, поведение программы будет некорректным!

По ключевому слову volatile :

Так как спецификации языка Java позволяет JRE сохранять локальную копию переменной в каждом потоке,
который ссылается на нее, то возможна ситуация, когда один поток изменил значение переменной, а второй не увидел
этого изменения, потому что работал со своей, (внутрипоточной) кэшированной копией переменной.
Решение - объявить переменную с ключевым словом volatile тогда :
1. Она всегда будет атомарно читаться и записываться.
2. Java-машина не будет помещать ее в кэш. Так что ситуация, когда 10 потоков работают со
своими локальными копиями исключена.
*/