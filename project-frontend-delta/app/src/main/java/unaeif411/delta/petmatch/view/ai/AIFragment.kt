package unaeif411.delta.petmatch.view.ai

import android.content.Context
import android.widget.Toast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import unaeif411.delta.petmatch.databinding.FragmentAiBinding
import unaeif411.delta.petmatch.repository.AIRepository
import unaeif411.delta.petmatch.service.GoogleAIConnector
import unaeif411.delta.petmatch.viewmodel.ViewModelFactory

class AIFragment : Fragment() {

    private var _binding: FragmentAiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: AIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory(AIrepository = AIRepository(GoogleAIConnector.getInstance()))).get(AIViewModel::class.java)

        _binding = FragmentAiBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(AIrepository = AIRepository(GoogleAIConnector.getInstance())))
            .get(AIViewModel::class.java)

        setupSearch()

        viewModel.Desc.observe(viewLifecycleOwner) { description ->
            binding.textView.text = description  // Asegúrate de que este ID corresponde al TextView en tu XML
        }
    }

    private fun setupSearch() {
        binding.searchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true  // Consume el evento de acción
            } else {
                false  // No consume el evento de acción
            }
        }

        binding.searchButton.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        val searchText = binding.searchField.text.toString()
        if (searchText.isNotEmpty()) {
            viewModel.getAnimalDesc(searchText)

            // Cierra el teclado
            val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(binding.searchField.windowToken, 0)

        } else {
            Toast.makeText(context, "Please enter a search query", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}