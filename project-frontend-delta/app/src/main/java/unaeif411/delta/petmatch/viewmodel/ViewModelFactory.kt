package unaeif411.delta.petmatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import unaeif411.delta.petmatch.repository.AIRepository
import unaeif411.delta.petmatch.repository.PetRepository
import unaeif411.delta.petmatch.repository.ReportRepository
import unaeif411.delta.petmatch.repository.UserRepository
import unaeif411.delta.petmatch.view.ai.AIViewModel
import unaeif411.delta.petmatch.view.petlist.PetListViewModel
import unaeif411.delta.petmatch.view.search.SearchViewModel
import unaeif411.delta.petmatch.view.settings.SettingsViewModel

class ViewModelFactory constructor(
    private val AIrepository: AIRepository? = null,
    private val petRepository: PetRepository? = null,
    private val repRepository: ReportRepository? = null,
    private val userRepository: UserRepository? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AIViewModel::class.java -> this.AIrepository?.let { AIViewModel(it) } as T
            PetListViewModel::class.java -> this.petRepository?.let {PetListViewModel(it) } as T
            SearchViewModel::class.java -> this.petRepository?.let {SearchViewModel(it) } as T
            SettingsViewModel::class.java -> this.userRepository?.let {SettingsViewModel(it) } as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }

        /*
        return if (modelClass.isAssignableFrom(AIViewModel::class.java)) {
            AIViewModel(this.AIrepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

         */
    }

}