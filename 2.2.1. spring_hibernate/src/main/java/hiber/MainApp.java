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

      userService.add(new User("User4", "Lastname", "user4@mail.ru", new Car("Lada", 2008)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("BMW", 2015)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("Lexus", 2019)));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("Mazda", 2020)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> userList2 = userService.getUserByCarModelAndSeries("Mazda", 2020);
      for (User user : userList2) {
         System.out.println(user);
      }

      context.close();
   }
}
