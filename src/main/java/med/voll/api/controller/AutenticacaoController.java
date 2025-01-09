package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.AutenticacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
