package api.controller;

import api.dto.CompradorRequestDTO;
import api.dto.CompradorResponseDTO;
import api.service.CompradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CAMADA: Controller
// RESPONSABILIDADE: receber as requisições de /compradores, chamar o service e devolver o status
// REGRAS: nenhuma (só HTTP); POST -> 201, GET -> 200, dado inválido -> 400, id inexistente -> 404
// ROTAS ATENDIDAS: as de /compradores em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Tag(name = "Compradores", description = "Cadastro e consulta dos compradores, com o preço combinado usado na RN02")
@RestController
@RequestMapping("/compradores")
public class CompradorController {

    private final CompradorService service;

    public CompradorController(CompradorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastra um comprador",
            description = "O CNPJ pode vir com ou sem pontuação e é guardado só com números. "
                    + "Não aceita CNPJ repetido.")
    @ApiResponse(responseCode = "201", description = "Comprador cadastrado: o corpo já traz o id gerado")
    @PostMapping
    public ResponseEntity<CompradorResponseDTO> cadastrar(@RequestBody @Valid CompradorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Lista os compradores", description = "Sem compradores cadastrados, a lista vem vazia, não 404.")
    @ApiResponse(responseCode = "200", description = "Lista de compradores (pode vir vazia)")
    @GetMapping
    public ResponseEntity<List<CompradorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Busca um comprador pelo id")
    @ApiResponse(responseCode = "200", description = "Comprador encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CompradorResponseDTO> buscarPorId(
            @Parameter(description = "Id do comprador", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
