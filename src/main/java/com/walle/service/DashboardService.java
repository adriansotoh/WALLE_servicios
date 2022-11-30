package com.walle.service;

import com.walle.dto.GeneralStarsDto;

import java.util.List;

public interface DashboardService {
    public abstract List<GeneralStarsDto> getGeneralRanking();
}
