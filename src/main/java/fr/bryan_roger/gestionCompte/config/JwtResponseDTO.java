//package fr.bryan_roger.gestionCompte.config;
//
//public class JwtResponseDTO {
//    private String accessToken;
//
//    // Constructeur avec paramètre
//    public JwtResponseDTO(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    // Constructeur sans paramètre
//    public JwtResponseDTO() {
//    }
//
//    // Getter
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    // Setter
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    // Méthode builder
//    public static JwtResponseDTOBuilder builder() {
//        return new JwtResponseDTOBuilder();
//    }
//
//    // Classe interne pour le builder pattern
//    public static class JwtResponseDTOBuilder {
//        private String accessToken;
//
//        public JwtResponseDTOBuilder accessToken(String accessToken) {
//            this.accessToken = accessToken;
//            return this;
//        }
//
//        public JwtResponseDTO build() {
//            return new JwtResponseDTO(accessToken);
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "JwtResponseDTO{" +
//                "accessToken='" + accessToken + '\'' +
//                '}';
//    }
//}
