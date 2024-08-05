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

      Car car1 = new Car();
      car1.setModel("Mazda");
      car1.setSeries(2020);

      User user1 = new User();
      user1.setFirstName("John Doe");
      user1.setCar(car1);

      userService.add(user1);

      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Lada", 2008)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 2015)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lexus", 2019)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Mazda", 2020)));

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
