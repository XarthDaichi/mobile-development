package unaeif411.delta.petmatch.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import unaeif411.delta.petmatch.adapters.SettingsAdapter
import unaeif411.delta.petmatch.databinding.FragmentSettingsBinding
import unaeif411.delta.petmatch.repository.UserRepository
import unaeif411.delta.petmatch.service.UserService
import unaeif411.delta.petmatch.viewmodel.LoginViewModel
import unaeif411.delta.petmatch.viewmodel.ViewModelFactory

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by viewModels()
    private val settingsAdapter = SettingsAdapter()
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val SettingsViewModel =
            ViewModelProvider(this, ViewModelFactory(userRepository = UserRepository(UserService.getInstance()))).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //binding.settingsItemsList.layoutManager = LinearLayoutManager(context)
        //binding.settingsItemsList.adapter = settingsAdapter

        settingsViewModel.settingsItemList.observe(viewLifecycleOwner){
            settingsAdapter.setSettingsItemsList(it)
        }

        settingsViewModel.findAllSettingsItems()

        /*val textView: TextView = binding.fragmentHeader
        SettingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        binding.logoutCard.setOnClickListener(){
            loginViewModel.logout()
            activity?.finish()
        }

        /*holder.itemView.setOnClickListener(){
            loginViewModel.logout()
            activity.finish()
        }*/ //todo implementar logout

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}