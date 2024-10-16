package unaeif411.delta.petmatch.springwithsecurity

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name:String = "RoleName",

    @ManyToMany
    @JoinTable(
        name = "role_privilege",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    var privilegeList: Set<Privilege>? = null,
) {
    override fun equals(other:Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return return "Role(id=$id, name=$name, privilegeList=$privilegeList)"
    }
}

@Entity
@Table(name = "privilege")
data class Privilege(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name:String = "PrivilegeName",
    // Entity Relationship
    @ManyToMany(fetch = FetchType.LAZY)
    var userList: Set<User>? = null,
    @ManyToMany(fetch = FetchType.LAZY)
    var roleList: Set<Role>? = null,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Privilege) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Privilege(id=$id, name=$name, userList=$userList, roleList=$roleList)"
    }
}

@Entity
@Table(name = "animal")
data class Animal(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name:String = "this is the name",
    var likes:String = "theses are the pets likes",
    var description:String = "this is the pet description",

    @ManyToOne(fetch = FetchType.LAZY)
    var user:User = User(),

    @ManyToMany(fetch = FetchType.LAZY)
    var likedAnimal: Set<User>? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    var speciesBreed:Species = Species(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Animal) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString():String {
        return "Animal(id=$id, name=$name, likes=$likes, description=$description, user=$user, likedAnimal=$likedAnimal, speciesBreed=$speciesBreed)"
    }
}

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var username: String? = null,
    var firstName: String = "firstName",
    var lastName: String = "lastName",
    var password: String = "password",
    var email: String = "this is an email",
    var phone: String = "this is a phone number",
    var createDate: Date = Date.from(Instant.now()),
    var enabled: Boolean? = null,
    var tokenExpired: Boolean? = null,
    // Entity Relationship
    @OneToMany(mappedBy = "id")
    var petList: List<Animal>? = null,

    @ManyToMany
    @JoinTable(
        name = "likes",
        joinColumns = [JoinColumn(name = "username", referencedColumnName = "username")],
        inverseJoinColumns = [JoinColumn(name = "animal_id", referencedColumnName = "id")]
    )
    var likedPetList: List<Animal>? = null,

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_username", referencedColumnName = "username")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roleList: Set<Role>? = null,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (username != other.username) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$username, firstName='$firstName', lastName='$lastName', password='$password', email='$email', createDate=$createDate, enabled=$enabled, tokenExpired=$tokenExpired, petList=$petList, likedPetList=$likedPetList, roleList=$roleList)"
    }

}

@Entity
@Table(name = "species")
data class Species(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name:String = "This is a species",

    @OneToMany(mappedBy = "id")
    var breedList:List<Breed>? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Species) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Species(id=$id, name=$name, breedList=$breedList)"
    }
}

@Entity
@Table(name = "breed")
data class Breed(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var name:String = "This is a breed"
) {
    override fun equals(other:Any?): Boolean {
        if (this === other) return true
        if (other !is Breed) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Breed(id=$id, name=$name)"
    }
}

@Entity
@Table(name = "report")
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long? = null,
    var description:String = "this is a report",

    @ManyToOne(fetch = FetchType.LAZY)
    var animal:Animal = Animal(),
) {
    override fun equals(other:Any?): Boolean {
        if (this === other) return true
        if (other !is Report) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Report(id=$id, description=$description, animal=$animal)"
    }
}