package sierp.springteam1.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectlogDto {
    int eno;        // 사원 번호
    int pjno;       // 프로젝트 번호
    int state;      // 상태
    int score;      // 이번 프로젝트로 얻은 점수
}
