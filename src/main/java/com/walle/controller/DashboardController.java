package com.walle.controller;

import com.walle.dto.GeneralStarsDto;
import com.walle.repository.TicketRepository;
import com.walle.utils.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class DashboardController {

    @Autowired
    TicketRepository ticketRepo;

    @GetMapping("/por-score")
    @ResponseBody
    public ResponseEntity<?> getScores() {
        List<GeneralStarsDto> salida = ticketRepo.getGeneralStarDashboard();

        return ResponseEntity.ok(salida);
    }

}
