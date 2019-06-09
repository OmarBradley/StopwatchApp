package omarbradley.com.stopwatchapp.view.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import omarbradley.com.domain.entity.RapRecord
import omarbradley.com.stopwatchapp.databinding.ItemviewRapTimeBinding
import omarbradley.com.util.base.BaseDataBindingViewHolder
import omarbradley.com.util.date.HHmmssFormatString

class RapTimeItemAdapter : ListAdapter<RapTimeItem, RapTimeItemView>(RapTimeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RapTimeItemView =
        RapTimeItemView(
            ItemviewRapTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RapTimeItemView, position: Int) {
        holder.bind(getItem(position))
    }
}

class RapTimeItemDiffCallback : DiffUtil.ItemCallback<RapTimeItem>() {

    override fun areItemsTheSame(oldItem: RapTimeItem, newItem: RapTimeItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RapTimeItem, newItem: RapTimeItem): Boolean =
        oldItem == newItem

}

class RapTimeItemView(
    private val binding: ItemviewRapTimeBinding
) : BaseDataBindingViewHolder(binding) {

    fun bind(item: RapTimeItem) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}

data class RapTimeItem(
    val index: String,
    val totalTimeText: String,
    val recordTimeText: String
)

fun RapRecord.toRapTimeItem(index: Int) =
    RapTimeItem(
        index = index.toString(),
        totalTimeText = totalTimeMillis.HHmmssFormatString,
        recordTimeText = recordTimeMillis.HHmmssFormatString
    )
