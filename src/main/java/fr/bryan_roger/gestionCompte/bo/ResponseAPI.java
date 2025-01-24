package fr.bryan_roger.gestionCompte.bo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseAPI <T> {
    private String code;
    private String message;
    private T data;
}


