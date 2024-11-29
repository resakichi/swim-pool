package com.swimpool.swim.pool.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class OrderEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Map<Integer, Long> countEntitiesPerHour(LocalDate date) {
        String queryString = "SELECT HOUR(e.date) AS hour, COUNT(*) as count "
                           + "FROM Order e "
                           + "WHERE e.date BETWEEN :dateStart AND :dateEnd "
                           + "GROUP BY HOUR(e.date)";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("dateStart", LocalDateTime.of(date, LocalTime.MIN));
        query.setParameter("dateEnd", LocalDateTime.of(date, LocalTime.MAX));

        List<Object[]> results = query.getResultList();

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

    public Integer countUsersOnDate(LocalDate date, Long userId){
        String queryString = "SELECT COUNT(*) " +
                            "FROM orders o " + 
                            "WHERE o.date BETWEEN :startDate AND :endDate AND o.user_id = :userId";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("startDate", LocalDateTime.of(date, LocalTime.MIN));
        query.setParameter("endDate", LocalDateTime.of(date, LocalTime.MAX));
        query.setParameter("userId", userId);
        System.out.println(query.getResultList().toString());
        return ((Long) query.getSingleResult()).intValue();
    }


    @Transactional
    public void save(Order order){
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void removeById(Long id){
        String queryString = "DELETE FROM Order o WHERE o.id = :id";
        int isSuccess = entityManager.createQuery(queryString)
        .setParameter("id", id)
        .executeUpdate();
        if (isSuccess == 0){
            throw new OptimisticLockException("order modified concurrently");
        }
    }


    public Order findByDateName(LocalDateTime date, String name){
        String queryString = "SELECT o "
        + "FROM Order o "
        + "LEFT JOIN FETCH o.user u "
        + "WHERE o.date = :date AND u.name = :name";
        
        Query query = entityManager.createQuery(queryString)
        .setParameter("date", date)
        .setParameter("name", name);

        return (Order) query.getSingleResult();
    }
}
