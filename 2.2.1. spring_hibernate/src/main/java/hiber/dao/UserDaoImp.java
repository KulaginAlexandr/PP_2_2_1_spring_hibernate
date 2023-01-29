package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private SessionFactory sessionFactory;
    private String SELECT_FROM_USER_BY_CAR = "FROM User user JOIN FETCH user.car WHERE user.car.model=:model AND user.car.series=:series";

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
    public List<User> getUserList() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public User getUserByCarModelSeries(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery(SELECT_FROM_USER_BY_CAR, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }


}
