package api.mapper;

import api.dto.VendaRequestDTO;
import api.dto.VendaResponseDTO;
import api.model.Comprador;
import api.model.Venda;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter VendaRequestDTO em Venda e Venda em VendaResponseDTO
// REGRAS: nenhuma (o comprador já chega buscado pelo Service)
// SITUAÇÃO: implementada
@Component
public class VendaMapper {

    public Venda toEntity(VendaRequestDTO dto, Comprador comprador) {
        return new Venda(comprador, dto.planta(), dto.quantidade(), dto.preco(), dto.data());
    }

    public VendaResponseDTO toResponse(Venda venda) {
        return new VendaResponseDTO(venda.getId(), venda.getComprador().getId(), venda.getPlanta(),
                venda.getQuantidade(), venda.getPreco(), venda.getData());
    }
}
