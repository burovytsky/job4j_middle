package ru.job4j.multithreading.common_resourse;

public class CountShareMain {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(count.get());
    }
    public static class Count {
        private int value;

        public void increment() {
            value++;
        }

        public int get() {
            return value;
        }
    }
}
// операция не атомарна
// этой операции происходит проверка на null, потом присвоение и возврат значения. Операция
// происходит поетапно, а не неразрывно. То есть одна нить может вызвать этот метод и проверить что объект
// равен null и затем перейти к присвоению, в этот момент дргуая нить может вызвать этот же метод и тоже проверка вернет
// null, так как первая нить еще не успела присвоить значение.