package unaeif411.delta.petmatch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unaeif411.delta.petmatch.databinding.ReportsItemBinding
import unaeif411.delta.petmatch.model.report.ReportDetails

class ReportsAdapter : RecyclerView.Adapter<ReportsViewHolder>(){
    var reportsListItems = mutableListOf<ReportDetails>()

    fun setReportsList(reportDetails: List<ReportDetails>){
        this.reportsListItems.clear()
        this.reportsListItems.addAll(reportDetails.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReportsItemBinding.inflate(inflater, parent, false)

        return ReportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        val report = reportsListItems[position]
        holder.binding.reportedPetName.text = report.animal.name
        holder.binding.reportDescription.text = report.description
    }

    override fun getItemCount(): Int {
        return reportsListItems.size
    }

    companion object{
        const val REPORT_ID = "report_id"
    }
}

class ReportsViewHolder(
    val binding: ReportsItemBinding
) : RecyclerView.ViewHolder(binding.root)