package unaeif411.delta.petmatch.model.role

import unaeif411.delta.petmatch.model.privelege.Privilege

data class Role(
    var id: String,
    var privileges: List<Privilege>
)
