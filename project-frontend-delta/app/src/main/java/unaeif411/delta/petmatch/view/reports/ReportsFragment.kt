package unaeif411.delta.petmatch.view.reports

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import unaeif411.delta.petmatch.adapters.ReportsAdapter
import unaeif411.delta.petmatch.databinding.FragmentReportsBinding

class ReportsFragment : Fragment() {
    private var _binding: FragmentReportsBinding? = null

    private val binding get() = _binding!!

    //TODO cambiar por reposrtsViewModel y reportsAdapter al crear
    private val reportsViewModel: ReportsViewModel by activityViewModels { ReportsViewModelFactory() }
    private val reportsAdapter = ReportsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)

        binding.reportsList.layoutManager = LinearLayoutManager(context)
        binding.reportsList.adapter = reportsAdapter

        //Adds the "brand" colors to PetMatch text in header
        val textView: TextView = binding.appNameHeader
        textView.text= Html.fromHtml("<font color=#538691>Pet</font>" +
                "<font color=#E58838>Match</font>")

        reportsViewModel.getAllReports()

        reportsViewModel.listedReports.observe(viewLifecycleOwner){
            reportsAdapter.setReportsList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}