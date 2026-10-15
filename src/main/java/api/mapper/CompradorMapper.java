package api.mapper;

import api.dto.CompradorRequestDTO;
import api.dto.CompradorResponseDTO;
import api.model.Comprador;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter CompradorRequestDTO em Comprador e Comprador em CompradorResponseDTO
// REGRAS: guarda o CNPJ só com números (tira pontos, barra e traço)
// SITUAÇÃO: implementada
@Component
public class CompradorMapper {

    public Comprador toEntity(CompradorRequestDTO dto) {
        String cnpjSoNumeros = dto.cnpj().replaceAll("\\D", "");
        return new Comprador(cnpjSoNumeros, dto.endereco(), dto.plantaPreco(), dto.preco());
    }

    public CompradorResponseDTO toResponse(Comprador comprador) {
        return new CompradorResponseDTO(comprador.getId(), comprador.getCnpj(), comprador.getEndereco(),
                comprador.getPlantaPreco(), comprador.getPreco());
    }
}
