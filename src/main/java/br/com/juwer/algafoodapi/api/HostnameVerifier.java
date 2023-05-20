package br.com.juwer.algafoodapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HostnameVerifier {

    @GetMapping("/hostcheck")
    public String verificarHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress()
                + "-" + InetAddress.getLocalHost().getHostName();
    }
}
