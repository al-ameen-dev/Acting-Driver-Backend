package com.alameendev.ActingDriver.exceptions.globalexceptioncontroller;


import com.alameendev.ActingDriver.exceptions.actor.ActorNotFoundException;
import com.alameendev.ActingDriver.exceptions.booking.BookingNotFoundException;
import com.alameendev.ActingDriver.exceptions.car.CarNotFoundException;
import com.alameendev.ActingDriver.exceptions.client.ClientNotFoundException;
import com.alameendev.ActingDriver.exceptions.dto.ErrorResponseDTO;
import com.alameendev.ActingDriver.exceptions.filestorage.FileIsEmptyException;
import com.alameendev.ActingDriver.exceptions.filestorage.FileStorageException;
import com.alameendev.ActingDriver.exceptions.filestorage.ProfileImageNotFoundException;
import com.alameendev.ActingDriver.exceptions.job.JobNotFoundException;
import com.alameendev.ActingDriver.exceptions.user.UserAlreadyExistException;
import com.alameendev.ActingDriver.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {})
    ResponseEntity<ErrorResponseDTO> handleException(Exception ex)
    {
        String message;
        HttpStatus status;
        if(ex instanceof UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof CarNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof ActorNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof ClientNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof FileIsEmptyException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof FileStorageException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof ProfileImageNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof JobNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else if(ex instanceof UserAlreadyExistException){
            message = ex.getMessage();
            status = HttpStatus.IM_USED;
        }else if(ex instanceof BookingNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else{
            message = "something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponseDTO response = ErrorResponseDTO.builder().errorMessage(message).build();
        return ResponseEntity.status(status).body(response);
    }
}
