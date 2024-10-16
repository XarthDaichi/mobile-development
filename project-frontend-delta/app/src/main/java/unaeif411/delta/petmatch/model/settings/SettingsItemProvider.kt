package unaeif411.delta.petmatch.model.settings

/**
 * This is a temporary class to simulate the interaction with
 * data
 */
class SettingsItemProvider {
    companion object {
        fun findItemById(id: Int) : SettingsItem {
            return taskList[id]
        }

        fun findAllItems() : List<SettingsItem> {
            return taskList
        }

        val taskList = listOf<SettingsItem>(
            SettingsItem(
                1,
                1,
                "Log out"
            )
        )
    }
}