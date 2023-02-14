package br.com.juwer.algafoodapi.api.model.dto.input;

import br.com.juwer.algafoodapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoDTOInput {

    @NotNull
    @FileSize(max = "20KB")
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
