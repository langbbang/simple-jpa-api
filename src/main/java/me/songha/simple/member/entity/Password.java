package me.songha.simple.member.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Password {
}
