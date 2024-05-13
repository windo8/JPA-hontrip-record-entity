package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.*;
import com.jpahontrip.record.service.RecordService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class RepositorysTest {

    @Autowired private EntityManager em;

    @Autowired private UserRepository userRepository;
    @Autowired private RecordBoardRepository recordBoardRepository;
    @Autowired private RecordCommentRepository recordCommentRepository;
    @Autowired private RecordLikeRepository recordLikeRepository;

    @Autowired private RecordService recordService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 게시물_댓글_좋아요_작성_테스트() throws Exception {

        //회원가입
        User user  = new User();
        user.setNickname("w1nd0");
        user.setProvider("s989hk341k32");
        user.setSocialId("test_social_id");
        user.setAgeRangeType(AgeRangeType.AGE_20_29);
        Long savedUser = userRepository.save(user);

        //위치 세팅
        Location location = new Location();
        location.setCity("수원");
        location.setLat(32.124);
        location.setLon(21.214);
        em.persist(location);


        //게시물 작성
        RecordBoard recordBoard = new RecordBoard();
        recordBoard.setUser(user);
        recordBoard.setThumbnail("test.jpg");
        recordBoard.setLocation(location);
        recordBoard.setTitle("test");
        recordBoard.setIsVisible(IsVisible.PRIVATE);
        recordBoard.setCreatedDate(LocalDateTime.now());
        Long savedBoard = recordBoardRepository.save(recordBoard);

        //댓글 작성
        RecordComment recordComment = new RecordComment();
        recordComment.setUser(user);
        recordComment.setRecordBoard(recordBoard);
        recordComment.setContent("test_comment");
        recordComment.setCreatedDate(LocalDateTime.now());
        recordCommentRepository.save(recordComment);

        recordBoard.changeComment(recordComment);

        //좋아요
        RecordLike recordLike = new RecordLike();
        recordLike.setUser(user);
        recordLike.setRecordBoard(recordBoard);
        recordLikeRepository.save(recordLike);

        recordBoard.changeLike(recordLike);

        em.flush();
        em.clear();

        //조회
        RecordBoard findById = recordBoardRepository.findById(1L);

        //출력
        System.out.println(findById.getUser().getNickname());

        List<RecordComment> recordComments = findById.getRecordComments();
        for (RecordComment comment : recordComments) {
            System.out.println(comment.getContent());
        } //연관관계 편의 메서드 사용해서 board에도 넣어줘야함

        System.out.println(findById.getLocation().getCity());
        System.out.println(findById.getThumbnail());
        System.out.println(findById.getIsVisible());
        System.out.println(findById.getCreatedDate());

        List<RecordLike> recordLikes = findById.getRecordLikes();
        for (RecordLike like : recordLikes) {
            System.out.println(like.getId());
        } //연관관계 편의 메서드 사용해서 board에도 넣어줘야함

        //검증


    }
}
