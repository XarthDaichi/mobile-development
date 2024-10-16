package unaeif411.delta.petmatch.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.recyclerview.widget.RecyclerView
import unaeif411.delta.petmatch.databinding.SettingsListItemBinding
import unaeif411.delta.petmatch.model.settings.SettingsItem
import unaeif411.delta.petmatch.viewmodel.LoginViewModel

class SettingsAdapter : RecyclerView.Adapter<SettingsListViewHolder>(){
    var settingsItems = mutableListOf<SettingsItem>()
    private lateinit var loginViewModel: LoginViewModel

    fun setSettingsItemsList(settingsItems: List<SettingsItem>){
        this.settingsItems = settingsItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SettingsListItemBinding.inflate(inflater,parent,false)

        return SettingsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsListViewHolder, position: Int) {
        val settingsItem = settingsItems[position]
        holder.binding.settingsItemIcon.id = settingsItem.icon
        holder.binding.settingsItemName.text = settingsItem.name

        /*holder.itemView.setOnClickListener(){
            loginViewModel.logout()
            activity.finish()
        }*/ //todo implementar logout
    }

    override fun getItemCount(): Int {
        return settingsItems.size
    }
}

class SettingsListViewHolder(
    val binding: SettingsListItemBinding
) : RecyclerView.ViewHolder(binding.root)