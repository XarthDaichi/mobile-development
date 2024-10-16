package unaeif411.delta.petmatch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unaeif411.delta.petmatch.databinding.PetListItemBinding
import unaeif411.delta.petmatch.model.animal.Animal

class PetListAdapter : RecyclerView.Adapter<PetListViewHolder>(){
    var petListItems = mutableListOf<Animal>()

    fun setPetsList(petsItems: List<Animal>){
        this.petListItems.clear()
        this.petListItems.addAll(petsItems.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PetListItemBinding.inflate(inflater,parent,false)

        return PetListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetListViewHolder, position: Int) {
        val petItem = petListItems[position]
        holder.binding.petName.text = petItem.name
        holder.binding.petBreed.text = petItem.speciesBreed.name

        //TODO: hacer un onClickListener como en TaskAdapter de Taskapp
    }

    override fun getItemCount(): Int {
        return petListItems.size
    }

    companion object{
        const val PET_ID = "pet_id"
    }
}

class PetListViewHolder(
    val binding: PetListItemBinding
):RecyclerView.ViewHolder(binding.root)