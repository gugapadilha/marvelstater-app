package daniel.lop.io.marvelappstarter.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.databinding.FragmentDetailsCharacterBinding
import daniel.lop.io.marvelappstarter.databinding.FragmentListCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment

@AndroidEntryPoint
class DetailCharacterFragment :
    BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {
    //override val viewModel: DetailsCharacterViewModel by viewModels()
    val args: DetailCharacterFragmentArgs by navArgs()


    override fun onResume() {
        super.onResume()
        binding.tvNameCharacterDetails.text = args.character.name
        binding.tvDescriptionCharacterDetails.text = args.character.description
            Glide.with(this)
                .load(args.character.thumbnailModel.path + "." + args.character.thumbnailModel.extension)
                .into(binding.imgCharacterDetails)
    }

    override val viewModel: DetailsCharacterViewModel
        get() = TODO("Not yet implemented")

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding = FragmentDetailsCharacterBinding
        .inflate(inflater, container, false)

}