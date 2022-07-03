package com.valet.qr_auth.controller;

import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private final JwtService jwtService;
    private final PersonService personService;
    private final PointService pointService;
    private final RecordService recordService;

    @GetMapping("/generateToken")
    public String generateToken(@RequestParam Long pointId){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtService.generateToken(person.getId(), pointId);
    }


    @PostMapping("/generatedPoint")
    public Point generatedNewPoint(@RequestBody Point point){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        point.setCreatorId(person.getId());
        return pointService.generatePoint(point);
    }

    @GetMapping("/login")
    public Person login(){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        person.setPassword("NONE");
        return person;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Person person){
        if (existUser(person.getPhone())){
            return new ResponseEntity<>("Юзер уже есть в базе!", HttpStatus.BAD_REQUEST);
        } else {
            person = personService.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @PostMapping("/setRecord")
    public Long setRecord(@RequestParam("token") String token,
                          @RequestParam("x") Double x,
                          @RequestParam("y") Double y){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordService.addNewRecord(person.getId(), token, "IN", x, y);
    }

    @GetMapping("/getAllRecord")
    public List<Person> getAllRecord(@RequestParam Long pointId){
        return recordService.getAllRecordsByPointId(pointId);
    }

    @GetMapping("/getAllPoint")
    public List<Point> getAllPoint(){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return pointService.getAllPoint(person.getId());
    }

    @GetMapping("/existUser")
    public boolean existUser(@RequestParam String phone){
        return personService.existUser(phone);
    }

    @PostMapping("/changeOrganization")
    public boolean changeOrganization(@RequestParam String organization){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.changeOrganization(person, organization);
    }

    @PostMapping("/changePhone")
    public boolean changePhone(@RequestParam String phone){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.changePhone(person, phone);
    }

    @PostMapping("/changeName")
    public boolean changeName(@RequestParam String name){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.changeName(person, name);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody String password){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.changePassword(person.getId(), password);
    }

    @DeleteMapping("/deleteAccount")
    public boolean deleteAccount(){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordService.deleteAllRecordByPersonId(person.getId()) &&
                personService.deleteUser(person);
    }
}
