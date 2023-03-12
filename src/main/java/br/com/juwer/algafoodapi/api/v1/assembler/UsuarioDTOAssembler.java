package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.UsuarioController;
import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public UsuarioDTOAssembler() {
        super(UsuarioController.class, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO toModel(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        usuarioDTO.add(hateoasAlgaLinks.linkToCliente(usuarioDTO.getId()));

        usuarioDTO.add(hateoasAlgaLinks.linkToClientes(false));

        usuarioDTO.add(hateoasAlgaLinks.linkToClienteGrupos(usuarioDTO.getId()));

        return usuarioDTO;
    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linkToClientes(true));
    }
}
