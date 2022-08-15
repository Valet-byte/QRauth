package com.valet.qr_auth_admin.controller;

import com.valet.qr_auth_admin.model.Admin;
import com.valet.qr_auth_admin.model.DTO.UserDTO;
import com.valet.qr_auth_admin.model.MyTime;
import com.valet.qr_auth_admin.model.Record;
import com.valet.qr_auth_admin.repo.RecordRepo;
import com.valet.qr_auth_admin.repo.UserDTORepo;
import com.valet.qr_auth_admin.service.interfaces.AdminService;
import com.valet.qr_auth_admin.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private final AdminService adminService;
    private final TokenService tokenService;
    private final RecordRepo recordRepo;
    private final UserDTORepo userDTORepo;

    @GetMapping("/login")
    public Mono<Admin> login(@AuthenticationPrincipal Mono<Admin> admin){
        return admin.map(a -> {a.setPassword("NONE"); return a;});
    }

    @PostMapping("/registration")
    public Mono<ResponseEntity<?>> registration(@RequestBody Admin admin){


        try {
            return adminService.registration(admin)
                    .onErrorReturn(Admin.builder().build())
                    .map(a -> {
                        if (a.getName() == null && a.getEmail() == null){
                            return new ResponseEntity<>("Что-то пошло не так", HttpStatus.BAD_REQUEST);
                        }
                        return new ResponseEntity<>(a, HttpStatus.CREATED);
                    });
        } catch (Exception e){
            e.printStackTrace();
            return Mono.error(new Throwable("Что-то пошло не так"));
        }

    }

    @GetMapping("/getToken")
    public Mono<String> getToken(@RequestParam double x,
                                 @RequestParam double y,
                                 @RequestParam int radius,
                                 @AuthenticationPrincipal Admin admin){
        return tokenService.createToken(x, y, radius, admin.getId(), admin.getOrganization().getId());
    }

    @GetMapping("/test")
    public Object test(){
        return LocalDate.now();
    }

    @GetMapping("/getRecordByDateAndMakeInTime")
    public Flux<Record> getStats(
            @RequestBody MyTime myTime,
            @AuthenticationPrincipal Admin admin){
        return recordRepo.findAllRecordWhoMadeFromAndTo(admin.getId(), myTime.getFrom(), myTime.getTo(), myTime.getArrivalTime());
    }

    @GetMapping("/getCountRecordByDate")
    public Mono<Integer> getCountRecordByDate(
            @RequestBody MyTime myTime,
            @AuthenticationPrincipal Admin admin){
        return recordRepo.countRecordFromToDate(admin.getId(), myTime.getFrom(), myTime.getTo());
    }

    @GetMapping("/getUsersByIds")
    public Flux<UserDTO> getUsersByIds(@RequestBody Iterable<Long> ids){
        return userDTORepo.findAllById(ids);
    }

}
