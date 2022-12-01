package com.walle.controller;

import com.walle.dto.ByMonthDashboardDto;
import com.walle.dto.DashboardDto;
import com.walle.dto.GeneralStarsDto;
import com.walle.repository.TicketRepository;
import com.walle.utils.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/por-mes")
    @ResponseBody
    public  ResponseEntity<?> getTickets(@Param("start") String start, @Param("end") String end) {
        List<ByMonthDashboardDto> salida = ticketRepo.getByMonthDashboard(start, end);

        return  ResponseEntity.ok(salida);
    }
    @GetMapping("")
    @ResponseBody
    public  ResponseEntity<?> getDashboard() {
        DashboardDto salida = ticketRepo.getDashboard();
        return  ResponseEntity.ok(salida);
    }

}
