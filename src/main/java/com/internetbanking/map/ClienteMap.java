package com.internetbanking.map;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.internetbanking.dto.ClienteDTO;
import com.internetbanking.request.ClienteRequest;
import com.internetbanking.response.ClienteResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteMap {

    private Date date;

    public ClienteResponse dtoToResponse(ClienteDTO clienteDTO) {

        return new ClienteResponse(clienteDTO.getNome(), clienteDTO.getPlanoExclusive(), clienteDTO.getSaldo());
    }

    public ClienteDTO requestToDTO(ClienteRequest request) {

        SimpleDateFormat dateFormat
                = new SimpleDateFormat("MM/dd/yyyy");

        try {

            date = dateFormat.parse(request.getDataNascimento());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        BigDecimal saldo = new BigDecimal("0.00");

        ClienteDTO c = new ClienteDTO(request.getNome(), request.getPlanoExclusive(), saldo, date);

        return c;
    }
}
