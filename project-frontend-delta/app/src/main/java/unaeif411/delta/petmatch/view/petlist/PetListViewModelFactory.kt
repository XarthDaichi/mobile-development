package unaeif411.delta.petmatch.view.petlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import unaeif411.delta.petmatch.repository.PetRepository
import unaeif411.delta.petmatch.service.PetService

@Suppress("UNCHECKED_CAST")
class PetListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PetListViewModel::class.java)) {
            PetListViewModel(
                petRepository = PetRepository(
                    petService = PetService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}