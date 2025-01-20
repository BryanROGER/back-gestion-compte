package fr.bryan_roger.gestionCompte.bll;


import fr.bryan_roger.gestionCompte.bo.ResponseAPI;

public class ResponseApiService  {

    private ResponseApiService() {
    }

    public static <T> ResponseAPI<T> createInstance(String code, String  message, T data){
        return new ResponseAPI<>(code, message, data);
    }

}
