package ru.kharina.study.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kharina.study.models.Product;
import ru.kharina.study.models.Provider;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {

    private final SessionFactory sessionFactory;

    Provider currentProvider = null;
    List<Product> currentProductList = new ArrayList<>();


    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Product show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product result = session.get(Product.class, id);
        currentProvider = result.getProvider();
        currentProductList = result.getProvider().getProductList();
        System.out.println(currentProductList.size());
        return result;
    }

    @Transactional
    public void save(Product person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Product updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Product personToBeUpdated = session.get(Product.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setPrice(updatedPerson.getPrice());
        personToBeUpdated.setCalories(updatedPerson.getCalories());
        personToBeUpdated.setCompound(updatedPerson.getCompound());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Product.class, id));
    }

    public Provider getCurrentProvider() {
        return currentProvider;
    }

    public void setCurrentProvider(Provider currentProvider) {
        this.currentProvider = currentProvider;
    }

    public List<Product> getCurrentProductList() {
        return currentProductList;
    }

    public void setCurrentProductList(List<Product> currentProductList) {
        this.currentProductList = currentProductList;
    }
}
