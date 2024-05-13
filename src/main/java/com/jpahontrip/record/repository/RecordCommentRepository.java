package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.RecordComment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RecordCommentRepository {

    private final EntityManager em;

    @Transactional
    public Long save(RecordComment recordComment) {
        em.persist(recordComment);
        return recordComment.getId();
    }

    public RecordComment findById(Long id) {
        return em.find(RecordComment.class, id);
    }
}
