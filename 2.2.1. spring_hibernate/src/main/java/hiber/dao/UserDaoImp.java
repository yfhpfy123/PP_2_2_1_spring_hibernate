package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

//   @Override
//   public User getUserByCarModelAndSeries(String model, int series) {
//      String hql = "SELECT user FROM User user WHERE user.car.model = :model AND user.car.series = :series";
//      Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
//      query.setParameter("model", model);
//      query.setParameter("series", series);
//      return query.getSingleResult();
//   }

   public List<User> getUserByCarModelAndSeries (String model, int series) {
      String hql = "SELECT user FROM User user WHERE user.car.model = :model AND user.car.series = :series";
      List<User> userList = sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series).getResultList();
      return userList;
   }

}
