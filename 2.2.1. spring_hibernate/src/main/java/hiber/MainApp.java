package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.add(new User("Иванна", "Титова", "afasfas@mail.ru", new Car("model1", 123)));
        userService.add(new User("Ангелина", "Игнатова", "afasfas@mail.ru", new Car("model2", 123)));
        userService.add(new User("Дмитрий", "Фома", "afasfas@mail.ru", new Car("model3", 123)));
        userService.add(new User("Игнат", "Кудряшов", "afasfas@mail.ru", new Car("model4", 123)));
        List<User> users = userService.getUserList();
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println(userService.getUserByCarModelSeries("diau", 3));
        context.close();
    }
}
