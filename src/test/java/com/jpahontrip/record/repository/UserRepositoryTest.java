package com.jpahontrip.record.repository;

import com.jpahontrip.record.domain.AgeRangeType;
import com.jpahontrip.record.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void save() throws Exception {
        //given
        User user = new User();
        user.setProvider("abcde");
        user.setSocialId("rh4421");
        user.setNickname("kojun");
        user.setAgeRangeType(AgeRangeType.AGE_20_29);

        //when
        Long savedId = userRepository.save(user);

        //then
        User findUser = userRepository.findById(savedId);

        Assertions.assertThat(savedId).isEqualTo(findUser.getId());
    }
}