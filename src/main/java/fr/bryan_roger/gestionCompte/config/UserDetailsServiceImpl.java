//package fr.bryan_roger.gestionCompte.config;
//
//import fr.bryan_roger.gestionCompte.bo.User;
//import fr.bryan_roger.gestionCompte.dal.UserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        User user = userRepository.findById(userEmail).stream().findFirst().orElse(null);
//        if(user == null){
//            throw new UsernameNotFoundException("could not found user..!!");
//        }
//        return new CustomUserDetails(user);
//    }
//}
