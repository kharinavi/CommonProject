package ru.kharina.study.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kharina.study.models.Owner;
import ru.kharina.study.models.Product;
import ru.kharina.study.models.Provider;

import java.util.List;

@Component
public class OwnerDAO {

    private final SessionFactory sessionFactory;

    Provider currentProvider = null;

    @Autowired
    public OwnerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Owner> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Owner p", Owner.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Owner show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Owner result = session.get(Owner.class, id);
        currentProvider = result.getProvider();
        return result;
    }

    @Transactional(readOnly = true)
    public Provider ProviderOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Owner result = session.get(Owner.class, id);
        return result.getProvider();
    }

    @Transactional
    public void save(Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        session.save(owner);
    }

    @Transactional
    public void update(int id, Owner updatedOwner) {
        Session session = sessionFactory.getCurrentSession();
        Owner ownerToBeUpdated = session.get(Owner.class, id);

        ownerToBeUpdated.setName(updatedOwner.getName());
        ownerToBeUpdated.setSurname(updatedOwner.getSurname());
        ownerToBeUpdated.setEmail(updatedOwner.getEmail());
        ownerToBeUpdated.setTelephoneNumber(updatedOwner.getTelephoneNumber());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Owner.class, id));
    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    public void setCurrentProvider(Provider currentProvider) {
        this.currentProvider = currentProvider;
    }
}
