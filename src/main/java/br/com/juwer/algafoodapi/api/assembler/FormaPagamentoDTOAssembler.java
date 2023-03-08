package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.FormaPagamentoController;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.utils.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public FormaPagamentoDTOAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDTO.class);
    }

    @Override
    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
        FormaPagamentoDTO formaPagamentoDTO = modelMapper.map(formaPagamento, FormaPagamentoDTO.class);

        formaPagamentoDTO.add(hateoasAlgaLinks.linkToFormaPagamento(formaPagamentoDTO.getId()));
        formaPagamentoDTO.add(hateoasAlgaLinks.linkToFormasPagamento(false));

        return formaPagamentoDTO;
    }

    @Override
    public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linkToFormasPagamento(true));
    }
}
