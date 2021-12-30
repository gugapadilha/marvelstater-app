package daniel.lop.io.marvelappstarter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.data.model.character.CharacterModel
import daniel.lop.io.marvelappstarter.data.model.comic.ComicModel
import daniel.lop.io.marvelappstarter.databinding.ItemCharacterBinding
import daniel.lop.io.marvelappstarter.databinding.ItemComicBinding
import daniel.lop.io.marvelappstarter.util.limitDescription
import daniel.lop.io.marvelappstarter.util.loadImage

class ComicAdapter: RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(val binding: ItemComicBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ComicModel>(){

        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            //chamado para ver se os items antigos e os novos sao iguais
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            //verificar se os menos items novos e antigos representam a mesma coisa visualmente falando
            return oldItem.id == newItem.id && oldItem.title == newItem.title  &&
                    oldItem.description == newItem.description && oldItem.thumbnailModel.path ==
                    newItem.thumbnailModel.path && oldItem.thumbnailModel.extension ==
                    newItem.thumbnailModel.extension
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var characters: List<ComicModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = characters[position]
        holder.binding.apply {
            tvNameComic.text = comic.title
            tvDescriptionComic.text = comic.description

        loadImage(imgComic, comic.thumbnailModel.path, comic.thumbnailModel.extension)
        }
    }
}