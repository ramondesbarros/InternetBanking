package com.internetbanking.map;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.response.ClienteResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteMap {

    public ClienteResponse dtoToResponse(ClienteDTO clienteDTO) {

        return new ClienteResponse(clienteDTO.getNome(), clienteDTO.getPlanoExclusive(), clienteDTO.getSaldo());
    }

    //TODO
    public ClienteDTO requestToDTO(ClienteRequest request) {

        Date dataNascimento = formatarData(request.getDataNascimento());

        return new ClienteDTO(request.getNome(), request.getPlanoExclusive(),  new BigDecimal("0.00"), dataNascimento);

    }

    private Date formatarData(String data) {

        try {

            SimpleDateFormat formatoDiaMesAno = new SimpleDateFormat("dd/MM/yyyy");

            Date dataFormatada = formatoDiaMesAno.parse(data);

            return dataFormatada;

        } catch (ParseException e) {

            throw new RuntimeException(e);
        }
    }
}
