package in.Ragav.MGNREGA.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class DistrictData 
{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String districtName;
	    private String month; // Format: YYYY-MM
	    private Long totalHouseholdsWorked;
	    private Long totalPersondaysGenerated;
	    private Double averageWageRate;
	    private Double totalExpenditure;
	    private LocalDateTime lastUpdated;

	    // Constructors
	    public DistrictData() {}

	    public DistrictData(String districtName, String month, Long totalHouseholdsWorked,
	                        Long totalPersondaysGenerated, Double averageWageRate,
	                        Double totalExpenditure, LocalDateTime lastUpdated) {
	        this.districtName = districtName;
	        this.month = month;
	        this.totalHouseholdsWorked = totalHouseholdsWorked;
	        this.totalPersondaysGenerated = totalPersondaysGenerated;
	        this.averageWageRate = averageWageRate;
	        this.totalExpenditure = totalExpenditure;
	        this.lastUpdated = lastUpdated;
	    }

	    // Getters and Setters
	    public Long getId() { return id; }
	    public String getDistrictName() { return districtName; }
	    public void setDistrictName(String districtName) { this.districtName = districtName; }

	    public String getMonth() { return month; }
	    public void setMonth(String month) { this.month = month; }

	    public Long getTotalHouseholdsWorked() { return totalHouseholdsWorked; }
	    public void setTotalHouseholdsWorked(Long totalHouseholdsWorked) { this.totalHouseholdsWorked = totalHouseholdsWorked; }

	    public Long getTotalPersondaysGenerated() { return totalPersondaysGenerated; }
	    public void setTotalPersondaysGenerated(Long totalPersondaysGenerated) { this.totalPersondaysGenerated = totalPersondaysGenerated; }

	    public Double getAverageWageRate() { return averageWageRate; }
	    public void setAverageWageRate(Double averageWageRate) { this.averageWageRate = averageWageRate; }

	    public Double getTotalExpenditure() { return totalExpenditure; }
	    public void setTotalExpenditure(Double totalExpenditure) { this.totalExpenditure = totalExpenditure; }

	    public LocalDateTime getLastUpdated() { return lastUpdated; }
	    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
	
}
