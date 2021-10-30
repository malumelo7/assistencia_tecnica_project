## next_solicitacao_servico
# Projeto de agendamento para solicitação de serviços em produtos com garantia para assistências técnicas.


Com o objetivo de controlar os agendamentos realizados em assistências técnicas, foi construído uma API com controle de horários disponíveis para realização do conserto de equipamentos. A API foi construída utilizando um banco de dados AWS em SQL, Springboot e JPA. As operações implementadas para API foram:

- Agendar atendimento ao cliente
- Desmarcar agendamento a partir do identificador do agendamento.
- Consultar agenda de agendamentos de uma data (de uma assistência).
- Listar assistências técnicas disponíveis (cadastradas).
- Listar horários livres por data para uma assistência técnica.

E as validações necessárias:

- Verificar se o agendamento está sendo realizado para uma assistência técnica válida e se o horário está disponível.
- Informações com domínio definido devem apenas aceitar valores válidos.
