package ru.job4j.multithreading.common_resourse;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        User user1 = User.of("name1");
        User user2 = User.of("name2");
        cache.add(user);
        cache.add(user1);
        cache.add(user2);
        Thread first = new Thread(
                () -> user.setName("rename")
        );
        first.start();
        first.join();
        System.out.println(cache.findById(2).getName());

        for(User u : cache.findAll()){
            System.out.println(u.getName() + " " + u.getId());
        }
    }
}
