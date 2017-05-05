package com.motorcycle.areas.logs.services;

import com.motorcycle.areas.logs.models.bindingModels.LogModel;
import com.motorcycle.areas.logs.models.viewModels.LogView;

import java.util.List;

public interface LogService {

    void persist(LogModel logModel);

    List<LogView> getAllByUsername(String username);

    void deleteAllLogs();
}
