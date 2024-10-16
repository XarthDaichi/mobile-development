package unaeif411.delta.petmatch.springwithsecurity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.jdbc.Sql

@Profile("initlocal")
@SpringBootTest
@Sql("/import-database.sql")
class LoadInitData (
    @Autowired
    val petRepository: PetRepository,
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val reportRepository: ReportRepository,
) {
    @Test
    fun testFindAll() {
        val petList: List<Animal> = petRepository.findAll();
        Assertions.assertTrue(petList.size == 1);
        val userList: List<User> = userRepository.findAll()
        Assertions.assertTrue(userList.size == 4);
        val reportList: List<Report> = reportRepository.findAll()
        Assertions.assertTrue(reportList.size == 1);
    }
}