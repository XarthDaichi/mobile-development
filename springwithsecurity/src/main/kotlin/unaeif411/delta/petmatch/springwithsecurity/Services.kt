package unaeif411.delta.petmatch.springwithsecurity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface RoleService {
    fun findAll(): List<RoleDetails>?
    fun findById(id: Long): RoleDetails?
}

@Service
class AbstractRoleService(
    @Autowired
    val roleRepository: RoleRepository,
    @Autowired
    val roleMapper: RoleMapper
) : RoleService {
    override fun findAll(): List<RoleDetails>? {
        return roleMapper.roleListToRoleDetailsList(roleRepository.findAll())
    }

    override fun findById(id: Long): RoleDetails? {
        val role: Optional<Role> = roleRepository.findById(id)
        return if (role.isPresent) {
            roleMapper.roleToRoleDetails(role.get())
        } else {
            null
        }
    }
}

interface UserService {
    fun findAll(): List<UserDetails>?
    fun findById(id: Long): UserDetails?
    fun create(userInput: UserDetails): UserDetails?
    fun update(userInput: UserDetails): UserDetails?
    fun deleteById(id: Long)
}

@Service
class AbstractUserService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val userMapper: UserMapper
) : UserService {
    override fun findAll(): List<UserDetails>? {
        return userMapper.userListToUserDetailsList(userRepository.findAll())
    }

    override fun findById(id: Long): UserDetails? {
        val user: Optional<User> = userRepository.findById(id)
        return if (user.isPresent) {
            userMapper.userToUserDetails(user.get())
        } else {
            null
        }
    }

    override fun create(userInput: UserDetails): UserDetails? {
        val user: User = userMapper.userDetailsToUser(userInput)
        return userMapper.userToUserDetails(userRepository.save(user))
    }

    override fun update(userInput: UserDetails): UserDetails? {
        val user: Optional<User> = userRepository.findById(userInput.username!!.toLong())
        if (user.isEmpty) {
            throw NoSuchElementException(String.format("The User with the username: %s not found!", userInput.username))
        }
        val userUpdated: User = user.get()
        userMapper.userDetailsToUser(userInput)
        return userMapper.userToUserDetails(userRepository.save(userUpdated))
    }

    override fun deleteById(id: Long) {
        if (userRepository.findById(id).isPresent) {
            userRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The User with the id: %s not found!", id))
        }
    }
}

interface AnimalService {
    fun findAll(): List<AnimalDetails>?
    fun findById(id: Long): AnimalDetails?
    fun create(animalInput: AnimalDetails): AnimalDetails?
    fun update(animalInput: AnimalDetails): AnimalDetails?
    fun deleteById(id: Long)
}

@Service
class AbstractAnimalService(
    @Autowired
    val petRepository: PetRepository,
    @Autowired
    val animalMapper: AnimalMapper
) : AnimalService {
    override fun findAll(): List<AnimalDetails>? {
        return animalMapper.animalListToAnimalDetailsList(petRepository.findAll())
    }

    override fun findById(id: Long): AnimalDetails? {
        val animal: Optional<Animal> = petRepository.findById(id)
        return if (animal.isPresent) {
            animalMapper.animalToAnimalDetails(animal.get())
        } else {
            null
        }
    }

    override fun create(animalInput: AnimalDetails): AnimalDetails? {
        val animal: Animal = animalMapper.animalDetailsToAnimal(animalInput)
        return animalMapper.animalToAnimalDetails(petRepository.save(animal))
    }

    override fun update(animalInput: AnimalDetails): AnimalDetails? {
        val animal: Optional<Animal> = petRepository.findById(animalInput.id!!)
        if (animal.isEmpty) {
            throw NoSuchElementException(String.format("The Animal with the id: %s not found!", animalInput.id))
        }
        val animalUpdated: Animal = animal.get()
        animalMapper.animalDetailsToAnimal(animalInput)
        return animalMapper.animalToAnimalDetails(petRepository.save(animalUpdated))
    }

    override fun deleteById(id: Long) {
        if (petRepository.findById(id).isPresent) {
            petRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The Animal with the id: %s not found!", id))
        }
    }
}

