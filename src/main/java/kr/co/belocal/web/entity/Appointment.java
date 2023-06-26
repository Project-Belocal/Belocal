package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private Integer travelThemeId;
    private Integer travelerId;
    private Integer guideId;
    private Integer travelerCompletionStatus;
    private Integer guideCompletionStatus;
}
