package testEx.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import testEx.dto.SockDTO;
import testEx.models.Sock;
import testEx.services.SocksService;
import testEx.util.SockErrorResponse;
import testEx.util.SockNotCreatedException;
import testEx.util.SockNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksRESTController {
    private final SocksService socksService;
    private  final ModelMapper modelMapper;

    @Autowired
    public SocksRESTController(SocksService socksService, ModelMapper modelMapper) {
        this.socksService = socksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/mahadi")
    public List<Sock> giveMeAllSocks(){
        return socksService.findAll();
    }

    @GetMapping()
    public int getAmountOfSocks(@RequestParam("color") String color,
                                @RequestParam ("operation") String operation,
                                 @RequestParam ("cottonPart") long cottonPart){

       return socksService.findAmountByCottonPartAndColor(color,operation,cottonPart);
    }

    @PostMapping( "/outcome")
    public ResponseEntity<HttpStatus> minusSocks(@RequestBody @Valid SockDTO sockDTO,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SockNotCreatedException(errorMessage.toString());
        }
        socksService.saveSock(converteToSock(sockDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping( "/income")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SockDTO sockDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SockNotCreatedException(errorMessage.toString());
        }
        socksService.saveSock(converteToSock(sockDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler

    private ResponseEntity<SockErrorResponse> handleException(SockNotFoundException e) {

        SockErrorResponse response = new SockErrorResponse(
                "request parameters are missing or have an incorrect format",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SockErrorResponse> handleException(SockNotCreatedException e) {

        SockErrorResponse response = new SockErrorResponse(
                "request parameters are missing or have an incorrect format",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Sock converteToSock(SockDTO sockDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sockDTO, Sock.class);
    }
}