interface ReportService {
    fun findAll(): List<ReportDetails>?
    fun findById(id: Long): ReportDetails?
    fun create(reportInput: ReportDetails): ReportDetails?
    fun update(reportInput: ReportDetails): ReportDetails?
    fun deleteById(id: Long)
}

@Service
class AbstractReportService(
    @Autowired
    val reportRepository: ReportRepository,
    @Autowired
    val reportMapper: ReportMapper
) : ReportService {
    override fun findAll(): List<ReportDetails>? {
        return reportMapper.reportListToReportDetailsList(reportRepository.findAll())
    }

    override fun findById(id: Long): ReportDetails? {
        val report: Optional<Report> = reportRepository.findById(id)
        return if (report.isPresent) {
            reportMapper.reportToReportDetails(report.get())
        } else {
            null
        }
    }

    override fun create(reportInput: ReportDetails): ReportDetails? {
        val report: Report = reportMapper.reportDetailsToReport(reportInput)
        return reportMapper.reportToReportDetails(reportRepository.save(report))
    }

    override fun update(reportInput: ReportDetails): ReportDetails? {
        val report: Optional<Report> = reportRepository.findById(reportInput.id!!)
        if (report.isEmpty) {
            throw NoSuchElementException(String.format("The Report with the id: %s not found!", reportInput.id))
        }
        val reportUpdated: Report = report.get()
        reportMapper.reportDetailsToReport(reportInput)
        return reportMapper.reportToReportDetails(reportRepository.save(reportUpdated))
    }

    override fun deleteById(id: Long) {
        if (reportRepository.findById(id).isPresent) {
            reportRepository.deleteById(id)
        } else {
            throw NoSuchElementException(String.format("The Report with the id: %s not found!", id))
        }
    }
}

@Service
@Transactional
class AppUserDetailsService(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val roleRepository: RoleRepository,
) : UserDetailsService {

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the `UserDetails`
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never `null`)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     * GrantedAuthority
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): org.springframework.security.core.userdetails.UserDetails {
        val userAuth: org.springframework.security.core.userdetails.User
        val user: User = userRepository.findByEmail(username).orElse(null)
            ?: return org.springframework.security.core.userdetails.User(
                "", "", true, true, true, true,
                getAuthorities(
                    listOf(
                        roleRepository.findByName("ROLE_USER").get()
                    )
                )
            )

        userAuth = org.springframework.security.core.userdetails.User(
            user.email, user.password, user.enabled == true, true, true,
            true, getAuthorities(user.roleList!!.toMutableList())
        )

        return userAuth
    }

    private fun getAuthorities(roles: Collection<Role>): Collection<GrantedAuthority> {
        return roles.flatMap { role ->
            sequenceOf(SimpleGrantedAuthority(role.name)) +
                    role.privilegeList!!.map { privilege -> SimpleGrantedAuthority(privilege.name) }
        }.toList()
    }

}

//interface AIService {
//    fun findAll(): List<String>?
//    fun findById(id: Long): String?
//    fun create(aiData: String): String?
//    fun update(id: Long, aiData: String): String?
//    fun deleteById(id: Long)
//}

//@Service
//class AbstractAIService(
//    @Autowired
//    val aiRepository: AIRepository
//) : AIService {
//    override fun findAll(): List<String>? {
//        return aiRepository.findAll()
//    }
//
//    override fun findById(id: Long): String? {
//        val aiData: Optional<String> = aiRepository.findById(id)
//        return aiData.orElse(null)
//    }
//
//    override fun create(aiData: String): String? {
//        return aiRepository.save(aiData)
//    }
//
//    override fun update(id: Long, aiData: String): String? {
//        val existingAIData: Optional<String> = aiRepository.findById(id)
//        if (existingAIData.isEmpty) {
//            throw NoSuchElementException(String.format("The AI data with the id: %s not found!", id))
//        }
//        return aiRepository.save(aiData)
//    }
//
//    override fun deleteById(id: Long) {
//        if (aiRepository.findById(id).isPresent) {
//            aiRepository.deleteById(id)
//        } else {
//            throw NoSuchElementException(String.format("The AI data with the id: %s not found!", id))
//        }
//    }
//}