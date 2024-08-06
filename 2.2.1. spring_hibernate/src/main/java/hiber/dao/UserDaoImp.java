package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   private final SessionFactory sessionFactory;
   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> getUserByCarModelAndSeries (String model, int series) {
      String hql = "SELECT user FROM User user WHERE user.car.model = :model AND user.car.series = :series";
      List<User> userList = sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series).getResultList();
      return userList;
   }

}
