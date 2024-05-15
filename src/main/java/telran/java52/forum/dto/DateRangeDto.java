package telran.java52.forum.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;


@Getter
public class DateRangeDto {
	LocalDate dateFrom;
	LocalDate dateTo;
	

	public DateRangeDto(LocalDate dateFrom, LocalDate dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	
	}
	
		public LocalDateTime getStartOfDay() {
		 return dateFrom.atStartOfDay(); 
	  }

	 
		public LocalDateTime getEndOfDay() {
		return dateTo.atTime(LocalTime.MAX);
	   }
}

	
    
