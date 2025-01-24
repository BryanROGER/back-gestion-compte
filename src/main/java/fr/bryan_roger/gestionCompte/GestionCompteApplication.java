package fr.bryan_roger.gestionCompte;

import fr.bryan_roger.gestionCompte.bo.*;
import fr.bryan_roger.gestionCompte.dal.*;

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
                                        IncomeRepository incomeRepository, WalletRepository walletRepository, BudgetRepository budgetRepository, HomeRepository homeRepository, RepartitionRepository repartitionRepository) {
        return args -> {

            if (userRepository.findAll().isEmpty()) {


                //Gestion des dates

                LocalDate currentDate = LocalDate.now();
                LocalDate dateLastMonth = currentDate.minusMonths(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
                String dateOneMonthAgo = dateLastMonth.format(formatter);
                String dateCurrentMonth = currentDate.format(formatter);
                String dateStringCurrentMonth = "01-2025";


                Tag tagLoisir = tagRepository.save(new Tag(null, "Loisir", false, true));
                Tag tagCourse = tagRepository.save(new Tag(null, "Courses", false, true));
                Tag tagSalaire = tagRepository.save(new Tag(null, "Salaire", true, false));
                Tag tagSalaire2 = tagRepository.save(new Tag(null, "Salaire", true, false));
                tagRepository.findAll().forEach(System.out::println);

                Household foyerPrincipal = homeRepository.save(new Household(null, List.of(tagSalaire, tagCourse, tagLoisir), "Foyer principal"));
                Household foyerSecondaire = homeRepository.save(new Household(null, List.of(tagSalaire2), "Seconde Zone"));

                User bryan = userRepository.save(new User("bryan@gmail.com", "Roger", "Bryan", "#9AED8F", "#109592", passwordEncoder.encode("test"), "ROLES_ADMIN", List.of(foyerPrincipal, foyerSecondaire)));
                User flora = userRepository.save(new User("flora@gmail.com", "Kurti", "Flora", "#109592", "#FFEFBF",
                        passwordEncoder.encode("test"), "ROLES_USER", List.of(foyerPrincipal)));
                userRepository.findAll().forEach(System.out::println);

                Spend spendCourses = spendRepository.save(new Spend(null, "Courses", dateCurrentMonth, bryan, List.of(bryan, flora), BigDecimal.valueOf(75.23), tagCourse, foyerPrincipal, 1));
                Spend spendHomeTrainer = spendRepository.save(new Spend(null, "Home trainer", dateCurrentMonth, bryan, List.of(bryan), BigDecimal.valueOf(499.99), tagLoisir, foyerPrincipal, 2));
                Spend spendIglooPortatif = spendRepository.save(new Spend(null, "Igloo portatif", dateCurrentMonth, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir, foyerPrincipal, 3));
                Spend spendIglooPortatifMoisPrecedent = spendRepository.save(new Spend(null, "Igloo portatif", dateOneMonthAgo, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagLoisir, foyerPrincipal, 1));
                Spend spend2eFoyer = spendRepository.save(new Spend(null, "Test dep 2e foyer", dateCurrentMonth, flora, List.of(flora, bryan), BigDecimal.valueOf(120.99), tagSalaire2, foyerSecondaire, 1));
                spendRepository.findAll().forEach(System.out::println);


                Income salaireBryan = incomeRepository.save(new Income(null, tagSalaire, bryan, BigDecimal.valueOf(2000), dateCurrentMonth, 1, foyerPrincipal));
                Income salaireFlora = incomeRepository.save(new Income(null, tagSalaire, flora, BigDecimal.valueOf(5000), dateCurrentMonth, 2, foyerPrincipal));
                incomeRepository.findAll().forEach(System.out::println);

                Budget budgetCourse = budgetRepository.save(new Budget(null, BigDecimal.valueOf(400), tagCourse));
                Budget budgetLoisirBryan = budgetRepository.save(new Budget(null, BigDecimal.valueOf(200), tagLoisir));
                Budget budgetLoisirFlora = budgetRepository.save(new Budget(null, BigDecimal.valueOf(1500), tagLoisir));
                budgetRepository.findAll().forEach(System.out::println);


                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                Date yesterday = calendar.getTime();

                Wallet walletBryanActive = walletRepository.save(new Wallet(null, List.of(budgetCourse, budgetLoisirBryan), dateStringCurrentMonth, null, true, foyerPrincipal, bryan));
                Wallet walletFloraActive = walletRepository.save(new Wallet(null, List.of(budgetCourse, budgetLoisirFlora), dateStringCurrentMonth, null, true, foyerPrincipal, flora));
                walletRepository.findAll().forEach(System.out::println);

                Repartition repartition1 = repartitionRepository.save(new Repartition(null,"03-2024", "10-2024", false,foyerPrincipal, bryan, flora, BigDecimal.valueOf(10), BigDecimal.valueOf(90)));
                Repartition repartition2 = repartitionRepository.save(new Repartition(null,"10-2024", "11-2024", false,foyerPrincipal, bryan, flora, BigDecimal.valueOf(70), BigDecimal.valueOf(30)));
                Repartition repartition3 = repartitionRepository.save(new Repartition(null,"11-2024", dateCurrentMonth, true, foyerPrincipal, bryan, flora, BigDecimal.valueOf(50), BigDecimal.valueOf(50)));
                repartitionRepository.findAll().forEach(System.out::println);



            }
        };
    }


}
