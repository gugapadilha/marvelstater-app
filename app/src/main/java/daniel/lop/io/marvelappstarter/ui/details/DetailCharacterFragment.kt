package daniel.lop.io.marvelappstarter.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.databinding.FragmentSearchCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment

@AndroidEntryPoint
class DetailCharacterFragment: BaseFragment<FragmentSearchCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBlinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}