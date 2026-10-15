package api.controller;

import api.dto.InsumoRequestDTO;
import api.dto.InsumoResponseDTO;
import api.service.InsumoService;
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
// RESPONSABILIDADE: receber as requisições de /insumos, chamar o service e devolver o status
// REGRAS: nenhuma (só HTTP); POST -> 201, GET -> 200, dado inválido -> 400, id inexistente -> 404
// ROTAS ATENDIDAS: as de /insumos em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Tag(name = "Insumos", description = "Cadastro e consulta dos insumos (adubo, semente, defensivo) em estoque")
@RestController
@RequestMapping("/insumos")
public class InsumoController {

    private final InsumoService service;

    public InsumoController(InsumoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastra um insumo", description = "Não aceita dois insumos com o mesmo nome.")
    @ApiResponse(responseCode = "201", description = "Insumo cadastrado: o corpo já traz o id gerado")
    @PostMapping
    public ResponseEntity<InsumoResponseDTO> cadastrar(@RequestBody @Valid InsumoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Lista os insumos", description = "Sem insumos cadastrados, a lista vem vazia, não 404.")
    @ApiResponse(responseCode = "200", description = "Lista de insumos (pode vir vazia)")
    @GetMapping
    public ResponseEntity<List<InsumoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Busca um insumo pelo id")
    @ApiResponse(responseCode = "200", description = "Insumo encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<InsumoResponseDTO> buscarPorId(
            @Parameter(description = "Id do insumo", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
