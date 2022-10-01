package com.valet.qrmainserver.controller;


import com.valet.qrmainserver.model.User;
import com.valet.qrmainserver.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private final TokenService tokenService;
    private final RecordService recordService;
    private final OrganizationService organizationService;
    private final TeamService teamService;


    @GetMapping("/login")
    public User login(@AuthenticationPrincipal User user){
        user.setPassword("none");
        return user;
    }


    @GetMapping("/getToken")
    public String getToken(@RequestParam double x,
                                 @RequestParam double y,
                                 @RequestParam int radius,
                                 @AuthenticationPrincipal User user) {
        return tokenService.createToken(x, y, radius, user.getOrganizationId(), teamService.getIdByLeadId(user.getId()));
    }

    @PostMapping("/addRecord")
    public boolean setRecord(@RequestParam double x,
                                  @RequestParam double y,
                                  @RequestParam String token,
                                  @RequestBody LocalDateTime now,
                                  @AuthenticationPrincipal User user){
        
        return recordService.addRecord(x, y, token, user, now);
    }

    @GetMapping("/getOrganizationsName")
    public List<String> getOrganizationsName(@RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "0") int page){
        return organizationService.getOrganizationsName(page, size).getContent();
    }

    @GetMapping("/test")
    public Object test(){
        return LocalDateTime.now();
    }

}
