package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.RecordLike;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RecordLikeRepository {

    private final EntityManager em;

    @Transactional
    public Long save(RecordLike recordLike) {
        em.persist(recordLike);
        return recordLike.getId();
    }

    public RecordLike findById(Long id) {
        return em.find(RecordLike.class, id);
    }
}
