package me.songha.simple.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@Getter
@EntityListeners(AuditingEntityListener.class)
@Embeddable
public class DateTime {

    @CreatedDate
    @Column(name = "create_at", updatable = false)
    private ZonedDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private ZonedDateTime updateAt;
}
