package com.swimpool.swim.pool.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swimpool.swim.pool.Entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

@Repository
public class OrderEntityRepository {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Map<Integer, Long> countEntitiesPerHour() {
        // Запрос на получение количества записей для каждого часа
        String queryString = "SELECT HOUR(e.date) AS hour, COUNT(*) as count "
                           + "FROM Order e "
                           + "GROUP BY HOUR(e.date)";

        Query query = entityManager.createQuery(queryString);

        // Получение результатов
        List<Object[]> results = query.getResultList();

        // Преобразование результатов в карту, где ключ - это час, а значение - количество записей
        Map<Integer, Long> resultMap = new HashMap<>();
        for (Object[] row : results) {
            int hour = (int) row[0];
            long count = (long) row[1];
            resultMap.put(hour, count);
        }

        return resultMap;
    }

    public Integer countOrdersOnTime(LocalDateTime date){
        String queryString = "SELECT COUNT(*) " +
                            "FROM Order o " + 
                            "WHERE o.date = :date";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("date", date);
        System.out.println(query.getResultList().toString());
        return ((Long) query.getSingleResult()).intValue();
    }

    public void save(Order order){
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
    }
}
