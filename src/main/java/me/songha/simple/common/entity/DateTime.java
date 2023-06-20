package me.songha.simple.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Deprecated
@Embeddable
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@Getter
//@EntityListeners(AuditingEntityListener.class)
public class DateTime {

    @CreatedDate
    @Column(name = "create_at", updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private ZonedDateTime updateAt;
}
