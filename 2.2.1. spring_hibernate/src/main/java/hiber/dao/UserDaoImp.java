package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
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
    public List<User> listUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(String model, int series) {
        Session session = sessionFactory.openSession();
        String HQL = "from Car where model = :paramModel and series = :paramSeries";
        Car car = session.createQuery(HQL, Car.class)
                .setParameter("paramModel", model)
                .setParameter("paramSeries", series)
                .getSingleResult();
        session.close();
        return car.getUser();
    }
}
