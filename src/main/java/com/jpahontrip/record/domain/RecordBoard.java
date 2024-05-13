package com.jpahontrip.record.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "record_board")
public class RecordBoard extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recordBoard")
    private List<RecordComment> recordComments = new ArrayList<>();

    @OneToMany(mappedBy = "recordBoard")
    private List<RecordLike> recordLikes = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private IsVisible isVisible;

    private String thumbnail;

    public void changeComment(RecordComment recordComment) {
        this.recordComments.add(recordComment);
    }

    public void changeLike(RecordLike recordLike) {
        this.recordLikes.add(recordLike);
    }
}
