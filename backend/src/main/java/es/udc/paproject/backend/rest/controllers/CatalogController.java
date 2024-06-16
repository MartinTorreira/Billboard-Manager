package es.udc.paproject.backend.rest.controllers;

import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.BillboardService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BillboardItemDto;
import es.udc.paproject.backend.rest.dtos.MovieDto;
import es.udc.paproject.backend.rest.dtos.SessionDto;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static es.udc.paproject.backend.rest.dtos.BillboardItemConversor.toBillboardItemDtos;
import static es.udc.paproject.backend.rest.dtos.MovieConversor.toMovieDto;
import static es.udc.paproject.backend.rest.dtos.SessionConversor.toSessionDto;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final static String INCORRECT_DATETIME_EXCEPTION_CODE = "project.exceptions.IncorrectDateTime";
    private final static String NON_EXISTENT_MOVIE_EXCEPTION = "project.exceptions.NonExistentMovie";


    @Autowired
    private BillboardService billboardService;
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/billboard")
    public List<BillboardItemDto> showBillboard(@RequestParam(defaultValue =  "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)throws IncorrectDateTime{
        return toBillboardItemDtos(billboardService.showBillboard(date));
    }

    @GetMapping("/movies/{id}")
    public MovieDto findMovieById(@PathVariable Long id) throws InstanceNotFoundException {
        return toMovieDto(billboardService.findMovie(id));
    }

    @GetMapping("/sessions/{id}")
    public SessionDto findSessionById(@PathVariable Long id) throws InstanceNotFoundException, SessionAlreadyStartedException {
        return toSessionDto(billboardService.findSession(id));
    }

    @ExceptionHandler(IncorrectDateTime.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleIncorrectDateTimeException(IncorrectDateTime exception, Locale locale){

        String errorMessage = messageSource.getMessage(INCORRECT_DATETIME_EXCEPTION_CODE, null, exception.getLocalizedMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(NonExistentMovieException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleNonExistentMovie(NonExistentMovieException exception, Locale locale){

        String errorMessage = messageSource.getMessage(NON_EXISTENT_MOVIE_EXCEPTION, null, exception.getLocalizedMessage(),
                locale);

        return new ErrorsDto(errorMessage);
    }


}