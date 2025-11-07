//package in.Ragav.MGNREGA.service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import in.Ragav.MGNREGA.Repository.DistrictRepository;
//import in.Ragav.MGNREGA.entity.DistrictData;
//
//@Service
//public class DistrictService {
//
//    @Autowired
//    private DistrictRepository districtRepository;
//
//    private static final String API_URL =
//            "https://api.data.gov.in/resource/ee03643a-ee4c-48c2-ac30-9f2ff26ab722"
//            + "?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b"
//            + "&format=json&limit=1000"; // fetch more records
//
//    public List<DistrictData> getDistrictData(String districtName) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class);
//            Map<String, Object> body = response.getBody();
//
//            System.out.println("=== API Raw Response ===");
//            System.out.println(body);
//
//            if (body == null || !body.containsKey("records")) {
//                System.out.println("⚠️ No 'records' key found in API response");
//                return getDataFromDatabase(districtName);
//            }
//
//            List<Map<String, Object>> records = (List<Map<String, Object>>) body.get("records");
//            List<DistrictData> result = new ArrayList<>();
//
//            for (Map<String, Object> record : records) {
//                Object nameObj = record.get("district_name"); // ✅ FIXED key
//                if (nameObj == null) continue;
//
//                String name = nameObj.toString().trim();
//
//                if (name.equalsIgnoreCase(districtName.trim())) {
//                    DistrictData data = new DistrictData();
//                    data.setDistrictName(name);
//                    data.setMonth((String) record.get("month")); // ✅ FIXED key
//                    data.setTotalHouseholdsWorked(parseLong(record.get("Total_Households_Worked"))); // ✅ FIXED key
//                    data.setTotalPersondaysGenerated(parseLong(record.get("Persondays_of_Central_Liability_so_far"))); // ✅ FIXED key
//                    data.setAverageWageRate(parseDouble(record.get("Average_Wage_rate_per_day_per_person"))); // ✅ FIXED key
//                    data.setTotalExpenditure(parseDouble(record.get("Total_Exp"))); // ✅ FIXED key
//                    data.setLastUpdated(LocalDateTime.now());
//
//                    result.add(data);
//                    districtRepository.save(data);
//                }
//            }
//
//            if (result.isEmpty()) {
//                System.out.println("⚠️ No records matched district: " + districtName);
//                return getDataFromDatabase(districtName);
//            }
//
//            return result;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return getDataFromDatabase(districtName);
//        }
//    }
//
//    private List<DistrictData> getDataFromDatabase(String districtName) {
//        return districtRepository.findByDistrictNameOrderByMonthDesc(districtName);
//    }
//
//    private Long parseLong(Object obj) {
//        if (obj == null) return 0L;
//        try { return Long.parseLong(obj.toString().replaceAll(",", "")); }
//        catch (Exception e) { return 0L; }
//    }
//
//    private Double parseDouble(Object obj) {
//        if (obj == null) return 0.0;
//        try { return Double.parseDouble(obj.toString().replaceAll(",", "")); }
//        catch (Exception e) { return 0.0; }
//    }
//}













package in.Ragav.MGNREGA.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Service
public class DistrictService {

    //private static final String CSV_PATH = "C:/Users/ragav/Downloads/ee03643a-ee4c-48c2-ac30-9f2ff26ab722_cae4fd526afc26985df49924ae54ddae.csv";
	// use resource path relative to project root when running from IDE or jar
	private static final String CSV_PATH = "src/main/resources/data/mgnrega.csv";


    public Map<String, String> getDistrictData(String district) {
        Map<String, String> data = new LinkedHashMap<>();
        district = district.trim().toUpperCase();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length > 10 && parts[5].trim().equalsIgnoreCase(district)) {
                    data.put("District", parts[5]);
                    data.put("Total Job Cards Issued", parts[28]);
                    data.put("Total Workers", parts[29]);
                    data.put("Works Taken Up", parts[30]);
                    data.put("Wages", parts[31]);
                    data.put("Women Persondays", parts[32]);
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data; // Empty map if not found
    }
}


























