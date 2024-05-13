package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public Long save (User user) {
        em.persist(user);
        return user.getId();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }
}
