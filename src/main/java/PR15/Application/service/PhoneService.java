package PR15.Application.service;

import PR15.Application.model.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {
    @Autowired
    private final SessionFactory sessionFactory;

    private Session session;

    public PhoneService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addPhone(Phone phone) {
        session.beginTransaction();
        session.saveOrUpdate(phone);
        session.getTransaction().commit();
    }

    public List<Phone> getPhone() {
        return session.createQuery("select p from Phone p", Phone.class).list();
    }

    public List<Phone> getPhone(UUID id) {
        return session.createQuery("select p from Phone p where p.id ='" + id + "'", Phone.class).list();
    }

    public void deletePosts(Phone phone) {
        session.beginTransaction();

        List<Phone> query = session.createQuery("select p from Phone p where p.id = '" + phone.getId() + "'", Phone.class).list();
        for (Phone p : query) {
            session.delete(p);
        }

        session.getTransaction().commit();
    }

    public void deletePhone(UUID id) {
        session.beginTransaction();

        Phone t = session.load(Phone.class, id);
        session.delete(t);

        session.getTransaction().commit();
    }
}
