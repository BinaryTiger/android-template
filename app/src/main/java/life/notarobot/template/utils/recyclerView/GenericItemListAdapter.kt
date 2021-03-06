package life.notarobot.template.utils.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericItemListAdapter<T>(
    listItems: List<T>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItems: List<T> = listItems ?: emptyList()

    fun setItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false),
            viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])

        holder.itemView.setOnClickListener {
            getOnClickAction().invoke()
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    protected abstract fun getOnClickAction(): () -> Unit

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T)
    }
}
