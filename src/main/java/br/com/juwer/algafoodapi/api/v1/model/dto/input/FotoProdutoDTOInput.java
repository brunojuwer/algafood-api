package br.com.juwer.algafoodapi.api.v1.model.dto.input;

import br.com.juwer.algafoodapi.core.validation.FileContentType;
import br.com.juwer.algafoodapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoDTOInput {

    @NotNull
    @FileSize(max = "600KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
