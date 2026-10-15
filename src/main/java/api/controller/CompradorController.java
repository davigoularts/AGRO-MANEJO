package api.controller;

import api.dto.CompradorRequestDTO;
import api.dto.CompradorResponseDTO;
import api.service.CompradorService;
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
@RestController
@RequestMapping("/compradores")
public class CompradorController {

    private final CompradorService service;

    public CompradorController(CompradorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompradorResponseDTO> cadastrar(@RequestBody @Valid CompradorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompradorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompradorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
