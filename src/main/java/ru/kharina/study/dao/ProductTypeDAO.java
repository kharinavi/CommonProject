package ru.kharina.study.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kharina.study.models.Product;
import ru.kharina.study.models.ProductType;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTypeDAO {

    private final SessionFactory sessionFactory;

    List<Product> currentProductList = new ArrayList<>();

    @Autowired
    public ProductTypeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<ProductType> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from ProductType p", ProductType.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public ProductType show(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProductType result = session.get(ProductType.class, id);
        currentProductList = result.getProductList();
        System.out.println(currentProductList.size());
        return result;
    }

    @Transactional
    public void save(ProductType person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, ProductType updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        ProductType personToBeUpdated = session.get(ProductType.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setDescription(updatedPerson.getDescription());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(ProductType.class, id));
    }

    public List<Product> getCurrentProductList() {
        return currentProductList;
    }

    public void setCurrentProductList(List<Product> currentProductList) {
        this.currentProductList = currentProductList;
    }
}
