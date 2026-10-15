package api.controller;

import api.dto.VendaRequestDTO;
import api.dto.VendaResponseDTO;
import api.service.VendaService;
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
// RESPONSABILIDADE: receber as requisições de /vendas, chamar o service e devolver o status
// REGRAS: usa a RN02 e a RN03 (aplicadas no VendaService); POST -> 201, GET -> 200,
//         regra violada ou campo inválido -> 400, comprador ou venda inexistente -> 404
// ROTAS ATENDIDAS: as de /vendas em docs/contrato-da-api.md.
// SITUAÇÃO: implementada
@Tag(name = "Vendas", description = "Registro e consulta das vendas da produção, com as regras RN02 e RN03")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @Operation(summary = "Registra uma venda",
            description = "RN02: a planta precisa ser a negociada com o comprador e o preço não pode "
                    + "divergir mais de 50% do preço combinado. RN03: a quantidade não pode passar do "
                    + "estoque da planta (total colhido menos total já vendido).")
    @ApiResponse(responseCode = "201", description = "Venda registrada: o corpo já traz o id gerado")
    @PostMapping
    public ResponseEntity<VendaResponseDTO> registrar(@RequestBody @Valid VendaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Lista as vendas", description = "Sem vendas registradas, a lista vem vazia, não 404.")
    @ApiResponse(responseCode = "200", description = "Lista de vendas (pode vir vazia)")
    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "Busca uma venda pelo id")
    @ApiResponse(responseCode = "200", description = "Venda encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarPorId(
            @Parameter(description = "Id da venda", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
