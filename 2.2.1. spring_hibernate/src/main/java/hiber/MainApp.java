package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
    public static void main(String[] args) {
        // "throws SQLException  - для чего? будь внимательным"
        // я это убрал, но он был в проекте изначально
        //правда если это был наводящий вопрос, то я тогда не понял к какому моменту)


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        Car car1 = new Car("Car1", 111, user1);
        Car car2 = new Car("Car2", 222, user2);
        Car car3 = new Car("Car3", 333, user3);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);


        userService.listUsers().forEach(System.out::println);

        User user123 = userService.getUser("Car1", 111);
        System.out.println(user123);

        context.close();
    }
}
