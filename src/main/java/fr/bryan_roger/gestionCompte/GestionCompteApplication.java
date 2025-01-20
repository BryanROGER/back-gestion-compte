package fr.bryan_roger.gestionCompte;

import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.dal.BudgetRepository;

import fr.bryan_roger.gestionCompte.bo.Household;
import fr.bryan_roger.gestionCompte.dal.HomeRepository;
import fr.bryan_roger.gestionCompte.bo.Income;
import fr.bryan_roger.gestionCompte.dal.IncomeRepository;
import fr.bryan_roger.gestionCompte.bo.Spend;
import fr.bryan_roger.gestionCompte.dal.SpendRepository;
import fr.bryan_roger.gestionCompte.bo.Tag;
import fr.bryan_roger.gestionCompte.dal.TagRepository;
import fr.bryan_roger.gestionCompte.bo.User;
import fr.bryan_roger.gestionCompte.dal.UserRepository;
import fr.bryan_roger.gestionCompte.bo.Wallet;
import fr.bryan_roger.gestionCompte.dal.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                                        IncomeRepository incomeRepository, WalletRepository walletRepository, BudgetRepository budgetRepository, HomeRepository homeRepository) {
        return args -> {

            //Gestion des dates

            LocalDate currentDate = LocalDate.now();
            LocalDate dateLastMonth = currentDate.minusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
            String dateOneMonthAgo = dateLastMonth.format(formatter);
            String dateCurrentMonth = currentDate.format(formatter);


            Tag tagLoisir = tagRepository.save(new Tag(null,  "Loisir", false, true));
            Tag tagCourse = tagRepository.save(new Tag(null,"Courses", false, true));
            Tag tagSalaire = tagRepository.save(new Tag(null, "Salaire", true, false));
            tagRepository.findAll().forEach(System.out::println);

            Household foyerPrincipal = homeRepository.save(new Household(null,List.of(tagSalaire), List.of(tagCourse, tagLoisir), "Foyer principal"));
            Household foyerSecondaire = homeRepository.save(new Household(null,List.of(tagSalaire), List.of(tagCourse, tagLoisir), "Seconde Zone"));

            User bryan = userRepository.save(new User("bryan@gmail.com","Roger", "Bryan", "#9AED8F", "#109592",
                    null, BigDecimal.valueOf(.1),  passwordEncoder.encode("test"), "ROLES_ADMIN", List.of(foyerPrincipal, foyerSecondaire)));
            User flora = userRepository.save(new User("flora@gmail.com","Kurti", "Flora", "#109592", "#FFEFBF",
                    null, BigDecimal.valueOf(.9),  passwordEncoder.encode("test"), "ROLES_USER", List.of(foyerPrincipal)));
            userRepository.findAll().forEach(System.out::println);

            Spend spendCourses = spendRepository.save(new Spend(null, "Courses", dateCurrentMonth, bryan, List.of(bryan, flora), BigDecimal.valueOf(75.23), tagCourse, foyerPrincipal,1));
            Spend spendHomeTrainer = spendRepository.save(new Spend(null, "Home trainer", dateCurrentMonth, bryan, List.of(bryan), BigDecimal.valueOf(499.99), tagLoisir, foyerPrincipal,2));
            Spend spendIglooPortatif = spendRepository.save(new Spend(null, "Igloo portatif", dateCurrentMonth, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir, foyerPrincipal,3));
            Spend spendIglooPortatifMoisPrecedent = spendRepository.save(new Spend(null, "Igloo portatif", dateOneMonthAgo, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir, foyerPrincipal,1));
            Spend spend2eFoyer = spendRepository.save(new Spend(null, "Test dep 2e foyer", dateCurrentMonth, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir, foyerSecondaire,1));
            spendRepository.findAll().forEach(System.out::println);


            Income salaireBryan = incomeRepository.save(new Income(null,tagSalaire, bryan, BigDecimal.valueOf(2000), dateCurrentMonth,1));
            Income salaireFlora = incomeRepository.save(new Income(null,tagSalaire, flora, BigDecimal.valueOf(5000), dateCurrentMonth,2));
            incomeRepository.findAll().forEach(System.out::println);

            Budget budgetCourse = budgetRepository.save(new Budget(null,BigDecimal.valueOf(400), tagCourse));
            Budget budgetLoisirBryan = budgetRepository.save(new Budget(null,BigDecimal.valueOf(200), tagLoisir));
            Budget budgetLoisirFlora = budgetRepository.save(new Budget(null,BigDecimal.valueOf(1500), tagLoisir));
            budgetRepository.findAll().forEach(System.out::println);


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date yesterday = calendar.getTime();

            Wallet walletBryanActive = walletRepository.save(new Wallet(null,List.of(budgetCourse, budgetLoisirBryan), yesterday, null, true));
            Wallet walletFloraActive = walletRepository.save(new Wallet(null,List.of(budgetCourse, budgetLoisirFlora), yesterday, null, true));
            walletRepository.findAll().forEach(System.out::println);


        };
    }


}
