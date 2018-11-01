package com.apap.tutorial07.service;

import java.util.Optional;

import com.apap.tutorial07.model.PilotModel;


/**
 * PilotService
 */
public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    PilotModel addPilot(PilotModel pilot);
    void deletePilotByLicenseNumber(String licenseNumber);
    void deletePilot(PilotModel pilot);
    Optional<PilotModel> getPilotDetailById(long id);
    void updatePilot (long pilotId, PilotModel pilot);

}