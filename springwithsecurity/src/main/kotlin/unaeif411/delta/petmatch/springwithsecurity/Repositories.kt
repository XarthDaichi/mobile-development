package unaeif411.delta.petmatch.springwithsecurity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PetRepository: JpaRepository<Animal, Long>

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(@Param("email") email : String) : Optional<User>
}

@Repository
interface ReportRepository: JpaRepository<Report, Long>

//@Repository
//interface AIRepository: JpaRepository<String, Long>

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName (@Param("name") name : String) : Optional<Role>
}