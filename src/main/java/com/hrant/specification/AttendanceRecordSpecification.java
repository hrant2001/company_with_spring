package com.hrant.specification;

import com.hrant.model.AttendanceRecord;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Expression;

@Component
public class AttendanceRecordSpecification {

    public static Specification<AttendanceRecord> containsFullNameOrDepartment(String searchingTerm, boolean isEmployee){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (isEmployee) {
                Expression<String> fullName = criteriaBuilder.concat(criteriaBuilder.concat(root.get("employee").get("fName"), " "), root.get("employee").get("lName"));

                return criteriaBuilder.like(fullName, "%" + searchingTerm + "%");

            } else {
                return criteriaBuilder.like(root.get("employee").get("department").get("name"), "%" + searchingTerm + "%");
            }
        });
    }
    public static Specification<AttendanceRecord> containsEntranceTime(String date){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("entranceTime").as(String.class), "%" + date + "%"));
    }
}
