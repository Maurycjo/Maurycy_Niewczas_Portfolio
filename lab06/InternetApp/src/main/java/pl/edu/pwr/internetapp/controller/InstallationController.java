package pl.edu.pwr.internetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.implementation.InstallationService;

import java.util.List;

@RestController
public class InstallationController {


    private final InstallationService installationService;

    @Autowired
    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    @GetMapping("/installations")
    List<Installation> getAllInstallations(){
        return installationService.getAllInstallation();
    }

    @GetMapping("/installations/{id}")
    Installation getOneInstallation(@PathVariable Long id){
        return installationService.getInstallationById(id);
    }

    @PostMapping("/installations/")
    Installation newInstallation(@RequestParam String address,
                                 @RequestParam String routerNumber,
                                 @RequestParam String serviceTypeName,
                                 @RequestParam Long clientId){
        return installationService.addInstallation(address, routerNumber, serviceTypeName, clientId);
    }

    @DeleteMapping("/installations/{id}")
    void deleteInstallation(@PathVariable Long id){
        installationService.deleteInstallation(id);
    }

//    @GetMapping("/installations")
//    List<Installation> getInstallationByClientId(@RequestParam Long clientId){
//        return installationService.getAllInstallationsByClientId(clientId);
//    }



}
