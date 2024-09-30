package fr.bryan_roger.gestionCompte.responseApi;


public class ResponseApiService  {

    private ResponseApiService() {
    }

    public static <T> ResponseAPI<T> createInstance(String code, String  message, T data){
        return new ResponseAPI<>(code, message, data);
    }

}
