package com.hrant.repository;

import com.hrant.model.AttendanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AttendanceRecordCriteriaRepositoryImpl implements AttendanceRecordCriteriaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AttendanceRecord> findByCriteria(boolean isEmployee, String searchingTerm, String date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AttendanceRecord> criteriaQuery = criteriaBuilder.createQuery(AttendanceRecord.class);

        Root<AttendanceRecord> recordRoot = criteriaQuery.from(AttendanceRecord.class);

        Predicate criteriaPredicate;
        if (isEmployee) {
            Expression<String> fullName = criteriaBuilder.concat(criteriaBuilder.concat(recordRoot.get("employee").get("fName"), " "), recordRoot.get("employee").get("lName"));

            criteriaPredicate = criteriaBuilder.like(fullName, "%" + searchingTerm + "%");

        } else {
            criteriaPredicate = criteriaBuilder.like(recordRoot.get("employee").get("department").get("name"), "%" + searchingTerm + "%");
        }

        Predicate datePredicate = criteriaBuilder.like(recordRoot.get("entranceTime").as(String.class), "%" + date + "%");

        criteriaQuery.where(criteriaPredicate, datePredicate);

        TypedQuery<AttendanceRecord> query = entityManager.createQuery(criteriaQuery);

        List<AttendanceRecord> a = query.getResultList();
        System.out.println("a");
        System.out.println(isEmployee + " " + searchingTerm + " " + date + " in criteria repo");
        return a;
    }
}
