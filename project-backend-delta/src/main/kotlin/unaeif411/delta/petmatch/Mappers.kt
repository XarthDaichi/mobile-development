package unaeif411.delta.petmatch

import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RoleMapper {
    @Mapping(source = "privilegeList", target = "privileges")
    fun roleToRoleDetails(role: Role): RoleDetails
    fun roleDetailsToRole(roleDetails: RoleDetails): Role
    fun roleListToRoleDetailsList(roleList: List<Role>): List<RoleDetails>
    fun roleDetailsListToRoleList(roleDetailsList: List<RoleDetails>): List<Role>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PrivilegeMapper {
    fun privilegeToPrivilegeDetails(privilege: Privilege): PrivilegeDetails
    fun privilegeDetailsToPrivilege(privilegeDetails: PrivilegeDetails): Privilege
    fun privilegeListToPrivilegeDetailsList(privilegeList: List<Privilege>): List<PrivilegeDetails>
    fun privilegeDetailsListToPrivilegeList(privilegeDetailsList: List<PrivilegeDetails>): List<Privilege>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    @Mapping(source = "petList", target = "pets")
    @Mapping(source = "likedPetList", target = "likedPets")
    @Mapping(source = "roleList", target = "roles")
    fun userToUserDetails(user: User): UserDetails
    fun userDetailsToUser(userDetails: UserDetails): User
    fun userListToUserDetailsList(userList: List<User>): List<UserDetails>
    fun userDetailsListToUserList(userDetailsList: List<UserDetails>): List<User>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface AnimalMapper {
    @Mapping(source = "user", target = "user")
    @Mapping(source = "likedAnimal", target = "likedByUsers")
    @Mapping(source = "speciesBreed", target = "speciesBreed")
    fun animalToAnimalDetails(animal: Animal): AnimalDetails
    fun animalDetailsToAnimal(animalDetails: AnimalDetails): Animal
    fun animalListToAnimalDetailsList(animalList: List<Animal>): List<AnimalDetails>
    fun animalDetailsListToAnimalList(animalDetailsList: List<AnimalDetails>): List<Animal>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SpeciesMapper {
    @Mapping(source = "breedList", target = "breeds")
    fun speciesToSpeciesDetails(species: Species): SpeciesDetails
    fun speciesDetailsToSpecies(speciesDetails: SpeciesDetails): Species
    fun speciesListToSpeciesDetailsList(speciesList: List<Species>): List<SpeciesDetails>
    fun speciesDetailsListToSpeciesList(speciesDetailsList: List<SpeciesDetails>): List<Species>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BreedMapper {
    fun breedToBreedDetails(breed: Breed): BreedDetails
    fun breedDetailsToBreed(breedDetails: BreedDetails): Breed
    fun breedListToBreedDetailsList(breedList: List<Breed>): List<BreedDetails>
    fun breedDetailsListToBreedList(breedDetailsList: List<BreedDetails>): List<Breed>
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReportMapper {
    @Mapping(source = "animal", target = "animal")
    fun reportToReportDetails(report: Report): ReportDetails
    fun reportToReportResults(report: Report): ReportResult
    fun reportDetailsToReport(reportDetails: ReportDetails): Report
    fun reportListToReportDetailsList(reportList: List<Report>): List<ReportDetails>
    fun reportListToReportResultList(reportList: List<Report>): List<ReportResult>
    fun reportDetailsListToReportList(reportDetailsList: List<ReportDetails>): List<Report>
}