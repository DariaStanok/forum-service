package telran.java52.forum.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;

@Getter
public class DateRangeDto {
	LocalDateTime dateFrom;
	LocalDateTime dateTo;
}
