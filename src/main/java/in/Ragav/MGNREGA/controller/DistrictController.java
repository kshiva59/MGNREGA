package in.Ragav.MGNREGA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import in.Ragav.MGNREGA.service.DistrictService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allow frontend/local testing
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    // Endpoint: http://localhost:8080/api/district?name=GUNTUR
    @GetMapping("/district")
    public Map<String, String> getDistrictData(@RequestParam String name) {
        return districtService.getDistrictData(name);
    }
}
