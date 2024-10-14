package fr.bryan_roger.gestionCompte;

import fr.bryan_roger.gestionCompte.budget.Budget;
import fr.bryan_roger.gestionCompte.budget.BudgetRepository;
import fr.bryan_roger.gestionCompte.config.security.UserRole;
import fr.bryan_roger.gestionCompte.config.security.UserRoleRepository;
import fr.bryan_roger.gestionCompte.home.Home;
import fr.bryan_roger.gestionCompte.home.HomeRepository;
import fr.bryan_roger.gestionCompte.income.Income;
import fr.bryan_roger.gestionCompte.income.IncomeRepository;
import fr.bryan_roger.gestionCompte.spend.Spend;
import fr.bryan_roger.gestionCompte.spend.SpendRepository;
import fr.bryan_roger.gestionCompte.tag.Tag;
import fr.bryan_roger.gestionCompte.tag.TagRepository;
import fr.bryan_roger.gestionCompte.user.User;
import fr.bryan_roger.gestionCompte.user.UserRepository;
import fr.bryan_roger.gestionCompte.wallet.Wallet;
import fr.bryan_roger.gestionCompte.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class GestionCompteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCompteApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(TagRepository tagRepository, UserRepository userRepository, SpendRepository spendRepository,
                                        IncomeRepository incomeRepository, WalletRepository walletRepository, BudgetRepository budgetRepository, HomeRepository homeRepository, UserRoleRepository roleRepository) {
        return args -> {

            Tag tagLoisir = tagRepository.save(new Tag("Loisir",false, true));
            Tag tagCourse = tagRepository.save(new Tag("Courses", false, true));
            Tag tagSalaire = tagRepository.save(new Tag("Salaire", true, false));
            tagRepository.findAll().forEach(System.out::println);

            Home foyerPrincipal = homeRepository.save(new Home(List.of(tagSalaire), List.of(tagCourse, tagLoisir), "Foyer principal"));
            Home foyerSecondaire = homeRepository.save(new Home(List.of(tagSalaire), List.of(tagCourse, tagLoisir),"Seconde Zone"));

            UserRole adminRole = roleRepository.save(new UserRole("ADMIN"));
            UserRole userRole = roleRepository.save(new UserRole("USER"));

//public User(String lastname, String firstname, String backgroundColor, String letterColor, Wallet wallet, BigDecimal repartition, String email, String password, Set<UserRole> roles, List<Home> households) {
            User bryan = userRepository.save(new User("Roger", "Bryan", "#9AED8F", "#109592",
                    null,BigDecimal.valueOf(.1),"bryan@gmail.com", passwordEncoder.encode("test"), Set.of(userRole, adminRole), List.of(foyerPrincipal, foyerSecondaire)));
			User flora = userRepository.save(new User("Kurti", "Flora", "#109592", "#FFEFBF",
                    null,BigDecimal.valueOf(.9),"flora@gmail.com", passwordEncoder.encode("test"),Set.of(userRole), List.of(foyerPrincipal)));
			userRepository.findAll().forEach(System.out::println);

            Spend spendCourses = spendRepository.save(new Spend("Courses", "09-2024", bryan, List.of(bryan, flora), BigDecimal.valueOf(75.23), tagCourse));
			Spend spendHomeTrainer = spendRepository.save(new Spend("Home trainer", "09-2024", bryan, List.of(bryan), BigDecimal.valueOf(499.99), tagLoisir));
			Spend spendIglooPortatif = spendRepository.save(new Spend("Igloo portatif", "09-2024", flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir));
			Spend spendIglooPortatifMoisPrecedent = spendRepository.save(new Spend("Igloo portatif", "08-2024", flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir));
			spendRepository.findAll().forEach(System.out::println);




			Income salaireBryan = incomeRepository.save(new Income(tagSalaire, bryan, BigDecimal.valueOf(2000), "09-2024"));
			Income salaireFlora = incomeRepository.save(new Income(tagSalaire, flora, BigDecimal.valueOf(5000), "09-2024"));
            incomeRepository.findAll().forEach(System.out::println);

            Budget budgetCourse = budgetRepository.save(new Budget(BigDecimal.valueOf(400), tagCourse));
            Budget budgetLoisirBryan = budgetRepository.save(new Budget(BigDecimal.valueOf(200), tagLoisir));
            Budget budgetLoisirFlora = budgetRepository.save(new Budget(BigDecimal.valueOf(1500), tagLoisir));
            budgetRepository.findAll().forEach(System.out::println);


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date yesterday = calendar.getTime();

            Wallet walletBryanActive = walletRepository.save(new Wallet(List.of(budgetCourse, budgetLoisirBryan), yesterday, null, true ));
            Wallet walletFloraActive = walletRepository.save(new Wallet(List.of(budgetCourse, budgetLoisirFlora), yesterday, null, true ));
            walletRepository.findAll().forEach(System.out::println);



        };
    }
}
