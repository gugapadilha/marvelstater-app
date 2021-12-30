package daniel.lop.io.marvelappstarter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.data.model.character.CharacterModel
import daniel.lop.io.marvelappstarter.databinding.ItemCharacterBinding
import daniel.lop.io.marvelappstarter.util.limitDescription
import daniel.lop.io.marvelappstarter.util.loadImage

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>(){
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            //chamado para ver se os items antigos e os novos sao iguais
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            //verificar se os menos items novos e antigos representam a mesma coisa visualmente falando
            return oldItem.id == newItem.id && oldItem.name == newItem.name &&
                    oldItem.description == newItem.description && oldItem.thumbnailModel.path ==
                    newItem.thumbnailModel.path && oldItem.thumbnailModel.extension ==
                    newItem.thumbnailModel.extension

        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var characters: List<CharacterModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.apply {
            tvNameCharacter.text = character.name
            if (character.description == ""){ //caso o personagem nao tenha descrição
                tvDescriptionCharacter.text = holder.itemView.context.getString(R.string.text_description_empty)
            }else{ //mostra a descrição de ate 100 letras dos personagens
                tvDescriptionCharacter.text = character.description.limitDescription(100)
            }
            loadImage(imgCharacter,
                character.thumbnailModel.path,
                character.thumbnailModel.extension)
        }

        holder.itemView.setOnClickListener{
            onItemClickListener?.let {
                it(character)
            }
        }
    }

    private var onItemClickListener: ((CharacterModel) -> Unit)? =  null

    fun setOnClickListener(listener: (CharacterModel) -> Unit){
        onItemClickListener = listener
    }

    fun getCharacterPosition(position: Int): CharacterModel {
        return characters[position]

    }

}