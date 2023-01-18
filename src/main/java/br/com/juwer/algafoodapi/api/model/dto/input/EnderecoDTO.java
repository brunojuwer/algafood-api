package br.com.juwer.algafoodapi.api.model.dto.input;

import br.com.juwer.algafoodapi.api.model.dto.CidadeResumoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoDTO cidade;
}
