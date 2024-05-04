package com.stephen.todolist.controller;

import com.stephen.todolist.pojo.Credential;
import com.stephen.todolist.service.KeepieService;
import com.stephen.todolist.util.CredentialUpdater;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Data
@RestController
public class CredentialReceiverController implements UserDetailsService {

    @Autowired
    private KeepieService keepieService;

    @Autowired
    private CredentialUpdater credentialUpdater;

    @Value("${server.port}")
    private String receivedPort;

    @Value("${receive-url}")
    private String receiveUrl;


    @ApiIgnore
    @PostMapping("/givemesecret")
    public ResponseEntity<String> receiveCredentials(@RequestBody Credential credential) {
        credentialUpdater.setCredential(credential);
        return new ResponseEntity<>("received", HttpStatus.OK);
    }

    @GetMapping("/notify_keepie")
    public ResponseEntity<String> notifyKeepie() {

        // keep notifying keepie until receiving credential
        while (credentialUpdater.getCredential() == null) {
            keepieService.getCredential(receiveUrl + "/givemesecret");
        }
        return new ResponseEntity<>("Update user credential successfully", HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Credential credential;
        if ((credential = credentialUpdater.getCredential()) != null) {
            return org.springframework.security.core.userdetails.User.withUsername(credential.getUsername())
                    .password("{noop}" + credential.getPassword())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("No credential available");
        }
    }
}
