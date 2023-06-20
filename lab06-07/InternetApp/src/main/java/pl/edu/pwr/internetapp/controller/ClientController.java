package pl.edu.pwr.internetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.service.implementation.ClientService;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    Client getOneClient(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    @PostMapping("/clients/")
    Client newClient(@RequestParam String firstName, @RequestParam String lastName){
        return clientService.addClient(firstName, lastName);
    }

    @PostMapping("/clients/{id}")
    Client modifyClient(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName){
        return clientService.modifyClient(id, firstName, lastName);
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }


}
