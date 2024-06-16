package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.BillboardItem;
import es.udc.paproject.backend.model.entities.Movie;
import es.udc.paproject.backend.model.entities.Session;
import es.udc.paproject.backend.model.exceptions.*;

import java.time.LocalDate;
import java.util.List;


public interface BillboardService {
    List<BillboardItem> showBillboard(LocalDate date) throws IncorrectDateTime;
    Session findSession(Long sessionId) throws InstanceNotFoundException, SessionAlreadyStartedException;
    Movie findMovie(Long movieId) throws InstanceNotFoundException;
}
