package in.Ragav.MGNREGA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.Ragav.MGNREGA.entity.DistrictData;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictData, Long> {
    List<DistrictData> findByDistrictNameOrderByMonthDesc(String districtName);
}