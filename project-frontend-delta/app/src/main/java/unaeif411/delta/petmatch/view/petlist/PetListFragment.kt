package unaeif411.delta.petmatch.view.petlist

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import unaeif411.delta.petmatch.adapters.PetListAdapter
import unaeif411.delta.petmatch.databinding.FragmentPetlistBinding
import unaeif411.delta.petmatch.repository.PetRepository
import unaeif411.delta.petmatch.service.PetService
import unaeif411.delta.petmatch.viewmodel.ViewModelFactory

class PetListFragment : Fragment() {

    private var _binding: FragmentPetlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val petListViewModel: PetListViewModel by activityViewModels { PetListViewModelFactory() }
    private val petListAdapter = PetListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPetlistBinding.inflate(inflater, container, false)

        binding.petsList.layoutManager = LinearLayoutManager(context)
        binding.petsList.adapter = petListAdapter

        //Adds the "brand" colors to PetMatch text in header
        val textView: TextView = binding.appNameHeader
        textView.text= Html.fromHtml("<font color=#538691>Pet</font>" +
                "<font color=#E58838>Match</font>")

        petListViewModel.getAllPets()

        petListViewModel.listedPets.observe(viewLifecycleOwner){
            petListAdapter.setPetsList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}