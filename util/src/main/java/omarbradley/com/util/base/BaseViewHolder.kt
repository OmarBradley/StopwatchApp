package omarbradley.com.util.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer

abstract class BaseDataBindingViewHolder(
    viewDataBinding: ViewDataBinding
) : BaseViewHolder(viewDataBinding.root)
