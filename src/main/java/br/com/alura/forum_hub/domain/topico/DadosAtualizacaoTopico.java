package br.com.alura.forum_hub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull // O ID é obrigatório para o Java saber QUAL tópico ele deve editar
        Long id,

        String titulo,

        String mensagem
) {
}