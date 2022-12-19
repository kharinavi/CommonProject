package ru.kharina.study.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kharina.study.models.Owner;
import ru.kharina.study.models.Product;
import ru.kharina.study.models.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderDAO {

    private final SessionFactory sessionFactory;

    List<Product> currentProductList = new ArrayList<>();
    Owner currentOwner = null;


    @Autowired
    public ProviderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Provider> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Provider p", Provider.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Provider show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Provider result = session.get(Provider.class, id);
        currentOwner = result.getOwner();
        currentProductList = result.getProductList();
        System.out.println(currentProductList.size());
        return result;
    }

    @Transactional
    public void save(Provider provider) {
        Session session = sessionFactory.getCurrentSession();
        session.save(provider);
    }

    @Transactional
    public void update(int id, Provider updatedProvider) {
        Session session = sessionFactory.getCurrentSession();
        Provider providerToBeUpdated = session.get(Provider.class, id);

        providerToBeUpdated.setName(updatedProvider.getName());
        providerToBeUpdated.setEmail(updatedProvider.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Provider.class, id));
    }

    public List<Product> getCurrentProductList() {
        return currentProductList;
    }

    public void setCurrentProductList(List<Product> currentProductList) {
        this.currentProductList = currentProductList;
    }

    public Owner getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Owner currentOwner) {
        this.currentOwner = currentOwner;
    }
}
