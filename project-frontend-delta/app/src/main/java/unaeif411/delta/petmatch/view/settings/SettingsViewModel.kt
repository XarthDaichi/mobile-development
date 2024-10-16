package unaeif411.delta.petmatch.view.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import unaeif411.delta.petmatch.model.settings.SettingsItem
import unaeif411.delta.petmatch.model.settings.SettingsItemProvider
import unaeif411.delta.petmatch.repository.UserRepository

class SettingsViewModel constructor (
    private var repository: UserRepository
) : ViewModel() {

    val settingsItem = MutableLiveData<SettingsItem>()
    val settingsItemList = MutableLiveData<List<SettingsItem>>()

    fun getSettingsItem(){
        val position = (0..9).random()
        val _settingsItem = SettingsItemProvider.findItemById(position)
        settingsItem.postValue(_settingsItem)
    }

    fun findAllSettingsItems(){
        val _settingsItemList = SettingsItemProvider.findAllItems()
        settingsItemList.postValue(_settingsItemList)
    }

}