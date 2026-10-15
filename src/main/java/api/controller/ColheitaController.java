package api.controller;

import api.dto.ColheitaRequestDTO;
import api.dto.ColheitaResponseDTO;
import api.service.ColheitaService;
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
// RESPONSABILIDADE: receber as requisições de /colheitas, chamar o service e devolver o status
// REGRAS: usa a RN01 (aplicada no ColheitaService); POST -> 201, GET -> 200,
//         regra violada ou campo inválido -> 400, lote ou colheita inexistente -> 404
// ROTAS ATENDIDAS: as de /colheitas em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Tag(name = "Colheitas", description = "Registro e consulta das colheitas, com a regra RN01")
@RestController
@RequestMapping("/colheitas")
public class ColheitaController {

    private final ColheitaService service;

    public ColheitaController(ColheitaService service) {
        this.service = service;
    }

    @Operation(summary = "Registra uma colheita",
            description = "RN01: o lote precisa existir e a quantidade não pode passar de "
                    + "100 sacas por hectare do lote.")
    @ApiResponse(responseCode = "201", description = "Colheita registrada: o corpo já traz o id gerado")
    @PostMapping
    public ResponseEntity<ColheitaResponseDTO> registrar(@RequestBody @Valid ColheitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Lista as colheitas", description = "Sem colheitas registradas, a lista vem vazia, não 404.")
    @ApiResponse(responseCode = "200", description = "Lista de colheitas (pode vir vazia)")
    @GetMapping
    public ResponseEntity<List<ColheitaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Busca uma colheita pelo id")
    @ApiResponse(responseCode = "200", description = "Colheita encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ColheitaResponseDTO> buscarPorId(
            @Parameter(description = "Id da colheita", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
