package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.model.dto.input.FotoProdutoDTOInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @Valid FotoProdutoDTOInput fotoProdutoDTOInput
            ) {
        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoDTOInput.getArquivo().getOriginalFilename();

        var caminhoEArquivoParaSalvar = Path.of("/home/brunojuwer/Documents/upload_test", nomeArquivo);

        try {
            fotoProdutoDTOInput.getArquivo().transferTo(caminhoEArquivoParaSalvar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
