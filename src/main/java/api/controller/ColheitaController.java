package api.controller;

import api.dto.ColheitaRequestDTO;
import api.dto.ColheitaResponseDTO;
import api.service.ColheitaService;
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
@RestController
@RequestMapping("/colheitas")
public class ColheitaController {

    private final ColheitaService service;

    public ColheitaController(ColheitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ColheitaResponseDTO> registrar(@RequestBody @Valid ColheitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ColheitaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColheitaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
