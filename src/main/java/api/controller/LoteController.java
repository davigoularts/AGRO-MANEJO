package api.controller;

import api.dto.LoteRequestDTO;
import api.dto.LoteResponseDTO;
import api.service.LoteService;
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
// RESPONSABILIDADE: receber as requisições de /lotes, chamar o service e devolver o status
// REGRAS: nenhuma (só HTTP); POST -> 201, GET -> 200, dado inválido -> 400, id inexistente -> 404
// ROTAS ATENDIDAS: as de /lotes em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Tag(name = "Lotes", description = "Cadastro e consulta dos lotes (talhões) de plantio")
@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastra um lote", description = "O id é gerado pela API, não pelo cliente.")
    @ApiResponse(responseCode = "201", description = "Lote cadastrado: o corpo já traz o id gerado")
    @PostMapping
    public ResponseEntity<LoteResponseDTO> cadastrar(@RequestBody @Valid LoteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Lista os lotes", description = "Sem lotes cadastrados, a lista vem vazia, não 404.")
    @ApiResponse(responseCode = "200", description = "Lista de lotes (pode vir vazia)")
    @GetMapping
    public ResponseEntity<List<LoteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Busca um lote pelo id")
    @ApiResponse(responseCode = "200", description = "Lote encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDTO> buscarPorId(
            @Parameter(description = "Id do lote", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
