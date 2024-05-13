package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.RecordBoard;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RecordBoardRepository {

    private final EntityManager em;

    @Transactional
    public Long save(RecordBoard recordBoard) {
        em.persist(recordBoard);
        return recordBoard.getId();
    }

    public RecordBoard findById(Long id) {
        return em.find(RecordBoard.class, id);
    }

}
